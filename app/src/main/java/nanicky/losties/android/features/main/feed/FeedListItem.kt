package nanicky.losties.android.features.main.feed

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.pager_item_feed_list.*
import nanicky.losties.android.R
import nanicky.losties.android.features.main.feed.item.FeedItem

class FeedListItem : Item() {

    override fun getLayout(): Int = R.layout.pager_item_feed_list

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val adapter = GroupAdapter<GroupieViewHolder>()
        viewHolder.rcFeedList.adapter = adapter
    }

}