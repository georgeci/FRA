package arch.screen.persons.features

import arch.domain.model.Person
import arch.screen.persons.presentation.common.PersonsCommonViewModel
import arch.screen.persons.presentation.router.PersonsRouterViewModel
import arch.screen.persons.screen_state.PersonsListState
import extensions.behaviorRelay
import extensions.publishRelay

class PersonsViewModel :
    PersonsCommonViewModel,
    PersonsRouterViewModel {
    override val screenState = behaviorRelay<PersonsListState>()

    override val refreshCommand = publishRelay<Any>()

    override val navigateToDetailConsumer = publishRelay<Person>()

    fun saveState() {

    }

    fun restoreState() {

    }
}