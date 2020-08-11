package br.com.lucaspires.challengexp.presenter.favorites

import br.com.lucaspires.challengexp.presenter.BasePresenter
import br.com.lucaspires.domain.usecase.CharacterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers

class FavoriteFragmentPresenterImp(
    private val characterUseCase: CharacterUseCase,
    private val view: FavoriteFragmentView
) : BasePresenter(),
    FavoriteFragmentPresenter {

    override fun getFavorites() {
        characterUseCase
            .getFavorites()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.sendData(it)
            }, {
                it.printStackTrace()
            }).also { disp.add(it) }
    }

    override fun unsubscribe() {
        unSub()
    }
}