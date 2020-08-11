package br.com.lucaspires.challengexp.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {
    protected val disp = CompositeDisposable()

    protected fun unSub() {
        disp.dispose()
    }
}