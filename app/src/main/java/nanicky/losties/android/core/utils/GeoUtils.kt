package nanicky.losties.android.core.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.core.base.Localizer
import nanicky.losties.android.core.extensions.showInfoAlertDialog
import nanicky.losties.android.features.publishad.PublishAdAnimalActivity
import timber.log.Timber
import java.util.*

fun checkPermissionsOrgetLocation(
    l: Localizer,
    activity: BaseActivity,
    onComplete: (Location) -> Unit,
    onError: () -> Unit
) {
    val checkSelfPermission =
        ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
    if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
        activity.showInfoAlertDialog(
            l.tr(R.string.permission),
            l.tr(R.string.need_location_permissions),
            { _, _ -> requestLocationPermission(activity) },
            { onError() }
        )
    } else {
        getLocationAndClose(activity, onComplete , onError)
    }
}

fun requestLocationPermission(activity: BaseActivity) {
    ActivityCompat.requestPermissions(
        activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
        PublishAdAnimalActivity.MY_PERMISSIONS_REQUEST_LOCATION
    )
}

fun getCompleteAddressString(
    context: Context,
    location: Location
): GeoResult = getCompleteAddressString(context, location.latitude, location.longitude)


fun getCompleteAddressString(
    context: Context,
    latitude: Double,
    longtitude: Double
): GeoResult {
    var strAdd : String? = null
    val geocoder = Geocoder(context, Locale.getDefault())
    try {
        val addresses: List<Address>? =
            geocoder.getFromLocation(latitude, longtitude, 1)
        if (addresses != null) {
            val returnedAddress: Address = addresses[0]
            val strReturnedAddress = StringBuilder("")
            for (i in 0..returnedAddress.maxAddressLineIndex) {
                strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
            }
            strAdd = strReturnedAddress.toString()
            Timber.w("My address: $strReturnedAddress")
        } else {
            Timber.w("My address: No Address returned!")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Timber.w("My address: Canont get Address!")
    }
    return GeoResult(strAdd, longtitude, latitude)
}

class GeoResult(val strAdd: String?, val longtitude: Double, val latitude: Double)
