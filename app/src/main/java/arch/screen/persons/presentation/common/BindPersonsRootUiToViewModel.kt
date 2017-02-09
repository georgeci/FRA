package arch.screen.persons.presentation.common

import arch.screen.persons.screen_state.PersonsListState
import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindPersonsCommonUiWithViewModel(
    view: PersonsCommonView,
    viewModel: PersonsCommonViewModel,
    schedulers: SchedulersFactory
): Disposable {
    val connectableStateStream = viewModel.screenState.publish()
    return CompositeDisposable(
        view.refreshStream
            .subscribe(viewModel.refreshCommand),

        connectableStateStream.ofType(PersonsListState.Content::class.java)
            .observeOn(schedulers.mainThread())
            .subscribe(view.showContent),
        connectableStateStream.ofType(PersonsListState.Empty::class.java)
            .observeOn(schedulers.mainThread())
            .subscribe(view.showEmpty),
        connectableStateStream.ofType(PersonsListState.Error::class.java)
            .observeOn(schedulers.mainThread()).map(PersonsListState.Error::message)
            .subscribe(view.showFullScreenError),
        connectableStateStream.ofType(PersonsListState.Progress::class.java)
            .observeOn(schedulers.mainThread())
            .subscribe(view.showFullScreenProgress),
        connectableStateStream.ofType(PersonsListState.ModalError::class.java)
            .observeOn(schedulers.mainThread()).map(PersonsListState.ModalError::message)
            .subscribe(view.showModalError),
        connectableStateStream.ofType(PersonsListState.ModalProgress::class.java)
            .observeOn(schedulers.mainThread())
            .subscribe(view.showModalProgress),

        connectableStateStream.connect()
    )
}