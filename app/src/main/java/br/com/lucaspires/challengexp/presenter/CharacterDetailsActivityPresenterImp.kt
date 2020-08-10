package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.domain.model.CharacterModel
import br.com.lucaspires.domain.usecase.CharacterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterDetailsActivityPresenterImp(
    private val useCase: CharacterUseCase,
    private val view: CharacterDetailsActivityView
) : CharacterDetailsActivityPresenter {

    private val disposable = CompositeDisposable()

    override fun getComics(characterId: Int, offset:Int) {
        disposable.add(
            useCase.getComicsOfCharacters(characterId, offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({

                    it?.let { view.populateComics(it) }
                },
                    {
                        it.printStackTrace()
                    })
        )
    }

    override fun getSeries(characterId: Int, offset:Int) {
        disposable.add(
            useCase.getSeriesOfCharacters(characterId, offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it?.let { view.populateSeries(it) }
                },
                    {
                        it.printStackTrace()
                    })
        )
    }

    override fun unsub() {
        disposable.dispose()
    }

    override fun saveFav(characterModel: CharacterModel) {
        disposable.add(
            useCase.favoriteCharacter(characterModel)
                .subscribe({
                    view.saveSuccess()
                }, {
                    view.saveError()
                    it.printStackTrace()
                })
        )
    }
}