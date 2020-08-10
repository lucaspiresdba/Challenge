package br.com.lucaspires.challengexp.iu.fragment

import br.com.lucaspires.domain.model.CharacterModel

interface AdapterCharacterInterface {
    fun saveToFavorite(characterModel: CharacterModel)
}
