package com.yumtaufikhidayat.jmo.data.local

import android.content.Context
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.model.home.ServiceProgram
import com.yumtaufikhidayat.jmo.model.profile.Profile

object DummyData {

    fun provideListOfServiceProgram(context: Context): List<ServiceProgram> {
        return mutableListOf(
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_old_day_guarantee,
                serviceProgramTitle = context.getString(R.string.txt_old_days_guarantee)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_accident_guarantee,
                serviceProgramTitle = context.getString(R.string.txt_accidental_guarantee)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_death_guarantee,
                serviceProgramTitle = context.getString(R.string.txt_death_guarantee)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_retirement,
                serviceProgramTitle = context.getString(R.string.txt_retirement_guarantee)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_lost_job_guarantee,
                serviceProgramTitle = context.getString(R.string.txt_loss_of_a_job_guarantee)
            )
        )
    }

    fun provideListOfOtherServices(context: Context): List<ServiceProgram> {
        return mutableListOf(
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_info,
                serviceProgramTitle = context.getString(R.string.txt_program_info)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_auto_debit,
                serviceProgramTitle = context.getString(R.string.txt_auto_debit_pay)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_account,
                serviceProgramTitle = context.getString(R.string.txt_register_bpu)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_updates,
                serviceProgramTitle = context.getString(R.string.txt_update_data)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_branch_office,
                serviceProgramTitle = context.getString(R.string.txt_branch_office)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_partner,
                serviceProgramTitle = context.getString(R.string.txt_service_partner)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_compliant,
                serviceProgramTitle = context.getString(R.string.txt_complaint)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_help,
                serviceProgramTitle = context.getString(R.string.txt_help)
            ),
            ServiceProgram(
                serviceProgramIcon = R.drawable.ic_baseline_other_menu,
                serviceProgramTitle = context.getString(R.string.txt_other_menu)
            )
        )
    }

    fun provideListOfProfileMenu(context: Context): List<Profile> {
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