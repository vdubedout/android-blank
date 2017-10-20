package eu.dubedout.blanktemplate

import android.graphics.Color
import com.airbnb.epoxy.EpoxyController
import java.util.*

class ListController : EpoxyController() {
    private var itemList: List<Item> = listOf()
    private var canLoadMore: Boolean = true

    override fun buildModels() {
        itemList.forEach {
            colorView {
                id(it.title)
                title(it.title)
                color(it.color)
            }
        }
    }

    fun setItems(itemList: List<Item>, canLoadMore: Boolean) {
        this.itemList = itemList
        this.canLoadMore = canLoadMore
        requestModelBuild()
    }
}