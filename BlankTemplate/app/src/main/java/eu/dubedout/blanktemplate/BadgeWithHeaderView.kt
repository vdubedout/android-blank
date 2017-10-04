package eu.dubedout.blanktemplate

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.*
import kotlinx.android.synthetic.main.badge_section_list.view.*
import kotlinx.android.synthetic.main.item_badge_section.view.*
import java.util.*

class BadgeWithHeaderView(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.badge_section_list, this, true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val headerEpoxyControler = HeaderEpoxyControler()
        badgeSectionListView.adapter = headerEpoxyControler.adapter

        val spanCount = 3
        val layoutManager = GridLayoutManager(context, spanCount)
        headerEpoxyControler.spanCount = spanCount
        layoutManager.spanSizeLookup = headerEpoxyControler.spanSizeLookup
        badgeSectionListView.layoutManager = layoutManager

        headerEpoxyControler.badgeSectionList = getTestList()
    }

    private fun getTestList(): List<BadgeSection> = listOf(
            BadgeSection(title = "header1", items = listOf("item1", "item2", "item3")),
            BadgeSection(title = "header2", items = listOf("item1", "item2", "item3", "item4")),
            BadgeSection(title = "header3", items = listOf("item1", "item2")),
            BadgeSection(title = "header4", items = listOf("item1", "item2", "item3")))
}

class HeaderEpoxyControler : EpoxyController() {
    var badgeSectionList: List<BadgeSection> = Collections.emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        for (badgeSection in badgeSectionList){
            BadgeSectionModel_()
                    .id(badgeSection.title)
                    .title(badgeSection.title)
                    .spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                    .addTo(this)
            for (badge in badgeSection.items){
                BadgeModel_()
                        .id(badgeSection.title, badge)
                        .subTitle(badge)
                        .addTo(this)
            }
        }
    }
}

data class BadgeSection(val title: String, val items: List<String>)

@EpoxyModelClass(layout = R.layout.item_badge)
abstract class BadgeModel : EpoxyModel<TextView>() {
    @EpoxyAttribute
    var subTitle: String = "badgeName"

    override fun bind(view: TextView?) {
        view?.text = subTitle
    }
}

@EpoxyModelClass
abstract class BadgeSectionModel: EpoxyModelWithHolder<BadgeSectionModel.Holder>(){
    @EpoxyAttribute
    var title: String = "subTitle"

    override fun bind(view: Holder?) {
        view?.title?.text = title
    }

    override fun getDefaultLayout(): Int = R.layout.item_badge_section

    override fun createNewHolder(): Holder = Holder()

    class Holder: EpoxyHolder() {
        var title: TextView? = null

        override fun bindView(itemView: View?) {
            title = itemView?.itemBadgeSection
        }

    }
}
