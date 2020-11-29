package nanicky.losties.android.features.publishad

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_publish_ad_animal.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.core.ui.EmailTextChangeListener
import nanicky.losties.android.core.utils.checkPermissionsOrgetLocation
import nanicky.losties.android.core.utils.getCompleteAddressString
import nanicky.losties.losties.enums.PublicationTypes.*
import nanicky.losties.losties.enums.toPublicationType


class PublishAdAnimalActivity : BaseActivity() {
    companion object {
        const val ANIMAL_TYPE_EXTRA = "ANIMAL_TYPE_EXTRA"
        const val MY_PERMISSIONS_REQUEST_LOCATION = 99

        var LAST_ADDRESS_SINCE_LAUNCH : String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_ad_animal)

        val type = intent.getStringExtra(ANIMAL_TYPE_EXTRA)

        type?.let {
            when (it.toPublicationType()) {
                LOST -> {
                    tvTitle.setText(R.string.lost_animal)
                }
                FOUND -> {
                    tvTitle.setText(R.string.found_take_home)
                }
                SEEN -> {
                    tvTitle.setText(R.string.seems_home)
                }
            }
        }

        initTypeSpinner()

        setUpEmail()

        setUpAddButton(ivAddPhone, etPhone1, ivClosePhone1, etPhone2, ivClosePhone2)
        setUpClose(etPhone1, ivClosePhone1)
        setUpClose(etPhone2, ivClosePhone2)



        setUpAddButton(ivAddSocNet, etSocNet1, ivCloseSoc1, etSocNet2, ivCloseSoc2)
        setUpClose(etSocNet1, ivCloseSoc1)
        setUpClose(etSocNet2, ivCloseSoc2)


        if (LAST_ADDRESS_SINCE_LAUNCH != null) {
            pbAddress.visibility = View.GONE
            tilAddress.visibility = View.VISIBLE
            etAddress.setText(LAST_ADDRESS_SINCE_LAUNCH)
        } else {
            tilAddress.visibility = View.GONE
            pbAddress.visibility = View.VISIBLE

            checkPermissionsOrgetLocation(l, this,
                onComplete = { location ->
                    val address = getCompleteAddressString(this, location)
                    pbAddress.visibility = View.GONE
                    tilAddress.visibility = View.VISIBLE
                    if (address != null) {
                        LAST_ADDRESS_SINCE_LAUNCH = address
                        etAddress.setText(address)
                    }
                },
                onError = {
                    pbAddress.visibility = View.GONE
                    tilAddress.visibility = View.VISIBLE
                })
        }

    }

    private fun setUpAddButton(
        btAdd: View,
        view1: View,
        view1Close: View,
        view2: View,
        view2Close: View
    ) {
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

    private fun initTypeSpinner() {
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

        spinnerType.isFocusableInTouchMode = true
        spinnerType.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (spinnerType.windowToken != null) {
                    spinnerType.performClick()
                }
                clTypeRoot.setBackgroundResource(R.drawable.backgr_white_with_blue_border)
            } else {
                clTypeRoot.setBackgroundResource(R.drawable.backgr_white_with_gray_border)
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

