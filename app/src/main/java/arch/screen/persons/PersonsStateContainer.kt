package arch.screen.persons

import arch.screen.persons.presentation.common.PersonsCommonState
import arch.screen.persons.presentation.create.PersonsCreateState
import arch.screen.persons.presentation.filter.PersonsFilterState
import arch.screen.persons.presentation.list.PersonsListState

class PersonsStateContainer(
    val commonState: PersonsCommonState,
    val createState: PersonsCreateState,
    val filterState: PersonsFilterState,
    val listState: PersonsListState
)