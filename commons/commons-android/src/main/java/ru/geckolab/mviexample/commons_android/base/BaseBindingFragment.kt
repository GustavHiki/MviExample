package ru.geckolab.mviexample.commons_android.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.onEach
import ru.geckolab.mviexample.commons_android.utils.viewBinding
import ru.geckolab.mviexample.mvi_core.MviEvent

abstract class BaseBindingFragment<VB : ViewBinding, VM : BaseMviViewModel<MviEvent>>(
    viewBindingFactory: (View) -> VB,
    @LayoutRes private val layoutRes: Int
) : BaseFragment(layoutRes) {

    val binding by viewBinding(viewBindingFactory)


    private val _viewModel: BaseMviViewModel<MviEvent> by viewModels(
        factoryProducer = { getFactoryProducer() ?: defaultViewModelProviderFactory }
    )
    val viewModel: VM
        get() = _viewModel as VM

    open fun getFactoryProducer(): ViewModelProvider.Factory? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOnState()
        observeOnAction()
    }

    private fun observeOnState() {
        viewModel.state
            .onEach { state -> state.render(binding) }
            .launchWhenStarted()
    }

    private fun observeOnAction() {
        viewModel.action
            .onEach { (action, event) -> action.render(binding, event) }
            .launchWhenStarted()
    }

}