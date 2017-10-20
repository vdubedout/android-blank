package eu.dubedout.blanktemplate

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_list.view.*

@EpoxyModelClass(layout = R.layout.item_list)
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