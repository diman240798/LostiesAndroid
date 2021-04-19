package nanicky.losties.android.core.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import nanicky.losties.android.core.extensions.showToast
import java.util.*


@SuppressLint("MissingPermission")
fun getLocationAndClose(context: Activity, successCallBack : (location: Location) -> Unit, errorCallBack : () -> Unit) {
    val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val timer = Timer()

    val locationListener: LocationListener = MyLocationListener(locationManager, successCallBack, timer)

    timer.schedule(object : TimerTask() {
        override fun run() {
            locationManager.removeUpdates(locationListener)
            context.runOnUiThread { errorCallBack() }
        }
    }, 5 * 1000, 1)

    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F, locationListener)
    } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0F, locationListener)
    } else {
        context.showToast("No gps providerEnabled")
        errorCallBack()
    }


}

class MyLocationListener(
    var locationManager: LocationManager,
    var callback: (location: Location) -> Unit,
    val timer: Timer
) : LocationListener {
    override fun onLocationChanged(location: Location?) {
        location?.let {
            timer.cancel()
            callback(location)
            locationManager.removeUpdates(this)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

}
