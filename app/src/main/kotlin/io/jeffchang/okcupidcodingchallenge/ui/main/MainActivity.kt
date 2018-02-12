package io.jeffchang.okcupidcodingchallenge.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.widget.LinearLayout
import dagger.android.support.DaggerAppCompatActivity
import io.jeffchang.okcupidcodingchallenge.R
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.ui.match.view.MatchFragment
import io.jeffchang.okcupidcodingchallenge.ui.specialblend.view.SpecialBlendFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup

/**
 * Creates the activity that hosts the fragments that our views and orchestrates communication
 * between them.
 */
class MainActivity : DaggerAppCompatActivity(),
        MatchFragment.OnCardClickedListener,
        SpecialBlendFragment.OnCardClickedListener {

    private val matchFragmentPagerAdapter: MatchFragmentPagerAdapter by lazy {
        MatchFragmentPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f
        title = getString(R.string.search_title)
        setupTabLayout()
        activity_main_viewpager.adapter = matchFragmentPagerAdapter
    }

    /**
     * Adds and sets up the view pager with tabs.
     */
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

    /**
     * Disables the viewpager when an error occurs
     */
    fun disableViewPager(disable: Boolean) {
        activity_main_viewpager.isDisabled = disable
        val tabStrip = activity_main_tab_layout.getChildAt(0) as LinearLayout
        tabStrip.isEnabled = !disable
        for (i in 0 until tabStrip.childCount) {
            tabStrip.getChildAt(i).isClickable = !disable
        }
    }


    fun clearAllMatches() {
        matchFragmentPagerAdapter.specialBlendFragment.clearAllMatches()
    }

    /**
     * Method called from Special Blend Fragment which toggles when a card is liked or not.
     */
    override fun onFromSpecialBlendFragmentToggleLike(match: Match, isLiked: Boolean) {
        if (isLiked) {
            matchFragmentPagerAdapter.matchFragment.addMatchToAdapter(match)
        } else {
            matchFragmentPagerAdapter.matchFragment.removeMatchFromAdapter(match)
        }
    }

    /**
     * Method called from Match Fragment which removes a like from the Match Fragment.
     * Then updates the Special Blend Fragment to search and remove the Match from the
     * RecyclerView Adapter
     */
    override fun onFromMatchFragmentRemoveLike(match: Match) =
                matchFragmentPagerAdapter.specialBlendFragment.removeLikeFromMatchList(match)

    /**
     * PagerAdapter that manages fragments that are used within the app.
     *
     * @property[specialBlendFragment] A reference to the Special Blend Fragment
     *
     * @property[matchFragment] A reference to the Special Blend Fragment
     */
    class MatchFragmentPagerAdapter(fragmentManager: FragmentManager)
        : FragmentPagerAdapter(fragmentManager) {

        private val frags = arrayOfNulls<Fragment>(2)

        val specialBlendFragment by lazy {
            frags[0] as SpecialBlendFragment
        }

        val matchFragment by lazy {
            frags[1] as MatchFragment
        }

        enum class FragmentState(val title: String) {
            SPECIAL_BLEND("Special Blend"),
            MATCH("Match %")
        }

        init {
            frags[0] = SpecialBlendFragment.newInstance()
            frags[1] = MatchFragment.newInstance()
        }

        override fun getItem(position: Int): Fragment = frags[position]!!

        /**
         * Overriding this is necessary to get a reference to fragments that persist through
         * a configuration change.
         */
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            frags[position] = super.instantiateItem(container, position) as Fragment
            return frags[position]!!
        }

        override fun getPageTitle(position: Int): CharSequence?
                = FragmentState.values()[position].title

        override fun getCount(): Int = FragmentState.values().size
    }
}
