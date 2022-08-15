package ru.geckolab.mviexample.commons_android.models

import androidx.annotation.AnimRes
import ru.geckolab.mviexample.commons_android.R

enum class FragmentAnim(
    @AnimRes val enterFragment: Int,
    @AnimRes val exitFragment: Int,
    @AnimRes val enterFragmentBack: Int,
    @AnimRes val exitFragmentBack: Int
) {
    NONE(-1, -1, -1, -1),
    SHARED_X_AXIS_RIGHT(
        R.anim.x_axis_right_enter,
        R.anim.x_axis_right_exit,
        R.anim.x_axis_right_enter_pop,
        R.anim.x_axis_right_exit_pop
    ),
    SHARED_X_AXIS_LEFT(
        R.anim.x_axis_left_enter,
        R.anim.x_axis_left_exit,
        R.anim.x_axis_left_enter_pop,
        R.anim.x_axis_left_exit_pop
    ),
    SHARED_Z_AXIS(
        R.anim.z_axis_enter,
        R.anim.z_axis_exit,
        R.anim.z_axis_enter_pop,
        R.anim.z_axis_exit_pop
    ),
    FADE_THROUGH(
        R.anim.fade_through_enter,
        R.anim.fade_through_exit,
        R.anim.fade_through_enter_pop,
        R.anim.fade_through_exit_pop
    ),
    FADE_ABOVE(
        R.anim.fade_above_enter,
        R.anim.fade_above_exit,
        R.anim.fade_above_enter_pop,
        R.anim.fade_above_exit_pop
    ),
    FADE_ABOVE_WITHOUT_ENTER(
        R.anim.anim_nothing,
        R.anim.fade_above_exit,
        R.anim.anim_nothing,
        R.anim.fade_above_exit_pop
    );

    companion object {
        val default = FADE_ABOVE

        fun isNeedLift(nextAnimId: Int): Boolean {
            if (nextAnimId == -1) return false
            return false
        }
    }
}