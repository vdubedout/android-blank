package eu.dubedout.blanktemplate

data class MainActivityViewState
constructor(
        val itemList: List<Item>,
        val lastPageFetched: Int,
        val canLoadMore: Boolean
)

data class Item constructor(val title: String, val color: Int)
