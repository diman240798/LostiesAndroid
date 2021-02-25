package nanicky.losties.android.features.showpublication

import android.content.Intent
import android.os.Bundle
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_show_publication.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.features.publishad.ImageItem

class ShowPublicationActivity : BaseActivity() {

    private lateinit var choosePhotoHelper: ChoosePhotoHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_publication)

        setUpPhotoLoad()



        /*val publicationType = animal.type

        publicationType?.let {
            when (it.toPublicationType()) {
                PublicationTypes.LOST -> {
                    tvTitle.text = l.tr(R.string.lostie)
                }
                PublicationTypes.FOUND -> {
                    tvTitle.text = l.tr(R.string.taken_home)
                }
                PublicationTypes.SEEN -> {
                    tvTitle.text = l.tr(R.string.seen_at_street)
                }
            }
        }*/

        
    }

    private fun setUpPhotoLoad() {
        val adapter = GroupAdapter<GroupieViewHolder>()
        vpPhotos.adapter = adapter

        indicator.setViewPager(vpPhotos)
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)

        class PhotoCallback : ChoosePhotoCallback<String> {

            override fun onChoose(photo: String?) {
                photo?.let {
                    adapter.add(ImageItem(photo))
                }
            }
        }

        choosePhotoHelper = ChoosePhotoHelper.with(this)
            .asFilePath()
            .build(PhotoCallback())

        lAnimalName.setOnClickListener {
            choosePhotoHelper.showChooser()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}