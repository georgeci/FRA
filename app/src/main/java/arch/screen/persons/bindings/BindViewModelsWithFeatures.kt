package arch.screen.persons.bindings

import arch.domain.interactor.GetPersonsInteractor
import arch.screen.persons.features.PersonsViewModel
import arch.screen.persons.features.bindGetPersonsListFeature
import arch.screen.persons.features.bindNavigationToDetailsFeature
import arch.screen.persons.screen_state.PersonsStateMachine
import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindViewModelsWithFeatures(
    viewModel: PersonsViewModel,
    interactor: GetPersonsInteractor,
    stateMachine: PersonsStateMachine,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    bindNavigationToDetailsFeature(
        viewModel,
        viewModel,
        schedulers
    ),
    bindGetPersonsListFeature(
        viewModel,
        viewModel,
        interactor,
        stateMachine,
        schedulers
    )
)