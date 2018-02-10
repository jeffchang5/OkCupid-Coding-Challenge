package io.jeffchang.okcupidcodingchallenge.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import dagger.android.support.DaggerAppCompatActivity
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.SpecialBlendFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.LinearLayout
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchFragment

class MainActivity : DaggerAppCompatActivity(),
        MatchFragment.OnCardClickedListener,
        SpecialBlendFragment.OnCardClickedListener {

    private val matchFragmentPagerAdapter by lazy {
        MatchFragmentPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f
        title = getString(R.string.search_title)
        setupTabLayout()
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

    override fun onFromSpecialBlendFragmentAddLike(match: Match, isLiked: Boolean) {
        if (isLiked) {
            matchFragmentPagerAdapter.matchFragment.addMatchToAdapter(match)
        }
        else matchFragmentPagerAdapter.matchFragment.removeMatchToAdapter(match)
    }

    override fun onFromSpecialBlendFragmentRemoveLike(match: Match) {

    }

    override fun onFromMatchFragmentRemoveLike() {

    }

    class MatchFragmentPagerAdapter(fragmentManager: FragmentManager)
        : FragmentPagerAdapter(fragmentManager) {

        val specialBlendFragment by lazy {
            SpecialBlendFragment.newInstance()
        }

        val matchFragment by lazy {
            MatchFragment.newInstance()
        }

        enum class FragmentState(val title: String) {
            SPECIAL_BLEND("Special Blend"),
            MATCH("Match %")
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> specialBlendFragment
                1 -> matchFragment
                else -> Fragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence?
                = FragmentState.values()[position].title

        override fun getCount(): Int = FragmentState.values().size
    }
}
