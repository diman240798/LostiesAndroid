package nanicky.losties.android.features.publishad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_publish_ad_animal.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.core.extensions.showInfoAlertDialog
import nanicky.losties.android.core.extensions.showToast
import nanicky.losties.android.core.ui.EmailTextChangeListener
import nanicky.losties.android.core.utils.GeoResult
import nanicky.losties.android.core.utils.checkPermissionsOrgetLocation
import nanicky.losties.android.core.utils.getCompleteAddressString
import nanicky.losties.android.features.common.requests.AddAnimalRequest
import nanicky.losties.android.features.entity.GeoAddress
import nanicky.losties.android.core.data.models.Animal
import nanicky.losties.android.core.data.models.UserData
import nanicky.losties.android.core.extensions.gone
import nanicky.losties.android.core.extensions.visible
import nanicky.losties.android.features.enums.PublicationTypes.*
import nanicky.losties.android.features.enums.toPublicationType
import nanicky.losties.losties.util.AnimalType
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
import java.util.*


class PublishAdAnimalActivity : BaseActivity() {

    companion object {
        const val ANIMAL_TYPE_EXTRA = "ANIMAL_TYPE_EXTRA"
        const val MY_PERMISSIONS_REQUEST_LOCATION = 99

        var LAST_ADDRESS_SINCE_LAUNCH : GeoResult? = null
    }

    private val viewmodel: PublishAdAnimalViewModel by viewModel()

    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    private var photoPaths: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_ad_animal)

        val publicationType = intent.getStringExtra(ANIMAL_TYPE_EXTRA)

        publicationType?.let {
            when (it.toPublicationType()) {
                LOST -> {
                    tvTitle.text = l.tr(R.string.lost_animal)
                }
                FOUND -> {
                    tvTitle.text = l.tr(R.string.found_take_home)
                }
                SEEN -> {
                    tvTitle.text = l.tr(R.string.seems_home)
                }
            }
        }

        viewmodel.init(this,
            loading = {
                if (it) progressDialog.show() else progressDialog.dismiss()
            },
            error = {
                showToast(l.tr(R.string.error_check_inet_connection))
            },
            result = {
                // удачно сохранили запись на беке, закрываем активити
                finish()
            }
        )

        initTypeSpinner()

        setUpEmail()

        setUpAddButton(ivAddPhone, etPhone1, ivClosePhone1, etPhone2, ivClosePhone2)
        setUpClose(etPhone1, ivClosePhone1)
        setUpClose(etPhone2, ivClosePhone2)



        setUpAddButton(ivAddSocNet, etSocNet1, ivCloseSoc1, etSocNet2, ivCloseSoc2)
        setUpClose(etSocNet1, ivCloseSoc1)
        setUpClose(etSocNet2, ivCloseSoc2)

        setUpGeoLocation()

        setUpPhotoLoad()

        sendButtonPrepare(publicationType)
    }

    private fun sendButtonPrepare(publicationType: String?) {
        btSend.setOnClickListener {
            /*if (photoPath == null) {
                showInfoAlertDialog(
                    l.tr(R.string.choose_photo_please),
                    l.tr(R.string.required_for_animal_identification)
                )
                return@setOnClickListener
            }*/

            val photos = mutableListOf<ByteArray>()
            photoPaths.forEach { photoPath ->
                    val photoFile = File(photoPath)
                    photos.add(photoFile.readBytes())
            }

            if (etAddress.text.toString().isEmpty()) {
                showInfoAlertDialog(
                    l.tr(R.string.please_specify_address),
                    l.tr(R.string.required_for_animal_identification)
                )
                return@setOnClickListener
            }
            val address = etAddress.text.toString()

            val phonesList = mutableListOf<String>()
            listOf(etPhone0, etPhone1, etPhone2).forEach { view ->
                val phoneStr = view.text.toString()
                if (view.isVisible && phoneStr.isNotEmpty()) {
                    phonesList.add(phoneStr)
                }
            }

            val netWorks = mutableListOf<String>()
            listOf(etSocNet0, etSocNet1, etSocNet2).forEach { view ->
                val networkStr = view.text.toString()
                if (view.isVisible && networkStr.isNotEmpty()) {
                    netWorks.add(networkStr)
                }
            }

            val email = etMail.text.toString()

            val type = (spinnerType.adapter as TextImageAdapter).items[spinnerType.selectedItemPosition].animalType

            val userName = etUserName.text.toString()
            val animalName = etAnimalName.text.toString()
            val breed = etBreed.text.toString()

            val longtitude = LAST_ADDRESS_SINCE_LAUNCH?.longtitude ?: 0.0
            val latitude = LAST_ADDRESS_SINCE_LAUNCH?.latitude ?: 0.0

            val addAnimalRequest = AddAnimalRequest(
                Animal(
                    UUID.randomUUID(),
                    animalName,
                    type,
                    breed
                ),
                UserData(
                    UUID.randomUUID(),
                    userName,
                    phonesList.joinToString(),
                    email,
                    netWorks.joinToString()
                ),
                GeoAddress(UUID.randomUUID(), longtitude, latitude, address),
                photos = photos
            )

            if (publicationType != null) {
                when (publicationType.toPublicationType()) {
                    LOST -> {
                        viewmodel.addLostAnimal(addAnimalRequest)
                    }
                    FOUND -> {
                        viewmodel.addFoundAnimal(addAnimalRequest)
                    }
                    SEEN -> {
                        viewmodel.addSeenAnimal(addAnimalRequest)
                    }

                }
            } else {
                viewmodel.addLostAnimal(addAnimalRequest)
            }

        }
    }

    private fun setUpPhotoLoad() {
        val adapter = GroupAdapter<GroupieViewHolder>()
        vpPhotos.adapter = adapter

        indicator.setViewPager(vpPhotos)
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)

        class PhotoCallback : ChoosePhotoCallback<String> {

            override fun onChoose(photo: String?) {
                photo?.let {
                    if (photoPaths.isEmpty()) {
                        pbType.visible()
                        clTypeRoot.gone()
                        viewmodel.requestAnimalTypeByPhoto(File(photo)) {
                            pbType.gone()
                            clTypeRoot.visible()
                        }
                    }
                    photoPaths.add(photo)
                    adapter.add(ImageItem(photo))
                }
            }
        }

        choosePhotoHelper = ChoosePhotoHelper.with(this)
            .asFilePath()
            .build(PhotoCallback())

        ivLoadPhoto.setOnClickListener {
            choosePhotoHelper.showChooser()
        }
    }

    private fun setUpGeoLocation() {
        if (LAST_ADDRESS_SINCE_LAUNCH != null && LAST_ADDRESS_SINCE_LAUNCH!!.strAdd != null) {
            pbAddress.visibility = View.GONE
            tilAddress.visibility = View.VISIBLE
            etAddress.setText(LAST_ADDRESS_SINCE_LAUNCH!!.strAdd)
        } else {
            tilAddress.visibility = View.GONE
            pbAddress.visibility = View.VISIBLE

            checkPermissionsOrgetLocation(l, this,
                onComplete = { location ->
                    val address = getCompleteAddressString(this, location)
                    pbAddress.visibility = View.GONE
                    tilAddress.visibility = View.VISIBLE
                    if (address.strAdd != null) {
                        LAST_ADDRESS_SINCE_LAUNCH = address
                        etAddress.setText(address.strAdd)
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
        val items = listOf<TextImageAnimalType>(
            TextImageAnimalType(l.tr(R.string.cat), R.drawable.ic_cat_mail, AnimalType.CAT),
            TextImageAnimalType(l.tr(R.string.catty), R.drawable.ic_cat_female, AnimalType.CATTY),
            TextImageAnimalType(l.tr(R.string.dog), R.drawable.ic_dog_mail, AnimalType.DOG),
            TextImageAnimalType(l.tr(R.string.doggy), R.drawable.ic_dog_female, AnimalType.DOGGY),
            TextImageAnimalType(l.tr(R.string.other), R.drawable.ic_other, AnimalType.OTHER)
        )

        val adapter = TextImageAdapter(this, items)
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
                val imageId = items[position].image
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

        viewmodel.animalType.observe(this, Observer { animalType ->
            val index = items.indexOfFirst { it.animalType == animalType }
            spinnerType.setSelection(index, true)
        })

        spinnerType.setSelection(adapter.items.size - 1)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

