package com.adk.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * 基类activity
 */
abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    val viewModel: T by lazy {
        val genericSuperclass = javaClass.genericSuperclass
        val parameterizedType = genericSuperclass as ParameterizedType
        val actualTypeArguments = parameterizedType.actualTypeArguments

        @Suppress("UNCHECKED_CAST")
        val viewModelClass: Class<T> = actualTypeArguments[0] as Class<T>
        ViewModelProvider(this).get(viewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        parseIntent()
        initView()
        initObserver()
    }

    abstract fun getLayoutId(): Int

    open fun parseIntent() {}

    open fun initView() {}

    open fun initObserver() {}
}