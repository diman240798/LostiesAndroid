package nanicky.losties.android.core.ui

import nanicky.losties.android.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class BottomEmptyViewItem: Item() {
    override fun getLayout(): Int = R.layout.ui_empty_view

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}