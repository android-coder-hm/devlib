package com.adk.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val showLoadingLiveData = MutableLiveData<Boolean>()
    val toastLiveData = MutableLiveData<String>()

    fun wrapExecute(
        bloc: suspend CoroutineScope.() -> Unit,
        preCheckFun: (() -> Boolean)? = null,
        errorFun: ((e: Exception) -> Unit)? = null,
        showLoading: Boolean = true
    ) {

        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                preCheckFun?.let {
                    if (!it()) {
                        return@launch
                    }
                }
                changeLoadingStatus(showLoading, true)
                bloc()
                changeLoadingStatus(showLoading, false)
            } catch (e: Exception) {
                changeLoadingStatus(showLoading, false)
                toastLiveData.postValue(e.localizedMessage)
                errorFun?.invoke(e)
            }
        }
    }

    private fun changeLoadingStatus(needShowLoading: Boolean, isShow: Boolean) {
        if (needShowLoading) {
            showLoadingLiveData.postValue(isShow)
        }
    }
}