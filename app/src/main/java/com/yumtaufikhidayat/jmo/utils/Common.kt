package com.yumtaufikhidayat.jmo.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random
import java.util.regex.Pattern


object Common {
    const val USER_PREFERENCES = "user_preferences"
    const val DELAY_TIME = 2000L
    const val STARTING_PAGE_INDEX = 1
    const val PAGE_SIZE = 10
    private const val EEE_D_MMM_YYYY_FORMAT = "EEE, d MMM yyyy"
    const val DATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val DATE_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    private var vibrantLightColorList = arrayOf(
        ColorDrawable(Color.parseColor("#FFEEAD")),
        ColorDrawable(Color.parseColor("#93CFB3")),
        ColorDrawable(Color.parseColor("#FD7A7A")),
        ColorDrawable(Color.parseColor("#FACA5F")),
        ColorDrawable(Color.parseColor("#1BA798")),
        ColorDrawable(Color.parseColor("#6AA9AE")),
        ColorDrawable(Color.parseColor("#FFBF27")),
        ColorDrawable(Color.parseColor("#D93947"))
    )

    private fun getRandomDrawableColor(): ColorDrawable {
        val idx: Int = Random().nextInt(vibrantLightColorList.size)
        return vibrantLightColorList[idx]
    }

    fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .placeholder(getRandomDrawableColor())
            .error(getRandomDrawableColor())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    fun String.convertDate(inputFormat: String): String {
        val formatter = SimpleDateFormat(inputFormat, Locale.getDefault())
        var formatParser = Date()
        if (this.isNotEmpty()) {
            formatParser = formatter.parse(this) ?: Date()
        }
        val newOutputFormat = SimpleDateFormat(EEE_D_MMM_YYYY_FORMAT, Locale.getDefault())
        return newOutputFormat.format(formatParser)
    }
}