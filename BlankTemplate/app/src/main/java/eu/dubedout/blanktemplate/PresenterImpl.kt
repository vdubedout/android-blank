package eu.dubedout.blanktemplate

import android.graphics.Color
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class PresenterImpl : Presenter {
    private val testingItemList = TestingItems(itemByPage = 20, maxItems = 300)
    private val viewStateSubject = BehaviorSubject.create<MainActivityViewState>()

    override val viewStateObservable: Observable<MainActivityViewState>
        get() = viewStateSubject

    override fun loadMore() {
        if (viewStateSubject.hasValue()){
            viewStateObservable
                    .firstElement()
                    .map { loadItems(it.lastPageFetched + 1, it.itemList) }
                    .subscribeOn(Schedulers.io())
                    .subscribe{ viewStateSubject.onNext(it) }
        } else {
            Observable.fromCallable { loadItems(0, listOf()) }
                    .subscribeOn(Schedulers.io())
                    .subscribe{viewStateSubject.onNext(it)}
        }
    }

    private fun loadItems(pageNumber: Int, itemList: List<Item>): MainActivityViewState {
        val page = testingItemList.get(pageNumber)
        val newList = itemList.toMutableList()
        newList.addAll(page.itemList)

        return MainActivityViewState(
                itemList = newList,
                canLoadMore = page.canLoadMore,
                lastPageFetched = pageNumber
        )
    }
}

class TestingItems(val itemByPage: Int = 20, val maxItems: Int = 300) {
    private val random = Random()
    private val itemList: Lazy<List<Item>> = loadItems()
    private val pageNumbers: Int = maxItems / itemByPage

    private fun loadItems(): Lazy<List<Item>> {
        val mutableList = mutableListOf<Item>()

        for (index in 0..maxItems){
            mutableList += newItem(index)
        }

        return lazyOf(mutableList.toList())
    }

    private fun newItem(index: Int): Item {
        val red = random.nextInt(256)
        val green= random.nextInt(256)
        val blue = random.nextInt(256)
        val color = Color.argb(190, red, green, blue)
        return Item(">>>> ${index} <<<<", color)
    }

    fun get(page: Int): Page {
        val fromIdex = page * itemByPage
        val toIndex = page * itemByPage + itemByPage

        return Page(pageNumber= page,
                canLoadMore = page < pageNumbers,
                itemList = itemList.value.subList(fromIdex, toIndex))
    }


}

data class Page(val pageNumber: Int, val canLoadMore: Boolean, val itemList: List<Item>)