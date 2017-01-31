package arch.screen.persons

import arch.domain.model.Person
import arch.screen.PersonsConnector
import extensions.plusAssign
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class PersonsPresenter(
    val connector: PersonsConnector,
    interactor: () -> Observable<List<Person>>
) {
    val disposable = CompositeDisposable()

    init {

        disposable += connector
            .personClicksStream
            .subscribe(connector.navigateToDetail)

        val dataStream = Observable.just(PersonsResolution.Progress)
            .flatMap { interactor() }
            .map { PersonsResolution.Valid(it) as PersonsResolution }
            .startWith(PersonsResolution.Progress)
            .publish()

        disposable += dataStream
            .ofType(PersonsResolution.Valid::class.java)
            .map { it.items }
            .subscribe(connector.updatePersonsList)
        disposable += dataStream
            .ofType(PersonsResolution.Progress::class.java)
            .map { Unit }
            .subscribe(connector.showProgress)
        disposable += dataStream.connect()
    }

    fun dispose() = disposable.dispose()
}