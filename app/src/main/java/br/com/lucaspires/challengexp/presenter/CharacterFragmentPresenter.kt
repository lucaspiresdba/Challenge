package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.challengexp.RequestState
import br.com.lucaspires.domain.usecase.CharacterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterFragmentPresenter(
    private val characterUseCase: CharacterUseCase,
    private val view: CharacterFragmentView
) {

    private val disposable = CompositeDisposable()

    fun getCharacters(offset: Int, name:String) {
        var checkName:String? = null
        if(name.trim().isNotEmpty()){
            checkName = name
        }
        disposable.add(
            characterUseCase
                .getAllCharacters(offset, checkName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe({
                    if (!it.characters.isNullOrEmpty()) {
                        view.sendData(it.characters)
                    } else {
                        view.requestResult(RequestState.EmptyList)
                    }
                }, {
                    view.requestResult(RequestState.Error)
                })
        )
    }

    fun onStop() {
        disposable.clear()
    }

}