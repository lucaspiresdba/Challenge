package br.com.lucaspires.challengexp.presenter.details

import br.com.lucaspires.domain.model.ItemContentModel

interface CharacterDetailsActivityView {
    fun populateComics(comics: List<ItemContentModel>)
    fun populateSeries(series: List<ItemContentModel>)
    fun saveSuccess()
    fun saveError()
}