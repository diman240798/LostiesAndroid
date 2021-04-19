package nanicky.losties.android.features.main.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_user.*
import nanicky.losties.android.R
import nanicky.losties.android.features.publishad.PublishAdAnimalActivity
import nanicky.losties.android.features.enums.PublicationTypes
import nanicky.losties.android.features.publishad.PublishAdAnimalActivity.Companion.ANIMAL_TYPE_EXTRA
import nanicky.losties.android.features.rateanimals.RateAnimalActivity
import nanicky.losties.losties.util.AnimalType

class UserFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()

        const val TAG = "UserFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clLostAnimal.setOnClickListener {
            val intent = Intent(context, PublishAdAnimalActivity::class.java)
            intent.putExtra(PublishAdAnimalActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.LOST.name)
            requireContext().startActivity(intent)
        }

        clFoundAnimal.setOnClickListener {
            val intent = Intent(context, PublishAdAnimalActivity::class.java)
            intent.putExtra(PublishAdAnimalActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.FOUND.name)
            requireContext().startActivity(intent)
        }

        clSeemsHomeAnimal.setOnClickListener {
            val intent = Intent(context, PublishAdAnimalActivity::class.java)
            intent.putExtra(PublishAdAnimalActivity.ANIMAL_TYPE_EXTRA, PublicationTypes.SEEN.name)
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
    }
}