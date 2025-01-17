package com.guet.flexbox.widget

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable

internal open class RoundedDrawable<T : Drawable>(
        drawable: T,
        var radius: Int = 0
) : DrawableWrapper<T>(drawable), Drawable.Callback {

    protected val path = Path()
    private val rectF = RectF()

    override fun draw(canvas: Canvas) {
        rectF.set(bounds)
        val sc = canvas.saveLayer(rectF, null)
        if (radius > 0) {
            path.apply {
                reset()
                addRoundRect(rectF, radius.toFloat(), radius.toFloat(), Path.Direction.CW)
                close()
            }
            canvas.clipPath(path)
        }
        wrappedDrawable.draw(canvas)
        canvas.restoreToCount(sc)
    }
}