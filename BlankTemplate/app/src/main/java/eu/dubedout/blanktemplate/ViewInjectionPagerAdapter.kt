package eu.dubedout.blanktemplate

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import java.util.*

class ViewInjectionPagerAdapter(vararg customView: Lazy<View>) : PagerAdapter() {

    var viewList: MutableList<Lazy<View>> = ArrayList()

    init {
        viewList.addAll(Arrays.asList(*customView))
    }

    override fun getCount(): Int = viewList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val customView = viewList[position].value
        container.addView(customView)
        return customView
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        if (container != null && `object` != null) {
            container.removeView(`object` as View?)
        }
    }
}