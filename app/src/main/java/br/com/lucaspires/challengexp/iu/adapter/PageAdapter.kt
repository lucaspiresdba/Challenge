package br.com.lucaspires.challengexp.iu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = ArrayList<Pair<Fragment, String>>()

    fun addFragment(pair: Pair<Fragment, String>) {
        fragmentList.add(pair)
    }

    override fun getItem(position: Int) = fragmentList[position].first

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = fragmentList[position].second
}