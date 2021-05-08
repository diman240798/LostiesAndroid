package nanicky.losties.android.features.main.feed

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.pager_item_feed_list.*
import nanicky.losties.android.R
import nanicky.losties.android.core.data.models.AnimalPublicationTyped
import nanicky.losties.android.features.watchpublication.ShowPublicationObject

class FeedListItem : Item() {

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun getLayout(): Int = R.layout.pager_item_feed_list

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.rcFeedList.adapter = adapter
    }

    fun setItems(
        items: List<AnimalPublicationTyped>,
        showPublicationObject: ShowPublicationObject
    ) {
        adapter.clear()
        adapter.addAll(items.map { FeedItem(it, showPublicationObject) })
        adapter.notifyDataSetChanged()
    }

}