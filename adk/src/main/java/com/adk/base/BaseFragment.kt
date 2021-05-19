package com.adk.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    val viewModel: T by lazy {
        val genericSuperclass = javaClass.genericSuperclass
        val parameterizedType = genericSuperclass as ParameterizedType
        val actualTypeArguments = parameterizedType.actualTypeArguments

        @Suppress("UNCHECKED_CAST")
        val viewModelClass: Class<T> = actualTypeArguments[0] as Class<T>
        ViewModelProvider(this).get(viewModelClass)
    }

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rootView = view
        initView()
        initDefaultObserver()
        initObserver()
    }

    private fun initDefaultObserver() {
        viewModel.showLoadingLiveData.observe(viewLifecycleOwner, {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        })
        viewModel.toastLiveData.observe(viewLifecycleOwner, {
            toastMsg(it)
        })
    }

    abstract fun getLayoutId(): Int

    open fun initView() {}

    open fun initObserver() {}

    open fun showLoading() {}

    open fun dismissLoading() {}

    open fun toastMsg(msg: String) {}
}