package nanicky.losties.android.features.watchad.item

import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.pager_item_watch.*
import nanicky.losties.android.R
import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.core.di.PHOTO_URL_POINT
import nanicky.losties.android.core.extensions.goToShowPublicationActivity
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.showpublication.ShowPublicationObject
import java.util.*

fun getImageUrl(photos: List<UUID>, type: PublicationTypes): String? {
    if (photos.isEmpty()) return null
    val photoId = photos[0]
    return getImageUrl(photoId, type)
}

fun getImageUrl(photoId: UUID, type: PublicationTypes): String {
    return String.format(PHOTO_URL_POINT, photoId, type)
}

class WatchItem(
    val animal: AnimalPublication,
    val publicationObject: ShowPublicationObject,
    val publicationTypes: PublicationTypes
) : Item() {

    override fun getLayout(): Int = R.layout.pager_item_watch

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val animalImage = getImageUrl(animal.animal!!.photoIds!!, PublicationTypes.LOST)
        Glide.with(viewHolder.containerView.context)
            .load(animalImage)
            .into(viewHolder.ivImage)

        viewHolder.tvAddress.text = animal.geoAddress!!.address
        Glide.with(viewHolder.containerView.context)
            .load(animal.animal!!.type!!.image)
            .into(viewHolder.ivTypeSex)

        viewHolder.root.setOnClickListener {
            publicationObject.animal = animal
            it.context.goToShowPublicationActivity(publicationTypes)
        }
    }

}