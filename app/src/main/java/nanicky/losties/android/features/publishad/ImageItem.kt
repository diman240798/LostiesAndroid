package nanicky.losties.android.features.publishad

import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_image.*
import nanicky.losties.android.R

class ImageItem(val photo: String) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Glide.with(viewHolder.containerView.context)
            .load(photo)
            .into(viewHolder.imageView)
    }

    override fun getLayout(): Int {
        return R.layout.item_image
    }

}
