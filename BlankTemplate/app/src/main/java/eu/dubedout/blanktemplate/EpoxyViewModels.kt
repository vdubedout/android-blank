package eu.dubedout.blanktemplate

import android.databinding.Bindable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.airbnb.epoxy.*
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_list_data.view.*

@EpoxyModelClass(layout = R.layout.item_list_loading_more)
abstract class LoadingMoreViewModel : EpoxyModel<ViewGroup>(){
    val loadingMoreObservable: PublishSubject<Boolean> = PublishSubject.create()

    override fun bind(view: ViewGroup?) {
        super.bind(view)
        loadingMoreObservable.onNext(true)
    }
}