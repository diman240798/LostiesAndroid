package nanicky.losties.android.features.main.feed

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.pager_item_feed_filters.*
import nanicky.losties.android.R

class FeedItem(val setFirstScreen: () -> Unit) : Item() {

    override fun getLayout(): Int = R.layout.pager_item_feed_filters

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.btGoBack.setOnClickListener {
            setFirstScreen()
        }
    }

}