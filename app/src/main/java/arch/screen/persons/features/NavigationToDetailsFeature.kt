package arch.screen.persons.features

import arch.screen.persons.presentation.list.PersonsListViewModel
import arch.screen.persons.presentation.router.PersonsRouterViewModel
import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

fun bindNavigationToDetailsFeature(
    listViewModel: PersonsListViewModel,
    routerViewModel: PersonsRouterViewModel,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    listViewModel.itemClickCommand
        .debounce(200, TimeUnit.MILLISECONDS, schedulers.computation())
        .subscribe(routerViewModel.navigateToDetailConsumer)
)