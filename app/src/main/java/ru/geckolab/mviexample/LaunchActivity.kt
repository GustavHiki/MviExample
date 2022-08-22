package ru.geckolab.mviexample

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.commons_android.utils.ext.fadeIn
import ru.geckolab.mviexample.commons_android.utils.ext.fadeOut
import ru.geckolab.mviexample.commons_android.utils.ext.getColorCompat
import ru.geckolab.mviexample.commons_android.utils.ext.isGestureNavigationBar
import ru.geckolab.mviexample.commons_android.utils.ext.navigationBarHeight
import ru.geckolab.mviexample.commons_android.utils.viewBinding
import ru.geckolab.mviexample.databinding.ActivityLaunchBinding
import ru.geckolab.mviexample.navigation.root.RootTabsNavigator
import javax.inject.Inject
import ru.geckolab.mviexample.commons_android.R as CommonR

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val isLandscape: Boolean
        get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    private val viewModel: LaunchViewModel by viewModels()
    private val binding by viewBinding(ActivityLaunchBinding::inflate)

    private val navigator: Navigator by lazy {
        RootTabsNavigator(this, R.id.container, supportFragmentManager)
    }

    private val tabItemReselectedListener = NavigationBarView.OnItemReselectedListener { item ->
        when (item.itemId) {
            R.id.home -> viewModel.onTabReselected(RootPage.HOME)
        }
    }

    private val tabItemSelectedListener = NavigationBarView.OnItemSelectedListener { item ->
        return@OnItemSelectedListener when (item.itemId) {
            R.id.home -> {
                viewModel.onTabSelected(RootPage.HOME)
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.BaseTheme_AppTheme)
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        viewModel.onCreate()

        setContentView(binding.root)
        initBottomNavigation()

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    fun selectRootPage(rootPage: RootPage) {
        binding.bottomNavigation.setOnItemReselectedListener(null)
        binding.bottomNavigation.setOnItemSelectedListener(null)

        binding.bottomNavigation.selectedItemId = when (rootPage) {
            RootPage.HOME -> R.id.home
        }

        binding.bottomNavigation.setOnItemSelectedListener(tabItemSelectedListener)
        binding.bottomNavigation.setOnItemReselectedListener(tabItemReselectedListener)
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(tabItemSelectedListener)
        binding.bottomNavigation.setOnItemReselectedListener(tabItemReselectedListener)
    }
}