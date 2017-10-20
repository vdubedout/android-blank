package eu.dubedout.blanktemplate

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.airbnb.epoxy.*
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_list_data.view.*

@EpoxyModelClass(layout = R.layout.item_list_data)
abstract class ColorViewModel : EpoxyModelWithHolder<ColorViewModel.Holder>(){

    @EpoxyAttribute var title = ""
    @EpoxyAttribute var color = 0

    override fun bind(holder: Holder) {
        holder.container.setBackgroundColor(color)
        holder.title.text = title
    }

    class Holder : EpoxyHolder(){
        lateinit var container: ViewGroup
        lateinit var title: TextView

        override fun bindView(itemView: View) {
            container = itemView as ViewGroup
            title = itemView.itemListTitle
        }
    }
}


@EpoxyModelClass(layout = R.layout.item_list_loading_more)
abstract class LoadingMoreViewModel : EpoxyModel<ViewGroup>(){
    val loadingMoreObservable: PublishSubject<Boolean> = PublishSubject.create()

    override fun bind(view: ViewGroup?) {
        super.bind(view)
        loadingMoreObservable.onNext(true)
    }
}