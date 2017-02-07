package arch.domain.interactor

import arch.domain.model.Person
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

val personInteractor: GetPersonsInteractor =
    { old, schedulers ->
        Observable.just(
            PersonsResolution.Content(listOf(
                Person("1", "Name1", false, "email1@email.ee", listOf()),
                Person("2", "Name2", false, "email2@email.ee", listOf()),
                Person("3", "Name3", false, "email3@email.ee", listOf()),
                Person("4", "Name4", false, "email4@email.ee", listOf()),
                Person("5", "Name5", false, "email5@email.ee", listOf()),
                Person("6", "Name6", false, "email6@email.ee", listOf()),
                Person("7", "Name7", false, "email7@email.ee", listOf()),
                Person("8", "Name8", false, "email8@email.ee", listOf()),
                Person("9", "Name9", false, "email9@email.ee", listOf()),
                Person("10", "Name10", false, "email10@email.ee", listOf()),
                Person("11", "Name11", false, "email11@email.ee", listOf()),
                Person("12", "Name12", false, "email12@email.ee", listOf())
            )) as PersonsResolution
        ).delay(5, TimeUnit.SECONDS, schedulers.io())
    }

sealed class PersonsResolution {
    data class Error(val message: String) : PersonsResolution()
    data class Content(val items: List<Person>) : PersonsResolution()
}