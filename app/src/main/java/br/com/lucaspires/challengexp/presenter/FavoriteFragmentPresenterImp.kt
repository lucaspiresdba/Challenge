package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.domain.usecase.CharacterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class FavoriteFragmentPresenterImp(
    private val characterUseCase: CharacterUseCase,
    private val view: FavoriteFragmentView
) : FavoriteFragmentPresenter {

    private val disposable = CompositeDisposable()

    override fun getFavorites() {
        disposable.add(
            characterUseCase
                .getFavorites()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.sendData(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun unsub() {
        disposable.clear()
    }
}