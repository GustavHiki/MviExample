package ru.geckolab.mviexample.commons_android.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import ru.geckolab.mviexample.commons_android.Tags
import ru.geckolab.mviexample.commons_android.debug
import ru.geckolab.mviexample.commons_android.models.FragmentAnim
import ru.geckolab.mviexample.commons_android.utils.ext.waitForTransition

abstract class BaseFragment(@LayoutRes private val layoutRes: Int) : Fragment(layoutRes) {
    val isLandscape: Boolean
        get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    override fun onAttach(context: Context) {
        super.onAttach(context)
        javaClass.debug(Tags.LIFECYCLE, "onAttach")
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            javaClass.debug(Tags.LIFECYCLE, "onBackPressed")
            onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        javaClass.debug(Tags.LIFECYCLE, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waitForTransition(view)
        javaClass.debug(Tags.LIFECYCLE, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        javaClass.debug(Tags.LIFECYCLE, "onStart")
    }

    override fun onResume() {
        super.onResume()
        javaClass.debug(Tags.LIFECYCLE, "onResume")
    }

    override fun onPause() {
        super.onPause()
        javaClass.debug(Tags.LIFECYCLE, "onPause")
    }

    override fun onStop() {
        super.onStop()
        javaClass.debug(Tags.LIFECYCLE, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        javaClass.debug(Tags.LIFECYCLE, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        javaClass.debug(Tags.LIFECYCLE, "onDestroy")
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (FragmentAnim.isNeedLift(nextAnim)) {
            val nextAnimation: Animation = AnimationUtils.loadAnimation(context, nextAnim)
            nextAnimation.setAnimationListener(object : Animation.AnimationListener {
                private var startZ = 0f
                override fun onAnimationStart(animation: Animation) {
                    view?.let { view ->
                        startZ = ViewCompat.getTranslationZ(view)
                        ViewCompat.setTranslationZ(view, 1f)
                    }
                }

                override fun onAnimationEnd(animation: Animation) {
                    view?.let { view ->
                        view.postDelayed({
                            ViewCompat.setTranslationZ(view, startZ)
                        }, 100)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            nextAnimation
        } else {
            null
        }
    }

    protected fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    protected fun wasRecreated(savedInstanceState: Bundle?) = savedInstanceState != null

    protected open fun onBackPressed() {}

    protected fun isViewDestroyed() = view == null

    protected fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            this@launchWhenStarted.collect()
        }
    }

    protected fun <T> Flow<T>.launchWhenCreated() {
        lifecycleScope.launchWhenCreated {
            this@launchWhenCreated.collect()
        }
    }

    protected fun <T> Flow<T>.launchWhenResumed() {
        lifecycleScope.launchWhenResumed {
            this@launchWhenResumed.collect()
        }
    }
}