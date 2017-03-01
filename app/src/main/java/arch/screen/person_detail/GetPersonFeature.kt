package arch.screen.person_detail

import arch.domain.interactor.GetPersonInteractor
import arch.screen.persons.presentation.android.LifecycleStreams
import extensions.SchedulersFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun getPersonFeature(
    inputView: PersonInputView,
    outputView: PersonOutputView,
    state: PersonState,
    scheduler: SchedulersFactory,
    interactor: GetPersonInteractor,
    lifecycleStreams: LifecycleStreams,
    personStateMachine: PersonStateMachine
): Disposable {
    return CompositeDisposable(
        state.personIdState
            .switchMap { id ->
                Observable.merge(
                    outputView.refreshStream,
                    lifecycleStreams.firstLaunchStream
                )
                    .switchMap { _ ->
                        interactor.get(scheduler, id)
                    }
            }
            .subscribe(personStateMachine.trigger),

        personStateMachine.observe()
            .subscribe(state.personState)
    )
}