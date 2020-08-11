package br.com.lucaspires.challengexp.presenter.characters

import br.com.lucaspires.domain.model.CharacterModel

interface CharacterFragmentPresenter {
    fun getCharacters(offset: Int, name: String)
    fun unsubscribe()
    fun saveToFavorite(characterModel: CharacterModel)
}
