package arch.screen.persons

import arch.screen.persons.presentation.common.PersonsCommonView
import arch.screen.persons.presentation.create.PersonsCreateView
import arch.screen.persons.presentation.filter.PersonsFilterView
import arch.screen.persons.presentation.list.PersonsListView
import arch.screen.persons.presentation.router.PersonsRouter

class PersonsScreen(
    val commonView: PersonsCommonView,
    val createView: PersonsCreateView,
    val filterView: PersonsFilterView,
    val listView: PersonsListView,
    val router: PersonsRouter
)