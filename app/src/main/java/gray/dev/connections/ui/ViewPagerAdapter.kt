package gray.dev.connections.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import gray.dev.connections.ui.blocked.BlockedFragment
import gray.dev.connections.ui.favorites.FavoritesFragment
import gray.dev.connections.ui.followers.FollowersFragment

class ViewPagerAdapter(fm: FragmentManager, numOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    private var numOfTabs: Int = 0

    init {
        this.numOfTabs = numOfTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FollowersFragment()
            }
            1 -> {
                FavoritesFragment()
            }
            2 -> {
                BlockedFragment()
            }

            else -> FollowersFragment()
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }


}