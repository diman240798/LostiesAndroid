package nanicky.losties.android.features.watchpublicationlist

import android.os.Bundle
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_publish_ad_animal.tvTitle
import kotlinx.android.synthetic.main.activity_watch_ad.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.core.extensions.goToMapActivity
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.enums.toPublicationType
import nanicky.losties.android.features.map.MapActivityObject
import nanicky.losties.android.features.watchpublication.ShowPublicationObject
import nanicky.losties.android.features.watchpublicationlist.item.WatchItem
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class WatchPublicationListActivity : BaseActivity() {

    private val viewModel: WatchPublicationListActivityViewModel by viewModel()
    private val dataObject: MapActivityObject by inject()

    companion object {
        const val ANIMAL_TYPE_EXTRA = "ANIMAL_TYPE_EXTRA"
        const val USER_ID_EXTRA = "USER_ID_EXTRA"
    }


    private val showPublicationObject: ShowPublicationObject by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_ad)
        subscribeModels()
    }

    private fun subscribeModels() {

        val adapter = GroupAdapter<GroupieViewHolder>()

        rvAnimalList.adapter = adapter

        fun setNewItems(
            adapter: GroupAdapter<GroupieViewHolder>,
            items: MutableCollection<WatchItem>
        ) {
            adapter.clear()
            adapter.addAll(items)
            adapter.notifyDataSetChanged()
        }

        val publicationType = intent.getStringExtra(ANIMAL_TYPE_EXTRA)!!.toPublicationType()
        val userId = intent.getStringExtra(USER_ID_EXTRA)


        when (publicationType) {
            PublicationTypes.LOST -> {
                tvTitle.text = l.tr(R.string.lost_animal)
                viewModel.getLostAnimals(userId)
            }
            PublicationTypes.TAKEN -> {
                tvTitle.text = l.tr(R.string.taken_take_home)
                viewModel.getTakenAnimals(userId)
            }
            PublicationTypes.SEEN -> {
                tvTitle.text = l.tr(R.string.seems_home)
                viewModel.getSeenAnimals(userId)
            }
        }

        viewModel.animals.observe(this, Observer {
            val newItems = it.map { WatchItem(it, showPublicationObject, publicationType, userId) }.toMutableList()
            setNewItems(adapter, newItems)
        })

        btOpenMap.setOnClickListener {
            viewModel.animals.value?.let {
                goToMapActivity(it, dataObject)
            }
        }
    }
}