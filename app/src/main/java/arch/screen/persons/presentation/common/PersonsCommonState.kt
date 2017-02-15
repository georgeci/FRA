package arch.screen.persons.presentation.common

import arch.screen.persons.screen_state.PersonsScreenState
import extensions.behaviorRelay

class PersonsCommonState {
    val screenState = behaviorRelay<PersonsScreenState>()
}