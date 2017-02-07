package arch.screen.persons.bindings

import arch.screen.persons.features.PersonsViewModel
import arch.screen.persons.presentation.android.LifecycleStreams
import arch.screen.persons.presentation.android.bindLifecycleToViewModel
import arch.screen.persons.presentation.common.PersonsCommonView
import arch.screen.persons.presentation.common.bindPersonsCommonUiWithViewModel
import arch.screen.persons.presentation.create.PersonsCreateView
import arch.screen.persons.presentation.create.bindPersonsCreateUiWithViewModel
import arch.screen.persons.presentation.list.PersonsListView
import arch.screen.persons.presentation.list.bindPersonsListUiWithViewModel
import arch.screen.persons.presentation.router.PersonsRouter
import arch.screen.persons.presentation.router.bindPersonsRouterToViewModel
import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindRootUiWithViewModel(
    commonView: PersonsCommonView,
    listView: PersonsListView,
    createView: PersonsCreateView,
    router: PersonsRouter,
    lifecycleStreams: LifecycleStreams,
    viewModel: PersonsViewModel,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    bindPersonsCommonUiWithViewModel(
        commonView,
        viewModel,
        schedulers
    ),
    bindPersonsCreateUiWithViewModel(
        createView,
        viewModel,
        schedulers
    ),
    bindPersonsListUiWithViewModel(
        listView,
        viewModel,
        schedulers
    ),
    bindPersonsRouterToViewModel(
        router,
        viewModel,
        schedulers
    ),
    bindLifecycleToViewModel(
        lifecycleStreams,
        viewModel
    )
)