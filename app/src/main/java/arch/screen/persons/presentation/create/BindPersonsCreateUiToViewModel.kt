package arch.screen.persons.presentation.create

import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindPersonsCreateUiWithViewModel(
    view: PersonsCreateView,
    viewModel: PersonsCreateViewModel,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    view.createClickStream
        .subscribe(viewModel.createPersonCommand),
    view.titleStream
        .subscribe(viewModel.newPersonInputState),
    viewModel.newPersonInputState
        .observeOn(schedulers.mainThread())
        .subscribe(view.titleConsumer)
)