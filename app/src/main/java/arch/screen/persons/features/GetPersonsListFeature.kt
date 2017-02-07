package arch.screen.persons.features

import android.util.Log
import arch.domain.interactor.GetPersonsInteractor
import arch.domain.interactor.PersonsResolution
import arch.screen.persons.presentation.common.PersonsCommonViewModel
import arch.screen.persons.presentation.list.PersonsListViewModel
import arch.screen.persons.screen_state.PersonsStateMachine
import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindGetPersonsListFeature(
    listViewModel: PersonsListViewModel,
    commonViewModel: PersonsCommonViewModel,
    interactor: GetPersonsInteractor,
    stateMachine: PersonsStateMachine,
    schedulers: SchedulersFactory
): Disposable {
    val connectable = listViewModel.itemsState
        .doOnNext { Log.d("QOQ items", it.javaClass.simpleName) }
        .switchMap { current ->
            commonViewModel.refreshCommand
                .doOnNext { Log.d("QOQ refresh", it.javaClass.simpleName) }
                .switchMap { interactor(current, schedulers) }
        }.publish()
    return CompositeDisposable(
        connectable
            .subscribe(stateMachine.trigger),

        connectable.ofType(PersonsResolution.Content::class.java)
            .map { it.items }
            .subscribe(listViewModel.itemsState),
        commonViewModel.refreshCommand.subscribe(stateMachine.startProgress),

        stateMachine.observe().subscribe(commonViewModel.screenState),
        connectable.connect()
    )
}