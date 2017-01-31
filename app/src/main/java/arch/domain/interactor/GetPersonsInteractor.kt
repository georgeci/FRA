package arch.domain.interactor

import arch.domain.model.Person
import io.reactivex.Observable

val personInteractor: () -> Observable<List<Person>> = {

    Observable.just(
        listOf(
            Person("1", "Name1", false, "email@email.ee", listOf()),
            Person("2", "Name2", false, "email@email.ee", listOf()),
            Person("3", "Name3", false, "email@email.ee", listOf()),
            Person("4", "Name4", false, "email@email.ee", listOf()),
            Person("5", "Name5", false, "email@email.ee", listOf()),
            Person("6", "Name6", false, "email@email.ee", listOf())
        )
    )
}