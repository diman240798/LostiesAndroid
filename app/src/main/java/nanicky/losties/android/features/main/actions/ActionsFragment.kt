package nanicky.losties.android.features.main.actions

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_actions.*
import kotlinx.android.synthetic.main.read_publications.*
import nanicky.losties.android.R
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.publishad.PublishAnimalActivity
import nanicky.losties.android.features.rateanimals.RateAnimalActivity
import nanicky.losties.android.features.watchpublicationlist.WatchPublicationListActivity
import nanicky.losties.losties.util.AnimalType

class ActionsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ActionsFragment()

        const val TAG = "ActionsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clLostAnimal.setOnClickListener {
            val intent = Intent(context, PublishAnimalActivity::class.java)
            intent.putExtra(PublishAnimalActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.LOST.name)
            requireContext().startActivity(intent)
        }

        clTakenAnimal.setOnClickListener {
            val intent = Intent(context, PublishAnimalActivity::class.java)
            intent.putExtra(PublishAnimalActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.TAKEN.name)
            requireContext().startActivity(intent)
        }

        clSeemsHomeAnimal.setOnClickListener {
            val intent = Intent(context, PublishAnimalActivity::class.java)
            intent.putExtra(PublishAnimalActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.SEEN.name)
            requireContext().startActivity(intent)
        }

        clWatchAnimals.setOnClickListener {
            val intent = Intent(context, RateAnimalActivity::class.java)
            intent.putExtra(RateAnimalActivity.ANIMAL_TYPE_EXTRA, AnimalType.ALL.name)
            requireContext().startActivity(intent)
        }

        clWatchCats.setOnClickListener {
            val intent = Intent(context, RateAnimalActivity::class.java)
            intent.putExtra(RateAnimalActivity.ANIMAL_TYPE_EXTRA, AnimalType.CAT.name)
            requireContext().startActivity(intent)
        }

        clWatchDogs.setOnClickListener {
            val intent = Intent(context, RateAnimalActivity::class.java)
            intent.putExtra(RateAnimalActivity.ANIMAL_TYPE_EXTRA, AnimalType.DOG.name)
            requireContext().startActivity(intent)
        }

        clReadLostAnimal.setOnClickListener {
            val intent = Intent(context, WatchPublicationListActivity::class.java)
            intent.putExtra(WatchPublicationListActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.LOST.name)
            requireContext().startActivity(intent)
        }

        clReadSeenAnimal.setOnClickListener {
            val intent = Intent(context, WatchPublicationListActivity::class.java)
            intent.putExtra(WatchPublicationListActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.SEEN.name)
            requireContext().startActivity(intent)
        }

        clReadTakenAnimal.setOnClickListener {
            val intent = Intent(context, WatchPublicationListActivity::class.java)
            intent.putExtra(WatchPublicationListActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.TAKEN.name)
            requireContext().startActivity(intent)
        }
    }
}