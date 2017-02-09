package arch.screen.persons.presentation.android

import arch.screen.persons.features.PersonsViewModel
import io.reactivex.disposables.Disposable

fun bindLifecycleToViewModel(
    lifecycleStreams: LifecycleStreams,
    viewModel: PersonsViewModel
): Disposable =
    lifecycleStreams.firstLaunchStream
        .subscribe(viewModel.refreshCommand)