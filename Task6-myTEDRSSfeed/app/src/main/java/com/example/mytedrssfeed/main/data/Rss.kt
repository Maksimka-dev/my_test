package com.example.mytedrssfeed.main.data

import org.simpleframework.xml.*

@Root(strict = false, name = "rss")
data class Rss  @JvmOverloads constructor(
    @field: Element(name = "channel", type = Channel::class)
    var channel: Channel? = null,
    @field: Attribute(name = "version")
    var version: String? = null
)

@Root(name = "channel", strict = false)
data class Channel  @JvmOverloads constructor(
    @field: Element(name = "image", type = Image::class, required = false)
    var image: Image? = null,
    @field: ElementList(name = "item", inline = true)
    var itemList: List<Item> = arrayListOf(),
    @field: Element(name = "docs")
    var docs: String? = null,
    @field: Element(name = "lastBuildDate")
    var lastBuildDate: String? = null,
    @field: Element(name = "link")
    var link: String? = null,
    @field: Element(name = "description", data = true)
    var description: String? = null,
    @field: Element(name = "language")
    var language: String? = null,
    @field: Element(name = "title")
    var title: String? = null,
    @field: Element(name = "managingEditor")
    var managingEditor: String? = null,
    @field: Element(name = "pubDate")
    var pubDate: String? = null,
    @field: Element(name = "webMaster")
    var webMaster: String? = null
)

@Root(name = "guid", strict = false)
data class Guid @JvmOverloads constructor(
    @field: Attribute(name = "isPermaLink", required = false)
    var isPermaLink: Boolean? = false,
    @field: Element(name = "content", required = false)
    var content: String? = null
)

@Root(name = "enclosure", strict = false)
data class Enclosure @JvmOverloads constructor(
    @field: Attribute(name = "length")
    var length: String? = null,
    @field: Attribute(name = "type")
    var type: String? = null,
    @field: Attribute(name = "url")
    var url: String? = null
)

@Root(name = "item", strict = false)
data class Item @JvmOverloads constructor(
    @field: Element(name = "enclosure", type = Enclosure::class, required = false)
    var enclosure: Enclosure? = null,
    @field: Element(name = "link", required = false)
    var link: String? = null,
    @field: Element(name = "description", required = false, data = true)
    var description: String? = null,
    @field: Element(name = "guid", type = Guid::class, required = false)
    var guid: Guid? = null,
    @field:Element(name = "title", required = false)
    var title: String? = null,
    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = null,
    @field:Element(name = "image", type = ItunesImage::class, required = false)
    var itunes_image: ItunesImage? = null,
    @field:Element(name = "group", type = MediaGroup::class, required = false)
    var group: MediaGroup? = null,
    @field:Element(name = "duration", required = false)
    var duration: String? = null
)

@Root(name = "image", strict = false)
data class Image @JvmOverloads constructor(
    @field:Element(name = "url", required = false)
    var url: String? = null,
    @field:Element(name = "title", required = false)
    var title: String? = null,
    @field:Element(name = "link", required = false)
    var link: String? = null
)

@Root(name = "image", strict = false)
data class ItunesImage @JvmOverloads constructor(
    @field:Attribute(name = "url", required = false)
    var url: String? = null
)

@Root(name = "group", strict = false)
data class MediaGroup @JvmOverloads constructor(
    @field: ElementList(name = "content ", inline = true)
    var contentList: List<Content> = arrayListOf()
)

@Root(name = "content", strict = false)
data class Content @JvmOverloads constructor(
    @field:Attribute(name = "url", required = false)
    var url: String? = null
)