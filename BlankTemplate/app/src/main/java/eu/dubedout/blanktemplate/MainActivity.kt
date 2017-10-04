package eu.dubedout.blanktemplate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_badge_section.view.itemBadgeSection

import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headerEpoxyControler = HeaderEpoxyControler()
        activityMainRecycler.adapter = headerEpoxyControler.adapter
        headerEpoxyControler.requestModelBuild()

        val spanCount = 3
        val layoutManager = GridLayoutManager(this, spanCount)
        headerEpoxyControler.spanCount = spanCount
        layoutManager.spanSizeLookup = headerEpoxyControler.spanSizeLookup
        activityMainRecycler.layoutManager = layoutManager

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
    @EpoxyAttribute var subTitle: String = "badgeName"

    override fun bind(view: TextView?) {
        view?.text = subTitle
    }
}

@EpoxyModelClass
abstract class BadgeSectionModel: EpoxyModelWithHolder<BadgeSectionModel.Holder>(){
    @EpoxyAttribute var title: String = "subTitle"

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


