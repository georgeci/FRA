package arch.screen.persons.presentation.list

import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindPersonsListUiWithState(
    view: PersonsListView,
    viewState: PersonsListState,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    viewState.scrollPositionState
        .firstElement()
        .observeOn(schedulers.mainThread())
        .subscribe(view.listScrollPositionConsumer),

    viewState.itemsState
        .observeOn(schedulers.mainThread())
        .subscribe(view.updateItems)
)