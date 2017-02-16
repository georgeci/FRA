package arch.domain.interactor

import arch.domain.model.Person
import extensions.SchedulersFactory
import io.reactivex.Observable

typealias GetPersonsInteractor = (List<Person>, SchedulersFactory) -> Observable<PersonsResolution>
typealias CreatePersonsInteractor = (String, SchedulersFactory) -> Observable<CreatePersonsResolution>