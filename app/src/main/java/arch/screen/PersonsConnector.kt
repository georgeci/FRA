package arch.screen

import arch.domain.model.Person
import arch.screen.persons.PersonsRouter
import arch.screen.persons.PersonsView
import extensions.behaviorRelay
import extensions.plusAssign
import extensions.publishRelay
import io.reactivex.disposables.CompositeDisposable

class PersonsConnector : PersonsView, PersonsRouter {
    override val navigateToDetail = publishRelay<Person>()
    override val personClicksStream = publishRelay<Person>()
    override val refreshesStream = publishRelay<Unit>()
    override val updatePersonsList = behaviorRelay<List<Person>>()
    override val showProgress = behaviorRelay<Unit>()

    val viewDisposable = CompositeDisposable()
    val routerDisposable = CompositeDisposable()

    fun attachView(view: PersonsView) {
        viewDisposable.addAll(
            view.personClicksStream.subscribe(personClicksStream),
            view.refreshesStream.subscribe(refreshesStream),
            showProgress.subscribe(view.showProgress),
            updatePersonsList.subscribe(view.updatePersonsList)
        )
    }

    fun attachRouter(router: PersonsRouter) {
        routerDisposable += navigateToDetail.subscribe(router.navigateToDetail)
    }

    fun dettachView() {
        viewDisposable.clear()
    }

    fun dettachRouter() {
        routerDisposable.clear()
    }
}