package arch.screen.persons.presentation.router

import arch.domain.model.Person
import com.jakewharton.rxrelay2.PublishRelay

interface PersonsRouterViewModel {
    val navigateToDetailConsumer: PublishRelay<Person>
}