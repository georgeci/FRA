package arch.screen.persons.presentation.create

import com.jakewharton.rxbinding.view.clicks
import com.jakewharton.rxbinding.widget.textChanges
import extensions.SchedulersFactory
import extensions.changeText
import extensions.rx2
import extensions.showToast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindPersonsCreateUiWithViewModel(
    viewHolder: PersonsCreateViewHolder,
    viewModel: PersonsCreateViewModel,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    viewHolder.titleInput.textChanges().rx2()
        .map(CharSequence::toString)
        .subscribe(viewModel.titleState),
    viewModel.titleState
        .observeOn(schedulers.mainThread())
        .subscribe(viewHolder.titleInput.changeText()),
    viewHolder.createButton.clicks().rx2()
        .subscribe(viewModel.createPersonCommand),
    viewModel.showToastInteraction
        .observeOn(schedulers.mainThread())
        .subscribe(viewHolder.view.showToast())
)