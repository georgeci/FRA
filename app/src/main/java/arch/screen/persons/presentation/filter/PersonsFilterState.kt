package arch.screen.persons.presentation.filter

import extensions.behaviorRelay

class PersonsFilterState {

    val filterInputState = behaviorRelay<String>()
}