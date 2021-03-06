package nanicky.losties.android.features.watchpublicationlist.item

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.pager_item_watch.*
import nanicky.losties.android.R
import nanicky.losties.android.core.data.models.AnimalPublication
import nanicky.losties.android.core.extensions.goToShowPublicationActivity
import nanicky.losties.android.core.extensions.loadImage
import nanicky.losties.android.core.utils.getImageUrl
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.watchpublication.ShowPublicationObject

class WatchItem(
    val animal: AnimalPublication,
    val publicationObject: ShowPublicationObject,
    val publicationTypes: PublicationTypes,
    val userId: String?
) : Item() {

    override fun getLayout(): Int = R.layout.pager_item_watch

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val animalImage = getImageUrl(animal.animal!!.photoIds!!, PublicationTypes.LOST)
        viewHolder.ivImage.loadImage(animalImage)
        viewHolder.tvAddress.text = animal.geoAddress!!.address
        viewHolder.ivTypeSex.loadImage(animal.animal!!.type!!.image)
        viewHolder.root.setOnClickListener {
            publicationObject.animal = animal
            it.context.goToShowPublicationActivity(publicationTypes, userId)
        }
    }
}