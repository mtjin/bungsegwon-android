package com.mtjin.bungsegwon.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtjin.bungsegwon.utils.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()


    private var _backClick = SingleLiveEvent<Unit>()
    val backClick: LiveData<Unit> get() = _backClick
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _isLottieLoading = MutableLiveData<Boolean>(false)
    val isLottieLoading: LiveData<Boolean> get() = _isLottieLoading


    // 일반적인 네트워크 통신시 사용할 base 통신 함수
    protected fun <T : Any> excute(
        single: Single<T>,
        res: (T) -> Unit,
        isShowLoad: Boolean = true
    ) {
        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { if (isShowLoad) _isLoading.value = true }
            .doAfterTerminate { if (isShowLoad) _isLoading.value = false }
            .subscribeBy(onSuccess = {
                res(it)
            }, onError = {
                Log.e(TAG, it.message.toString())
            })
            .addTo(compositeDisposable)
    }

    protected fun <T : Any> excute(
        flowable: Flowable<T>,
        res: (T) -> Unit,
        isShowLoad: Boolean = true
    ) {
        flowable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { if (isShowLoad) _isLoading.value = true }
            .doAfterTerminate { if (isShowLoad) _isLoading.value = false }
            .subscribeBy(onNext = {
                res(it)
            }, onComplete = {
                Log.d(TAG, "onComplete")
            }, onError = {
                Log.e(TAG, it.message.toString())
            })
            .addTo(compositeDisposable)
    }

    protected fun excute(
        completable: Completable,
        res: (Boolean) -> Unit,
        isShowLoad: Boolean = true
    ) {
        completable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { if (isShowLoad) _isLoading.value = true }
            .doAfterTerminate { if (isShowLoad) _isLoading.value = false }
            .subscribeBy(onComplete = {
                res(true)
            }, onError = {
                Log.e(TAG, it.message.toString())
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }

    fun showLottieProgress() {
        _isLottieLoading.value = true
    }

    fun hideLottieProgress() {
        _isLottieLoading.value = false
    }

    fun onBackClick() {
        _backClick.call()
    }

    companion object {
        const val TAG = "BaseViewModel"
    }

}