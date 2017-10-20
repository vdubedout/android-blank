package eu.dubedout.blanktemplate

import io.reactivex.Observable

interface Presenter {
    val viewStateObservable: Observable<MainActivityViewState>
    fun loadMore()
}