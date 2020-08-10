package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.domain.model.CharacterModel

interface FavoriteFragmentView {
    fun sendData(listCharacters: List<CharacterModel>)
}