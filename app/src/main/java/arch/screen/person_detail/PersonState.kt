package arch.screen.person_detail

import arch.domain.model.Person
import extensions.behaviorRelay

class PersonState {
    val personState = behaviorRelay<Person>()
}