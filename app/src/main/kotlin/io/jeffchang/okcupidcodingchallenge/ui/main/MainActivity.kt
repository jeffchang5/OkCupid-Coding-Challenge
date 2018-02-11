package io.jeffchang.okcupidcodingchallenge.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.widget.LinearLayout
import dagger.android.support.DaggerAppCompatActivity
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.ui.main.MainActivity.MatchFragmentPagerAdapter.FragmentState
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchFragment
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.SpecialBlendFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup



class MainActivity : DaggerAppCompatActivity(),
        MatchFragment.OnCardClickedListener,
        SpecialBlendFragment.OnCardClickedListener {

    private var matchFragmentPagerAdapter: MatchFragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f
        title = getString(R.string.search_title)
        setupTabLayout()
        setupViewPager()
    }

    private fun setupTabLayout() {
        activity_main_tab_layout.addTab(
                activity_main_tab_layout
                        .newTab()
                        .setText(resources.getString(R.string.special_blend)))
        activity_main_tab_layout.addTab(
                activity_main_tab_layout.newTab()
                .setText(resources.getString(R.string.match_percent)))
        activity_main_tab_layout.setupWithViewPager(activity_main_viewpager)
    }

    private fun setupViewPager() {
        matchFragmentPagerAdapter = MatchFragmentPagerAdapter(supportFragmentManager)
        activity_main_viewpager.adapter = matchFragmentPagerAdapter
    }

    fun disableViewPager(disable: Boolean) {
        activity_main_viewpager.isDisabled = disable
        val tabStrip = activity_main_tab_layout.getChildAt(0) as LinearLayout
        tabStrip.isEnabled = !disable
        for (i in 0 until tabStrip.childCount) {
            tabStrip.getChildAt(i).isClickable = !disable
        }
    }

    override fun onFromSpecialBlendFragmentToggleLike(match: Match, isLiked: Boolean) {
        val matchFragment =
                matchFragmentPagerAdapter?.getFragment(FragmentState.MATCH) as MatchFragment
        if (isLiked) {
            matchFragment.addMatchToAdapter(match)
        } else {
            matchFragment.removeMatchToAdapter(match)
        }
    }

    override fun onFromSpecialBlendFragmentRemoveLike(match: Match) {

    }

    override fun onFromMatchFragmentRemoveLike(match: Match) {
        val specialBlendFragment =
                matchFragmentPagerAdapter?.getFragment(FragmentState.SPECIAL_BLEND) as SpecialBlendFragment
        specialBlendFragment.removeLikeFromMatchList(match)
    }

    class MatchFragmentPagerAdapter(fragmentManager: FragmentManager)
        : FragmentPagerAdapter(fragmentManager) {

        private val frags = arrayOfNulls<Fragment>(2)

        fun getFragment(state: FragmentState): Fragment? {
            return if (state == FragmentState.SPECIAL_BLEND)
                frags[0]
            else frags[1]
        }

        init {
            frags[0] = SpecialBlendFragment.newInstance()
            frags[1] = MatchFragment.newInstance()
        }

        enum class FragmentState(val title: String) {
            SPECIAL_BLEND("Special Blend"),
            MATCH("Match %")
        }

        override fun getItem(position: Int): Fragment {
            return frags[position]!!
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            frags[position] = super.instantiateItem(container, position) as Fragment
            return frags[position]!!
        }
        override fun getPageTitle(position: Int): CharSequence?
                = FragmentState.values()[position].title

        override fun getCount(): Int = FragmentState.values().size
    }
}
