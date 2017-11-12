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

        activityMainViewPager.adapter = ViewInjectionPagerAdapter(
                lazy {
                    val textView = TextView(this)
                    textView.text = "Swipe to next page"
                    textView
                },
                lazy { BadgeWithHeaderView(this) }
        )
    }
}

