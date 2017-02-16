package arch.screen.persons.bindings

import arch.domain.interactor.GetPersonsInteractor
import arch.screen.persons.PersonsStateContainer
import arch.screen.persons.PersonsScreen
import arch.screen.persons.features.bindGetPersonsListFeature
import arch.screen.persons.features.bindNavigationToDetailsFeature
import arch.screen.persons.presentation.android.LifecycleStreams
import arch.screen.persons.screen_state.PersonsStateMachine
import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindScreenWithFeatures(
    ui: PersonsScreen,
    states: PersonsStateContainer,
    stateMachine: PersonsStateMachine,
    lifecycleStreams: LifecycleStreams,
    interactor: GetPersonsInteractor,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    bindNavigationToDetailsFeature(
        ui.listView,
        ui.router,
        schedulers
    ),
    bindGetPersonsListFeature(
        listView = ui.listView,
        listState = states.listState,
        commonView = ui.commonView,
        interactor = interactor,
        commonState = states.commonState,
        stateMachine = stateMachine,
        schedulers = schedulers,
        lifecycleStreams = lifecycleStreams
    )
)