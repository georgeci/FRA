package arch.screen.persons.presentation.router

import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindPersonsRouterToViewModel(
    router: PersonsRouter,
    viewModel: PersonsRouterViewModel,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    viewModel.navigateToDetailConsumer
        .observeOn(schedulers.mainThread())
        .subscribe(router.navigateToDetail)
)