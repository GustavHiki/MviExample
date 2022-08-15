package ru.geckolab.mviexample.home

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import ru.geckolab.mviexample.commons_android.base.BaseFragment
import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.commons_android.utils.viewBinding
import ru.geckolab.mviexample.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment: BaseFragment(R.layout.fragment_home) {

    companion object {
        fun newInstance(rootPage: RootPage) = HomeFragment()
    }

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}