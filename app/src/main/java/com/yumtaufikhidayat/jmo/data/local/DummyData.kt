package com.yumtaufikhidayat.jmo.data.local

import android.content.Context
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.model.profile.Profile
import dagger.hilt.android.qualifiers.ApplicationContext

object DummyData {

    fun provideListOfProfileMenu(@ApplicationContext context: Context): List<Profile> {
        return mutableListOf(
            Profile(
                profileMenuName = context.getString(R.string.txt_change_profile),
                profileMenuIcon = R.drawable.ic_baseline_edit,
                profileMenuTextColor = R.color.black
            ),
            Profile(
                profileMenuName = context.getString(R.string.txt_your_digital_card),
                profileMenuIcon = R.drawable.ic_baseline_keyboard_arrow_right,
                profileMenuTextColor = R.color.black
            ),
            Profile(
                profileMenuName = context.getString(R.string.txt_term_condition),
                profileMenuIcon = R.drawable.ic_baseline_keyboard_arrow_right,
                profileMenuTextColor = R.color.black
            ),
            Profile(
                profileMenuName = context.getString(R.string.txt_privacy_policy),
                profileMenuIcon = R.drawable.ic_baseline_keyboard_arrow_right,
                profileMenuTextColor = R.color.black
            ),
            Profile(
                profileMenuName = context.getString(R.string.txt_about_app),
                profileMenuIcon = R.drawable.ic_baseline_keyboard_arrow_right,
                profileMenuTextColor = R.color.black
            ),
            Profile(
                profileMenuName = context.getString(R.string.txt_logout),
                profileMenuIcon = R.drawable.ic_baseline_logout,
                profileMenuTextColor = R.color.red
            )
        )
    }
}