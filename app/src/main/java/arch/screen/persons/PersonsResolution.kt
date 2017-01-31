package arch.screen.persons

import arch.domain.model.Person

sealed class PersonsResolution {
    class Valid(val items: List<Person>) : PersonsResolution()
    object Progress : PersonsResolution()
}