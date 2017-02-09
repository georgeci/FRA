package arch.screen.persons.presentation.list

import arch.domain.model.Person
import extensions.behaviorRelay
import extensions.publishRelay
import org.funktionale.option.Option

class PersonsListViewModel {
    val scrollPositionState = behaviorRelay<Option<Int>>()

    val itemsState = behaviorRelay<List<Person>>(listOf())

    val itemClickCommand = publishRelay<Person>()
}