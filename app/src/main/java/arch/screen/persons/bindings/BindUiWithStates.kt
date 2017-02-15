package arch.screen.persons.bindings

import arch.screen.persons.PersonsStateContainer
import arch.screen.persons.PersonsScreen
import arch.screen.persons.presentation.common.bindPersonsCommonUiWithState
import arch.screen.persons.presentation.create.bindPersonsCreateUiWithState
import arch.screen.persons.presentation.filter.bindPersonsFilterUiWithState
import arch.screen.persons.presentation.list.bindPersonsListUiWithState
import extensions.SchedulersFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun bindUiWithStates(
    ui: PersonsScreen,
    states: PersonsStateContainer,
    schedulers: SchedulersFactory
): Disposable = CompositeDisposable(
    bindPersonsCommonUiWithState(
        ui.commonView,
        states.commonState,
        schedulers
    ),
    bindPersonsCreateUiWithState(
        ui.createView,
        states.createState,
        schedulers
    ),
    bindPersonsListUiWithState(
        ui.listView,
        states.listState,
        schedulers
    ),
    bindPersonsFilterUiWithState(
        ui.filterView,
        states.filterState,
        schedulers
    )
)