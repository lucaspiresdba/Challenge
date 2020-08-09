package br.com.lucaspires.challengexp.presenter

interface CharacterFragmentPresenter {
    fun getCharacters(offset: Int, name: String)
    fun onStop()
}
