package com.example.mytedrssfeed.main.data


data class DataTEDXML(val rss: Rss)

data class Rss(val channel: Channel?, val version: String?)

data class Channel(
    val image: Image?,
    val item: ArrayList<Item>,
    val docs: String?,
    val lastBuildDate: String?,
    val link: String?,
    val description: String?,
    val language: String?,
    val title: String?,
    val managingEditor: String?,
    val pubDate: String?,
    val webMaster: String?
)

data class Guid(val isPermaLink: Boolean?, val content: String)

data class Enclosure(val length: String?, val type: String?, val url: String?)

data class Item(
    val enclosure: Enclosure?,
    val link: String?,
    val description: String?,
    val guid: Guid?,
    val title: String?,
    val pubDate: String?
)

data class Image(
    var link: String?,
    var title: String?,
    var url: String?
)