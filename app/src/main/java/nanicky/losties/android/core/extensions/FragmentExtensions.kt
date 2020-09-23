package nanicky.losties.android.core.extensions

import androidx.fragment.app.Fragment

fun Fragment.add(containerId: Int, fragment: Fragment) {
    val fragmentTransaction = childFragmentManager.beginTransaction()
    fragmentTransaction.add(containerId, fragment)
    fragmentTransaction.commit()
}

fun Fragment.replace(containerId: Int, fragment: Fragment) {
    val fragmentTransaction = childFragmentManager.beginTransaction()
    fragmentTransaction.replace(containerId, fragment)
    fragmentTransaction.commit()
}