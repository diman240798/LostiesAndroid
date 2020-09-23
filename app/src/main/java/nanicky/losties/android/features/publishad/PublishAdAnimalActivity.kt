package nanicky.losties.android.features.publishad

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_publish_ad_animal.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.Localizer
import nanicky.losties.android.core.extensions.visible
import nanicky.losties.android.core.ui.EmailTextChangeListener
import org.koin.android.ext.android.inject


class PublishAdAnimalActivity : AppCompatActivity() {

    val l: Localizer by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_ad_animal)

        initTypeAutoAutocomplete()

        setUpEmail()

        setUpAddButton(ivAddPhone, etPhone1, ivClosePhone1, etPhone2, ivClosePhone2)
        setUpClose(etPhone1, ivClosePhone1)
        setUpClose(etPhone2, ivClosePhone2)



        setUpAddButton(ivAddSocNet, etSocNet1, ivCloseSoc1, etSocNet2, ivCloseSoc2)
        setUpClose(etSocNet1, ivCloseSoc1)
        setUpClose(etSocNet2, ivCloseSoc2)

    }

    private fun setUpAddButton(btAdd: View, view1 : View, view1Close : View, view2: View, view2Close: View) {
        btAdd.setOnClickListener {
            if (view1.visibility != View.VISIBLE) {
                view1.visibility = View.VISIBLE
                view1Close.visibility = View.VISIBLE
            } else {
                view2.visibility = View.VISIBLE
                view2Close.visibility = View.VISIBLE
            }
        }
    }

    private fun initTypeAutoAutocomplete() {
        val autoItems = listOf<TextAndImage>(
            TextAndImage(l.tr(R.string.cat), R.drawable.ic_cat_mail),
            TextAndImage(l.tr(R.string.catty), R.drawable.ic_cat_female),
            TextAndImage(l.tr(R.string.dog), R.drawable.ic_dog_mail),
            TextAndImage(l.tr(R.string.doggy), R.drawable.ic_dog_female),
            TextAndImage(l.tr(R.string.other), R.drawable.ic_other)
        )

        val adapter = TextImageAdapter(this, autoItems)
        spinnerType.adapter = adapter
        spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val imageId = autoItems[position].image
                ivType.setImageResource(imageId)
            }
        }
    }

    private fun setUpEmail() {
        val emailTextChangeListener = EmailTextChangeListener(etMail)
        etMail.addTextChangedListener(emailTextChangeListener)
    }

    private fun setUpClose(text: EditText, btClose: View) {
        btClose.setOnClickListener {
            text.visibility = View.GONE
            btClose.visibility = View.GONE
            text.setText("")
        }
    }
}

