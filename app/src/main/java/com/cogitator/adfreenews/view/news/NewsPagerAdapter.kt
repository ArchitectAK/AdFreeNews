package com.cogitator.adfreenews.view.news

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import com.cogitator.adfreenews.view.article.ArticleFragment

/**
 * @author Ankit Kumar on 14/09/2018
 */

class NewsPagerAdapter(fm: FragmentManager, private val newsCategoryList: List<String>) : FragmentStatePagerAdapter(fm) {
    //    var newsCategoryList = ArrayList<String>()
    var registeredFragments = SparseArray<Fragment>()


    override fun getItem(position: Int): Fragment {
        return ArticleFragment.newInstance(newsCategoryList[position])
    }

    override fun getCount(): Int {
        try {
            return newsCategoryList.size
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0
    }

    override fun getPageTitle(position: Int): CharSequence = newsCategoryList[position]


    override fun destroyItem(container: ViewGroup, position: Int, objectt: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, objectt)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments.get(position)
    }
}