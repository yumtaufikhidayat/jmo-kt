package com.yumtaufikhidayat.jmo.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.yumtaufikhidayat.jmo.R

class MaterialCustomButton: MaterialButton {
    private lateinit var enableBackground: Drawable
    private lateinit var disableBackground: Drawable
    private var txtColor: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        enableBackground = ContextCompat.getDrawable(context, R.drawable.bg_button_enabled) as Drawable
        disableBackground = ContextCompat.getDrawable(context, R.drawable.bg_button_disabled) as Drawable
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        background = if (isEnabled) enableBackground else disableBackground

        setTextColor(txtColor)
        gravity = Gravity.CENTER

        text = when (id) {
            R.id.btnLogin -> this.context.getString(R.string.txt_login)
            R.id.btnRegister -> this.context.getString(R.string.txt_register)
            else -> ""
        }
    }
}