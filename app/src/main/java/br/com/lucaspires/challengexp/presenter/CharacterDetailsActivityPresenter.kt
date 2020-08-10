package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.domain.model.CharacterModel

interface CharacterDetailsActivityPresenter {
    fun getComics(characterId:Int, offset:Int)
    fun getSeries(characterId:Int, offset:Int)
    fun unsub()
    fun saveFav(characterModel: CharacterModel)
}