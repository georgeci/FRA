package arch.screen.persons.presentation.create

import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindPersonsCreateUiWithState(
    view: PersonsCreateView,
    state: PersonsCreateState,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    view.titleInputStream
        .subscribe(state.titleState),
    state.titleState
        .observeOn(schedulers.mainThread())
        .subscribe(view.titleInputConsumer)
)