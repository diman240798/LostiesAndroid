package nanicky.losties.android.core.di

import nanicky.losties.android.features.main.feed.FeedFragmentViewModel
import nanicky.losties.android.features.publishad.PublishAdAnimalViewModel
import nanicky.losties.android.features.rateanimals.RateAnimalViewModel
import nanicky.losties.android.features.watchad.WatchAdActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel {
        PublishAdAnimalViewModel(get())
    }

    viewModel {
        RateAnimalViewModel(get())
    }

    viewModel {
        FeedFragmentViewModel(get())
    }

    viewModel {
        WatchAdActivityViewModel(get())
    }
}