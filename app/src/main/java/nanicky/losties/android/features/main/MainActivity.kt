package nanicky.losties.android.features.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import kotlinx.android.synthetic.main.activity_main.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.core.data.models.vk.VKUser
import nanicky.losties.android.core.data.models.vk.VKUsersRequest
import nanicky.losties.android.features.main.actions.ActionsFragment
import nanicky.losties.android.features.main.feed.FeedFragment
import nanicky.losties.android.features.main.user.UserFragment
import timber.log.Timber

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

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
            R.id.item_actions -> changeFragment(ActionsFragment.newInstance(), ActionsFragment.TAG)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                Timber.d("Successful VK login. Request user...")
                VK.execute(
                    VKUsersRequest(
                        token.userId
                    ), object : VKApiCallback<List<VKUser>> {
                        override fun success(result: List<VKUser>) {
                            Timber.d("VK users received...")
                            if (result.isNotEmpty()) {
                                Timber.d("VK first user getting...")
                                Timber.d(result.first().toString())
                                authNotifier.vkUid.value = result.first()
                            }
                        }

                        override fun fail(error: Exception) {
                            Timber.d("VK user receiving failed...")
                        }
                    })
            }

            override fun onLoginFailed(errorCode: Int) {
                Timber.d("VK login onLoginFailed...")
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}