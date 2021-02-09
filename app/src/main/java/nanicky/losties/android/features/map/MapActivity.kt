package nanicky.losties.android.features.map

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_map.*
import nanicky.losties.android.R
import org.osmdroid.api.IMapController
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        setUpMap()
    }


    private fun setUpMap() {
        val context: Context = this
        val mapController: IMapController = vMap.getController()
        mapController.setZoom(12)
        val startPoint = GeoPoint(47.219196, 39.702261)
        mapController.setCenter(startPoint)
        vMap.setBuiltInZoomControls(false)
        vMap.minZoomLevel = 6.0
        val myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), vMap)
        // Fixme: Set Custom item
        //Bitmap myCustomLocationOverlay = BitmapFactory.decodeResource(getResources(), R.drawable.my_location_overlay_small);
        //myLocationOverlay.setPersonIcon(myCustomLocationOverlay);
        myLocationOverlay.enableMyLocation()
        vMap.overlays.add(myLocationOverlay)
        val mRotationGestureOverlay = RotationGestureOverlay(context, vMap)
        mRotationGestureOverlay.isEnabled = true
        vMap.setMultiTouchControls(true)
        vMap.overlays.add(mRotationGestureOverlay)
    }


}