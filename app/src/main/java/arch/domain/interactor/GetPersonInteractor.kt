package arch.domain.interactor

import arch.domain.model.Person
import extensions.SchedulersFactory
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

interface GetPersonInteractor{
    fun get(schedulers: SchedulersFactory, p2: String): Observable<PersonResolution>
}

class GetPersonInteractorImpl : GetPersonInteractor {
    override fun get(schedulers: SchedulersFactory, p2: String): Observable<PersonResolution> {
        return Observable.just(
            PersonResolution.Content(Person("1", "Name1", false, "email1@email.ee", listOf())) as PersonResolution
        ).delay(5, TimeUnit.SECONDS, schedulers.io())
    }
}

sealed class PersonResolution {
    data class Error(val message: String) : PersonResolution()
    data class Content(val item: Person) : PersonResolution()
    object Progress : PersonResolution()
}