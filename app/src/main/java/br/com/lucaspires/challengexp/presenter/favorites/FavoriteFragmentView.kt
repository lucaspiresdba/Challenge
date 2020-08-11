package br.com.lucaspires.challengexp.presenter.favorites

import br.com.lucaspires.domain.model.CharacterModel

interface FavoriteFragmentView {
    fun sendData(listCharacters: List<CharacterModel>)
}