package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.domain.model.CharacterModel

interface CharacterFragmentPresenter {
    fun getCharacters(offset: Int, name: String)
    fun onStop()
    fun saveToFavorite(characterModel: CharacterModel)
}
