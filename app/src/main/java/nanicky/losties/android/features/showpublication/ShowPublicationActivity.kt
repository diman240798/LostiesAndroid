package nanicky.losties.android.features.showpublication

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_publication.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.features.enums.PublicationTypes

class ShowPublicationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_publication)



        val publicationType = animal.type

        publicationType?.let {
            when (it.toPublicationType()) {
                PublicationTypes.LOST -> {
                    tvTitle.text = l.tr(R.string.lostie)
                }
                PublicationTypes.FOUND -> {
                    tvTitle.text = l.tr(R.string.taken_home)
                }
                PublicationTypes.SEEN -> {
                    tvTitle.text = l.tr(R.string.seen_at_street)
                }
            }
        }

        
    }
}