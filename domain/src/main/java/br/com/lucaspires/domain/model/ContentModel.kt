package br.com.lucaspires.domain.model

data class ContentModel(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val contentItems: List<ItemContentModel>?
)