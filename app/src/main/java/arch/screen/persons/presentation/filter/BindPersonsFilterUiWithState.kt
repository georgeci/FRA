package arch.screen.persons.presentation.filter

import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindPersonsFilterUiWithState(
    view: PersonsFilterView,
    state: PersonsFilterState,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    view.filterValueStream
        .subscribe(state.filterInputState),
    state.filterInputState
        .observeOn(schedulers.mainThread())
        .subscribe(view.filterInputConsumer)
)