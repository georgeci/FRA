package arch.screen.person_detail

import arch.domain.model.Person
import extensions.behaviorRelay

class PersonState {
    val personIdState = behaviorRelay<String>()

    val personState = behaviorRelay<PersonScreenState>()
}

sealed class PersonScreenState {
    data class Content(val person: Person) : PersonScreenState()
    data class Error(val message: String) : PersonScreenState()
    data class ModalError(val person: Person, val message: String) : PersonScreenState()
    data class ModalProgress(val person: Person) : PersonScreenState()
    object Progress : PersonScreenState()
}