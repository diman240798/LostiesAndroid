package nanicky.losties.android.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import nanicky.losties.android.R
import nanicky.losties.android.features.main.feed.FeedFragment
import nanicky.losties.android.features.main.user.UserFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val DEFAULT_ITEM = R.id.item_feed
        const val CURRENT_BOTTOM_NAV_POSITION = "bottom_nav_pos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pos =
            savedInstanceState?.getInt(CURRENT_BOTTOM_NAV_POSITION, DEFAULT_ITEM) ?: DEFAULT_ITEM
        initBottomNavigationView(pos)
    }

    private fun initBottomNavigationView(pos: Int) {
        bnvMenu.setOnNavigationItemSelectedListener(this)

        bnvMenu.selectedItemId = pos
        navigate(pos)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return navigate(item.itemId)
    }

    private fun navigate(itemId: Int): Boolean {
        when (itemId) {
            R.id.item_feed -> changeFragment(FeedFragment.newInstance(), FeedFragment.TAG)
            R.id.item_profile -> changeFragment(UserFragment.newInstance(), UserFragment.TAG)
        }
        return true
    }

    private fun changeFragment(fragment: Fragment, tagFragmentName: String) {

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment: Fragment? = supportFragmentManager.primaryNavigationFragment

        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment)
        }
        var fragmentTemp: Fragment? = supportFragmentManager.findFragmentByTag(tagFragmentName)

        if (fragmentTemp == null) {
            fragmentTemp = fragment
            fragmentTransaction.add(fcvContainer.id, fragmentTemp, tagFragmentName)
        } else {
            fragmentTransaction.show(fragmentTemp)
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commitNowAllowingStateLoss()
    }
}