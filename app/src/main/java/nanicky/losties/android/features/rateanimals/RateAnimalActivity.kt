package nanicky.losties.android.features.rateanimals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_rate_animal.*
import nanicky.losties.android.R
import nanicky.losties.android.core.extensions.gone
import nanicky.losties.android.core.extensions.invisible
import nanicky.losties.android.core.extensions.visible
import nanicky.losties.android.features.publishad.PublishAdAnimalActivity
import nanicky.losties.losties.util.AnimalType
import nanicky.losties.losties.util.toAnimalType
import org.koin.android.viewmodel.ext.android.viewModel

class RateAnimalActivity : AppCompatActivity() {

    companion object {
        const val ANIMAL_TYPE_EXTRA = "ANIMAL_TYPE_EXTRA"
    }

    private val viewmodel: RateAnimalViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_animal)

        val animalType = intent.getStringExtra(PublishAdAnimalActivity.ANIMAL_TYPE_EXTRA)?.toAnimalType()

        animalType?.let {
           viewmodel.type = animalType
        }

        loadNextImage()

        viewmodel.animalRandomRequest.observe(this, Observer {
            pbAnimal.invisible()
            ivAnimal.visible()

            it?.let {
                Glide.with(this)
                    .load(it.photo.toByteArray())
                    .circleCrop()
                    .into(ivAnimal)

//                ivAnimal.setImageBitmap(BitmapFactory.decodeByteArray(it.name_photo.toByteArray(), 0, it.name_photo.size))

                btLike.visible()
                btLike.isEnabled = true
                btDislike.visible()
                btDislike.isEnabled = true

                tvLiked.text = it.count_liked.toString()
                tvDisliked.text = it.count_unliked.toString()
            }
        })
    }

    fun onNext(view: View) {
        loadNextImage()
    }

    private fun loadNextImage() {
        ivAnimal.invisible()
        pbAnimal.visible()
        tvDisliked.invisible()
        tvLiked.invisible()
        when (viewmodel.type) {
            AnimalType.CAT -> {
                viewmodel.requestRandomCat()
            }
            AnimalType.DOG -> {
                viewmodel.requestRandomDog()
            }
            else -> viewmodel.requestRandomAnimal()
        }
    }

    fun onLikeClicked(view: View) {
        viewmodel.rateAnimal(liked = true)
        tvDisliked.visible()
        tvLiked.visible()
        btLike.isEnabled = false
        btDislike.gone()
    }

    fun onDislikeClicked(view: View) {
        viewmodel.rateAnimal(liked = false)
        tvDisliked.visible()
        tvLiked.visible()
        btDislike.isEnabled = false
        btLike.gone()
    }


}