package nanicky.losties.android.features.main.feed.item

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import nanicky.losties.android.R
import nanicky.losties.android.core.extensions.goToShowPublicationActivity

class FeedItem  : Item() {
    override fun getLayout(): Int = R.layout.pager_item_feed_list_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.root.setOnClickListener {
            it.context.goToShowPublicationActivity()
        }
    }
}