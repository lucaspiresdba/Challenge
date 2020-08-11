package br.com.lucaspires.challengexp.presenter.details

import br.com.lucaspires.domain.model.CharacterModel

interface CharacterDetailsActivityPresenter {
    fun getComics(characterId:Int, offset:Int)
    fun getSeries(characterId:Int, offset:Int)
    fun unsubscribe()
    fun saveFav(characterModel: CharacterModel)
}