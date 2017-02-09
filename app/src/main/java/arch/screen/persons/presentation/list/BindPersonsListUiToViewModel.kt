package arch.screen.persons.presentation.list

import extensions.SchedulersFactory
import extensions.filterDefined
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.funktionale.option.toOption

fun bindPersonsListUiWithViewModel(
    view: PersonsListView,
    viewModel: PersonsListViewModel,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    view.personClicksStream
        .subscribe(viewModel.itemClickCommand),

    view.listScrollPositionStream
        .map { it.toOption() }
        .subscribe(viewModel.scrollPositionState),

    viewModel.scrollPositionState
        .firstElement()
        .filterDefined()
        .observeOn(schedulers.mainThread())
        .subscribe(view.listScrollPositionConsumer),

    viewModel.itemsState
        .observeOn(schedulers.mainThread())
        .subscribe(view.updateItems)
)