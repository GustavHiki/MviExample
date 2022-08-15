package ru.geckolab.mviexample.host

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.terrakok.cicerone.Navigator
import dagger.hilt.android.AndroidEntryPoint
import ru.geckolab.mviexample.LaunchActivity
import ru.geckolab.mviexample.R
import ru.geckolab.mviexample.commons_android.base.BaseFragment
import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.commons_android.utils.delegates.args
import ru.geckolab.mviexample.navigation.child.ChildFragmentNavigator
import ru.geckolab.mviexample.navigation.child.ChildScreenRouters
import ru.geckolab.mviexample.navigation.root.RootTabsFragmentRouter
import javax.inject.Inject

@AndroidEntryPoint
class HostFragment : BaseFragment(R.layout.fragment_host) {
    companion object {
        fun newInstance(rootPage: RootPage) =
            HostFragment().apply { this.rootScreen = rootPage }
    }

    @Inject
    lateinit var childScreenRouters: ChildScreenRouters

    @Inject
    lateinit var rootTabRouters: RootTabsFragmentRouter

    @Inject
    lateinit var viewModelFactory: AssistedFactory.HomeHostFactory

    private val viewModel: HostViewModel by viewModels {
        AssistedFactory.homeHostFactory(viewModelFactory, rootScreen)
    }

    private var rootScreen by args<RootPage>()

    private val navigator: Navigator by lazy {
        ChildFragmentNavigator(rootTabRouters, requireActivity(), R.id.host_container, childFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as LaunchActivity).selectRootPage(rootScreen)
    }

    override fun onResume() {
        super.onResume()
        childScreenRouters.navigatorHolder(rootScreen).setNavigator(navigator)
    }

    override fun onPause() {
        childScreenRouters.navigatorHolder(rootScreen).removeNavigator()
        super.onPause()
    }
}