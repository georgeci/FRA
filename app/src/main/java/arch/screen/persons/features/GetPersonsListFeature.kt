package arch.screen.persons.features

import arch.domain.interactor.GetPersonsInteractor
import arch.domain.interactor.PersonsResolution
import arch.screen.persons.presentation.android.LifecycleStreams
import arch.screen.persons.presentation.common.PersonsCommonState
import arch.screen.persons.presentation.common.PersonsCommonView
import arch.screen.persons.presentation.list.PersonsListState
import arch.screen.persons.presentation.list.PersonsListView
import arch.screen.persons.screen_state.PersonsStateMachine
import extensions.SchedulersFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindGetPersonsListFeature(
    listView: PersonsListView,
    listState: PersonsListState,
    commonView: PersonsCommonView,
    lifecycleStreams: LifecycleStreams,
    commonState: PersonsCommonState,
    interactor: GetPersonsInteractor,
    stateMachine: PersonsStateMachine,
    schedulers: SchedulersFactory
): Disposable {
    val connectable = listState.itemsState
        .switchMap { current ->
            Observable.merge(
                commonView.refreshStream,
                lifecycleStreams.firstLaunchStream
            )
                .doOnNext { stateMachine.startProgress.accept(Unit) }
                .switchMap { interactor(current, schedulers) }
        }.publish()

    return CompositeDisposable(
        connectable
            .subscribe(stateMachine.trigger),

        connectable.ofType(PersonsResolution.Content::class.java)
            .map { it.items }
            .subscribe(listState.itemsState),

        stateMachine.observe().subscribe(commonState.screenState),
        connectable.connect()
    )
}