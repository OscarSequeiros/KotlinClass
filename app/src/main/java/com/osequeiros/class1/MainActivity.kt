package com.osequeiros.class1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.UI
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), Logger {

    companion object {
        val extra: String = "EXTRA_PARAM_ID"
    }

    val adapter = MediaAdapter { navigateToDetail(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_lista.adapter = adapter

        loadFilterData(Filter.None)
        MediaProvider.dataAsync { data -> adapter.items = data }
    }

    override fun onCreateOptionsMenu(menu : Menu) : Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {

        val filter: Filter = when (item.itemId) {
            R.id.filter_videos -> Filter.ByType(MediaItem.Type.VIDEO)
            R.id.filter_photos -> Filter.ByType(MediaItem.Type.PHOTO)
            else -> Filter.None
        }

        loadFilterData(filter)

        return true
    }

    private fun loadFilterData(filter: Filter) {
        /*MediaProvider.dataAsync { media ->
            adapter.items = when (filter) {
                Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }*/
        async(kotlinx.coroutines.experimental.android.UI) {
            val cats = bg { MediaProvider.dataSync("cats") }
            val nature = bg { MediaProvider.dataSync("nature") }
            adapter.items = cats.await() + nature.await()
        }
    }


    private fun navigateToDetail(item: MediaItem) {
        startActivity<DetailActivity>(DetailActivity.ID to item.id)
    }
}

sealed class Filter {
    object None : Filter()
    class ByType(val type: MediaItem.Type) : Filter()
}

interface Logger {

    val tag : String
        get() = javaClass.simpleName

    fun d(message : String) = Log.d(tag, message)
}
