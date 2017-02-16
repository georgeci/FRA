package arch.screen.persons.presentation.list

import arch.domain.model.Person
import com.jakewharton.rxrelay2.Relay
import extensions.behaviorRelay

class PersonsListState {
    val itemsState: Relay<List<Person>> = behaviorRelay<List<Person>>(listOf())
    val scrollPositionState: Relay<Int> = behaviorRelay<Int>()
}