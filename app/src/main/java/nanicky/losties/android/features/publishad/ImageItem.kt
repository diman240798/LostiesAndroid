package nanicky.losties.android.features.publishad

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_image.*
import nanicky.losties.android.R
import nanicky.losties.android.core.extensions.loadImage

class ImageItem(val photo: String) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.imageView.loadImage(photo)
    }

    override fun getLayout(): Int {
        return R.layout.item_image
    }

}
