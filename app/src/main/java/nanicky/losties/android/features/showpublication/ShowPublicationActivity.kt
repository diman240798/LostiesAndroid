package nanicky.losties.android.features.showpublication

import android.os.Bundle
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_publish_ad_animal.*
import kotlinx.android.synthetic.main.activity_show_publication.*
import kotlinx.android.synthetic.main.activity_show_publication.indicator
import kotlinx.android.synthetic.main.activity_show_publication.tvTitle
import kotlinx.android.synthetic.main.activity_show_publication.vpPhotos
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.enums.toPublicationType
import nanicky.losties.android.features.publishad.ImageItem
import nanicky.losties.android.features.publishad.PublishAdAnimalActivity
import nanicky.losties.android.features.watchad.item.getImageUrl
import org.koin.android.ext.android.inject

class ShowPublicationActivity : BaseActivity() {

    companion object {
        const val ANIMAL_TYPE_EXTRA = "ANIMAL_TYPE_EXTRA"
    }

    private val showPublicationObject: ShowPublicationObject by inject()

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
        Glide.with(this).load(type.image).into(ivAnimalType)
        tvAnimalBreed.text = animalData.breed
        tvUserName.text = animal.user!!.name
        tvAddress.text = animal.geoAddress!!.address
        tvPhone0.text = animal.user.numbers
        tvMail.text = animal.user.emails
        tvSocial0.text = animal.user.networksUrls



    }
}