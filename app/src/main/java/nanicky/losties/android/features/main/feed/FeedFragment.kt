package nanicky.losties.android.features.main.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_feed.*
import nanicky.losties.android.R
import nanicky.losties.android.features.watchpublication.ShowPublicationObject
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FeedFragment: Fragment() {


    companion object {
        @JvmStatic
        fun newInstance() = FeedFragment()

        const val TAG = "FeedFragment"
    }

    private val viewModel: FeedFragmentViewModel by viewModel()
    private val showPublicationObject: ShowPublicationObject by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GroupAdapter<GroupieViewHolder>()
        val feedListItem = FeedListItem()
        adapter.add(feedListItem)
        vpFeed.adapter = adapter

        viewModel.newPublications.observe(viewLifecycleOwner, Observer {
            feedListItem.setItems(it, showPublicationObject)
        })

        viewModel.getNewPublications()
    }
}