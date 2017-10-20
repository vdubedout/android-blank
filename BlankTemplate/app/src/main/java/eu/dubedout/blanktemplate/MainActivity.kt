package eu.dubedout.blanktemplate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.activityMainRecycler as recycler

class MainActivity : AppCompatActivity() {
    private lateinit var presenter: Presenter
    private lateinit var disposables: CompositeDisposable
    private lateinit var controller: ListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = PresenterImpl()

        setUpList()
    }

    private fun setUpList() {
        controller = ListController()
        recycler.adapter = controller.adapter
        val spanCount = 3
        val layoutManager = GridLayoutManager(this, spanCount)
        controller.spanCount = spanCount
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        recycler.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        disposables = CompositeDisposable()

        presenter.loadMore()
        disposables += handleListItems()
    }

    private fun handleListItems(): Disposable =
        presenter.viewStateObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ controller.setItems(it.itemList, it.canLoadMore)}


    override fun onPause() {
        super.onPause()

        disposables.dispose()
    }


}
