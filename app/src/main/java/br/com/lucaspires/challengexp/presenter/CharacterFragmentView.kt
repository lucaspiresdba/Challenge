package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.challengexp.RequestState
import br.com.lucaspires.domain.model.CharacterModel

interface CharacterFragmentView {
    fun requestResult(result: RequestState)
    fun sendData(list: List<CharacterModel>?)
    fun hideLoading()
}