package com.buzz.bookandroid.common.arch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.buzz.bookandroid.common.arch.viewmodel.BaseViewModel

/**
 * MVI - BaseFragment
 * */
internal abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel<*, *, *>>(
    val viewBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    protected abstract val viewModel: VM

    private var binding: VB? = null
        get() = field ?: throw IllegalStateException("Not allowed to be called after 'onDestroyView'")

    protected open val onBackPressedHandler: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = viewBinding(inflater, container, false)
        return binding?.run {
            initRenderers(this)
            root
        }
    }

    abstract fun initRenderers(binding: VB)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStateOrEvent(viewModel)
        bindingLifecycle(viewLifecycleOwner.lifecycle)
    }

    abstract fun observeStateOrEvent(viewModel: VM)

    private fun bindingLifecycle(lifecycle: Lifecycle) {
        // BaseRenderer must be public, `getFields()` improve performance
        for (field in this::class.java.fields) {
            val value = field.get(this)
            if (value is BaseRenderer) {
                lifecycle.addObserver(value)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
