package ru.geckolab.mviexample.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ru.geckolab.mviexample.commons_android.base.BaseBindingFragment
import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.commons_android.utils.delegates.args
import ru.geckolab.mviexample.home.databinding.FragmentHomeBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {

    companion object {
        fun newInstance(rootPage: RootPage) = HomeFragment().apply {
            this.rootPage = rootPage
        }
    }

    private var rootPage by args<RootPage>()

    @Inject
    lateinit var viewModelFactory: AssistedFactory.HomeFactory

    override fun getFactoryProducer(): ViewModelProvider.Factory =
        AssistedFactory.homeFactory(viewModelFactory, rootPage)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() = with(binding) {
        tvTitle.setOnClickListener { viewModel.reduce(HomeEvent.LoadPapers) }
        snackbarAction.setOnClickListener { viewModel.reduce(HomeEvent.SnackBarClick("blyaaaaaa")) }
    }

}