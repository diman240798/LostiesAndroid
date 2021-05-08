package nanicky.losties.android.features.map

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_map.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.losties.util.AnimalType
import org.koin.android.ext.android.inject
import org.osmdroid.api.IMapController
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MapActivity : BaseActivity() {

    private val dataObject: MapActivityObject by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        setUpMap()

        val radiusMarker = RadiusMarkerClusterer(this)

        dataObject.items.forEach {
            // create Marker
            val marker = Marker(vMap)
            marker.title = it.animal!!.name
            val markerPosition = GeoPoint(it.geoAddress!!.latitude, it.geoAddress!!.longtitude)
            marker.position = markerPosition
            marker.icon = getDrawable(getMapMarkerByType(it.animal!!.type!!))
            marker.setOnMarkerClickListener { _, _ ->
                onMarkerClicked(it)
                false
            }
            radiusMarker.add(marker)
        }
        vMap.overlays.add(radiusMarker)
    }

    private fun onMarkerClicked(animalPublication: AnimalPublication) {

    }

    private fun getMapMarkerByType(type: AnimalType): Int = when (type) {
        AnimalType.CAT -> R.drawable.ic_map_cat
        AnimalType.CATTY -> R.drawable.ic_map_catty
        AnimalType.DOG -> R.drawable.ic_map_dog
        AnimalType.DOGGY -> R.drawable.ic_map_doggy
        AnimalType.OTHER -> R.drawable.ic_map_other
        else -> R.drawable.ic_map_cat
    }


    private fun setUpMap() {


        val context: Context = this
        Configuration.getInstance()
            .load(context, PreferenceManager.getDefaultSharedPreferences(context))
        vMap.setTileSource(TileSourceFactory.MAPNIK)
        val mapController: IMapController = vMap.controller
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