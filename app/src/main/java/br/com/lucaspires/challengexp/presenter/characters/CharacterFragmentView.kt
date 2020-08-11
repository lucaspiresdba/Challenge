package br.com.lucaspires.challengexp.presenter.characters

import br.com.lucaspires.domain.model.CharacterModel

interface CharacterFragmentView {
    fun sendData(list: List<CharacterModel>?)
    fun hideBottomLoading()
    fun noConnection()
    fun error()
    fun hideLoading()
    fun showLoading()
    fun showBottomLoading()
    fun saveSuccess()
    fun saveError()
}