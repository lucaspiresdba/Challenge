package br.com.lucaspires.challengexp.presenter.details

import android.util.Log
import br.com.lucaspires.challengexp.presenter.BasePresenter
import br.com.lucaspires.domain.model.CharacterModel
import br.com.lucaspires.domain.usecase.CharacterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterDetailsActivityPresenterImp(
    private val useCase: CharacterUseCase,
    private val view: CharacterDetailsActivityView
) : BasePresenter(),
    CharacterDetailsActivityPresenter {

    override fun getComics(characterId: Int, offset: Int) {

            useCase.getComicsOfCharacters(characterId, offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it?.let { view.populateComics(it) }
                },
                    {
                        it.printStackTrace()
                    }).also { disp.add(it) }

    }

    override fun getSeries(characterId: Int, offset: Int) {

            useCase.getSeriesOfCharacters(characterId, offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it?.let { view.populateSeries(it) }
                },
                    {
                        Log.e("aaa", it.toString())
                    }).also { disp.add(it) }

    }

    override fun unsubscribe() {
        unSub()
    }

    override fun saveFav(characterModel: CharacterModel) {

            useCase.favoriteCharacter(characterModel)
                .subscribe({
                    view.saveSuccess()
                }, {
                    view.saveError()
                    it.printStackTrace()
                }).also { disp.add(it) }
    }
}