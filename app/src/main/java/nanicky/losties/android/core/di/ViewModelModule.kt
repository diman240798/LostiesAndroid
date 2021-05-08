package nanicky.losties.android.core.di

import nanicky.losties.android.features.main.feed.FeedFragmentViewModel
import nanicky.losties.android.features.main.user.UserFragmentViewModel
import nanicky.losties.android.features.publishad.PublishAnimalViewModel
import nanicky.losties.android.features.rateanimals.RateAnimalViewModel
import nanicky.losties.android.features.watchpublication.WatchPublicationActivityViewModel
import nanicky.losties.android.features.watchpublicationlist.WatchPublicationListActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel {
        PublishAnimalViewModel(get())
    }

    viewModel {
        RateAnimalViewModel(get())
    }

    viewModel {
        FeedFragmentViewModel(get())
    }

    viewModel {
        WatchPublicationListActivityViewModel(get())
    }

    viewModel {
        WatchPublicationActivityViewModel(get(named(DEFAULT_RETROFIT_CLIENT)))
    }

    viewModel {
        UserFragmentViewModel(get(), get(), get(named(SOCIAL_NETWORKS_HTTP_CLIENT)))
    }
}