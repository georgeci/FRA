package arch.screen.persons.presentation.filter

import extensions.behaviorRelay
import extensions.plusAssign
import extensions.publishRelay
import io.reactivex.disposables.CompositeDisposable

//Maybe generated
class PersonsCreateViewProxy : PersonsFilterView {

    private val disposable = CompositeDisposable()

    override val filterValueStream = behaviorRelay<String>()
    override val showToast = publishRelay<String>()
    
    fun attachView(view: PersonsFilterView) {
        disposable += view.filterValueStream.subscribe(filterValueStream)
        disposable += showToast.subscribe(view.showToast)
    }

    fun detachView() = disposable.dispose()
}