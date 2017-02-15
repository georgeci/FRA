package arch.screen.persons.features

import arch.screen.persons.presentation.list.PersonsListView
import arch.screen.persons.presentation.router.PersonsRouter
import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

fun bindNavigationToDetailsFeature(
    listView: PersonsListView,
    router: PersonsRouter,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    listView.personClicksStream
        .debounce(200, TimeUnit.MILLISECONDS, schedulers.computation())
        .observeOn(schedulers.mainThread())
        .subscribe(router.navigateToDetail)
)