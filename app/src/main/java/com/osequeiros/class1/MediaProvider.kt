package com.osequeiros.class1

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by osequeiros on 8/27/17.
 */

object MediaProvider {

    private val thumbBase = "http://lorempixel.com/400/400/dogs/"

    /*val data = (1..10).map { MediaItem("Title $it", "$thumbBase$it",
            if (it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO) }*/

    private var data = emptyList<MediaItem>()

    fun dataAsync(dataType: String, callback : (List<MediaItem>) -> Unit) {
        doAsync {
            if (data.isEmpty()) {
                data = dataSync(dataType)
            }
            uiThread {
                callback(data)
            }
        }
    }

    fun dataSync(dataType: String) : List<MediaItem> {
        Thread.sleep(2000)
        return (1..10).map {
            MediaItem(it, "Title $it", "$thumbBase/$dataType/$it",
                    if (it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO)
        }
    }
}

/*fun getMedia2() = listOf(
        MediaItem("Title 1", "${thumbBase}1", MediaItem.Type.PHOTO),
        MediaItem("Title 2", "${thumbBase}2", MediaItem.Type.PHOTO),
        MediaItem("Title 3", "${thumbBase}3", MediaItem.Type.VIDEO),
        MediaItem("Title 4", "${thumbBase}4", MediaItem.Type.PHOTO),
        MediaItem("Title 5", "${thumbBase}5", MediaItem.Type.PHOTO),
        MediaItem("Title 6", "${thumbBase}6", MediaItem.Type.VIDEO),
        MediaItem("Title 7", "${thumbBase}7", MediaItem.Type.VIDEO),
        MediaItem("Title 8", "${thumbBase}8", MediaItem.Type.PHOTO),
        MediaItem("Title 9", "${thumbBase}9", MediaItem.Type.PHOTO),
        MediaItem("Title 10", "${thumbBase}10", MediaItem.Type.VIDEO))*/

/*fun getMedia() = (1..10).map { MediaItem("Title $it", "$thumbBase$it",
        if (it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO) }*/
