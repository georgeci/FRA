package arch.screen.persons.presentation.common

import extensions.SchedulersFactory
import io.reactivex.disposables.Disposable

fun bindPersonsCommonUiWithState(
    view: PersonsCommonView,
    state: PersonsCommonState,
    schedulers: SchedulersFactory
): Disposable =
    state.screenState
        .observeOn(schedulers.mainThread())
        .subscribe(view.changeStateConsumer)
