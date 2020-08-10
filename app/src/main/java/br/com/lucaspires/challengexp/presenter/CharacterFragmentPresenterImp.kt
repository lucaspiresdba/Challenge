package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.data.CheckConnectionInterceptor.NoNetworkExpcetion
import br.com.lucaspires.domain.usecase.CharacterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterFragmentPresenterImp(
    private val characterUseCase: CharacterUseCase,
    private val view: CharacterFragmentView
) : CharacterFragmentPresenter {

    private val disposable = CompositeDisposable()

    override fun getCharacters(offset: Int, name: String) {
        var checkName: String? = null
        if (name.trim().isNotEmpty()) {
            checkName = name
        }
        disposable.add(
            characterUseCase
                .getAllCharacters(offset, checkName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    when (offset) {
                        0 -> view.showLoading()
                        else -> view.showBottomLoading()
                    }
                }
                .doFinally {
                    when (offset) {
                        0 -> view.hideLoading()
                        else -> view.hideBottomLoading()
                    }
                }
                .subscribe({
                    view.sendData(it.characters)

                }, {
                    when (it) {
                        is NoNetworkExpcetion -> {
                            view.noConnection()
                        }
                        else -> view.error()
                    }
                    it.printStackTrace()
                })
        )
    }

    override fun onStop() {
        disposable.dispose()
    }

}