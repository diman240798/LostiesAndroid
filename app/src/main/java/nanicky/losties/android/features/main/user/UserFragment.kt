package nanicky.losties.android.features.main.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.vk.api.sdk.VK
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.fragment_user.view.*
import kotlinx.android.synthetic.main.read_publications.*
import kotlinx.android.synthetic.main.read_publications.view.*
import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import nanicky.losties.android.core.base.Localizer
import nanicky.losties.android.core.data.local.AuthNotifier
import nanicky.losties.android.core.data.local.AuthType
import nanicky.losties.android.core.data.local.UserRepository
import nanicky.losties.android.core.extensions.*
import nanicky.losties.android.core.utils.FaceBookUtil
import nanicky.losties.android.core.utils.FileUtils.AVATAR_THUMBNAIL
import nanicky.losties.android.features.enums.PublicationTypes
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.File
import java.util.*

class UserFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()

        const val TAG = "UserFragment"
    }

    private lateinit var callbackManager: CallbackManager

    private val l: Localizer by inject()
    private val authNotifier: AuthNotifier by inject()
    private val userRepository: UserRepository by inject()
    private val viewModel: UserFragmentViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!userRepository.isAuthorized()) {
            initRegister(view)
        } else {
            initLogin()
        }

        // WATCH
        view.clReadLostAnimal.setOnClickListener {
            requireActivity().goToWatchPublicationsListActivity(PublicationTypes.LOST, userRepository.getId())
        }

        view.clReadTakenAnimal.setOnClickListener {
            requireActivity().goToWatchPublicationsListActivity(PublicationTypes.TAKEN, userRepository.getId())
        }

        view.clReadSeenAnimal.setOnClickListener {
            requireActivity().goToWatchPublicationsListActivity(PublicationTypes.SEEN, userRepository.getId())
        }


    }

    private fun initLogin() {
        btWhyRegister.gone()
        clRootVk.gone()
        clRootFacebook.gone()
        clRootReadPublication.visible()
        tvUserNameBelowPhoto.visible()
        tvUserNameBelowPhoto.text = userRepository.fullName()
        ivPhotoUser.loadImage(File(requireContext().filesDir, AVATAR_THUMBNAIL))
    }

    private fun initRegister(view: View) {
        btWhyRegister.visible()
        clRootVk.visible()
        clRootFacebook.visible()
        clRootReadPublication.gone()
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Timber.d("Successful facebook login. Try to get user info...")
                    if (loginResult == null) return
                    loginResult.accessToken?.let {
                        FaceBookUtil.getFacebookUserOrNull(
                            loginResult.accessToken,
                            authNotifier
                        )
                    }
                }

                override fun onCancel() {}

                override fun onError(exception: FacebookException) {
                    context?.showToast(l.tr(R.string.error_facebook_user_get))
                }
            })

        view.btWhyRegister.setOnClickListener {
            requireContext().showInfoAlertDialog(
                l,
                l.tr(R.string.why_should_i_register),
                l.tr(R.string.why_should_i_register_description)
            )
        }

        view.clRootFacebook.setOnClickListener {
            Timber.d("Started login via Facebook...")
            val accessToken = AccessToken.getCurrentAccessToken()
            if (accessToken != null && !accessToken.isExpired) { // если мы залогинись в фейсбук, но не смогли в бек енд
                FaceBookUtil.getFacebookUserOrNull(accessToken, authNotifier)
            } else {
                LoginManager.getInstance().logIn(this, Collections.emptyList())
            }
        }

        view.clRootVk.setOnClickListener {
            Timber.d("Started login via VK...")
            VK.login(requireActivity())
        }

        (requireActivity() as BaseActivity).authNotifier.vkUid.observe(viewLifecycleOwner, Observer {
            Timber.d("VK user notified... Starting signIn via backend...")
            viewModel.signIn(
                userId = it.id.toString(),
                firstName = it.firstName,
                lastName = it.lastName,
                photo = it.photo,
                authType = AuthType.vk
            )
            initLogin()
        })

        (requireActivity() as BaseActivity).authNotifier.facebookUid.observe(viewLifecycleOwner, Observer {
            Timber.d("Facebook user notified... Starting signIn via backend...")
            viewModel.signIn(
                userId = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                photo = it.photo,
                authType = AuthType.facebook
            )
        })

        viewModel.contentState.observe(viewLifecycleOwner, Observer {
            initLogin()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null || !callbackManager.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}