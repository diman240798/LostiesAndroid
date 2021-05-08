package nanicky.losties.android.features.watchpublication

import android.os.Bundle
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_show_publication.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.core.extensions.loadImage
import nanicky.losties.android.core.extensions.visible
import nanicky.losties.android.core.utils.getImageUrl
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.enums.toPublicationType
import nanicky.losties.android.features.publishad.ImageItem
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class WatchPublicationActivity : BaseActivity() {

    companion object {
        const val ANIMAL_TYPE_EXTRA = "ANIMAL_TYPE_EXTRA"
        const val USER_ID_EXTRA = "USER_ID_EXTRA"
    }

    private val showPublicationObject: ShowPublicationObject by inject()
    private val viewModel: WatchPublicationActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_publication)


        val animal = showPublicationObject.animal

        val animalData = animal.animal!!
        val publicationType = intent.getStringExtra(ANIMAL_TYPE_EXTRA).toPublicationType()
        publicationType.let {
            when (it) {
                PublicationTypes.LOST -> {
                    tvTitle.text = l.tr(R.string.lostie)
                }
                PublicationTypes.TAKEN -> {
                    tvTitle.text = l.tr(R.string.taken_home)
                }
                PublicationTypes.SEEN -> {
                    tvTitle.text = l.tr(R.string.seen_at_street)
                }
            }
        }


        val adapter = GroupAdapter<GroupieViewHolder>()
        vpPhotos.adapter = adapter

        indicator.setViewPager(vpPhotos)
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)

        animalData.photoIds?.forEach {
            val imageUrl = getImageUrl(it, publicationType)
            adapter.add(ImageItem(imageUrl))
        }

        tvAnimalName.text = animalData.name
        val type = animalData.type!!
        tvAnimalType.text = type.rusName
        ivAnimalType.loadImage(type.image)
        tvAnimalBreed.text = animalData.breed
        tvUserName.text = animal.userData!!.name
        tvAddress.text = animal.geoAddress!!.address
        tvPhone0.text = animal.userData.numbers
        tvMail.text = animal.userData.emails
        tvSocial0.text = animal.userData.networksUrls


        val userId = intent.getStringExtra(USER_ID_EXTRA)
        userId?.let {
            btPublishOnSocailNetworks.visible()
            btPublishOnSocailNetworks.setOnClickListener {
                viewModel.publishOnSocailNetworks(animal.id!!, publicationType)
            }
        }
    }
}