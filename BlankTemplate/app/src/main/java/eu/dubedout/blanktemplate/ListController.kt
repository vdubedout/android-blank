package eu.dubedout.blanktemplate

import com.airbnb.epoxy.EpoxyController
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject

class ListController : EpoxyController() {
    private var itemList: List<Item> = listOf()
    private var canLoadMore: Boolean = true
    private var disposables = CompositeDisposable()
    private val shouldLoadMoreSubject = PublishSubject.create<Boolean>()

    val shouldLoadMoreObservable: Observable<Boolean>
        get() = shouldLoadMoreSubject


    override fun buildModels() {
        disposables.dispose()
        disposables = CompositeDisposable()

        itemList.forEach {
            colorView {
                id(it.title)
                title(it.title)
                color(it.color)
            }
        }

        if (canLoadMore){
            val loadingMoreModel = LoadingMoreViewModel_()
            loadingMoreModel.id("LoadingMore"+itemList.size).addTo(this)
            loadingMoreModel.spanSizeOverride { totalSpanCount, position, itemCount -> totalSpanCount }
            disposables += loadingMoreModel
                    .loadingMoreObservable
                    .subscribe { shouldLoadMoreSubject.onNext(true) }
        }
    }

    fun setItems(itemList: List<Item>, canLoadMore: Boolean) {
        this.itemList = itemList
        this.canLoadMore = canLoadMore
        requestModelBuild()
    }
}