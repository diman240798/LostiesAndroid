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
    }
}