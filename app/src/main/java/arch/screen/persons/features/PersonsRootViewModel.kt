package arch.screen.persons.features

import arch.domain.model.Person
import arch.screen.persons.presentation.common.PersonsCommonViewModel
import arch.screen.persons.presentation.create.PersonsCreateViewModel
import arch.screen.persons.presentation.list.PersonsListViewModel
import arch.screen.persons.presentation.router.PersonsRouterViewModel
import arch.screen.persons.screen_state.PersonsListState
import extensions.behaviorRelay
import extensions.publishRelay
import io.reactivex.disposables.CompositeDisposable
import org.funktionale.option.Option

class PersonsViewModel :
    PersonsCommonViewModel,
    PersonsListViewModel,
    PersonsCreateViewModel,
    PersonsRouterViewModel {
    override val screenState = behaviorRelay<PersonsListState>()
    override val scrollPositionState = behaviorRelay<Option<Int>>()
    override val newPersonInputState = behaviorRelay<String>()

    override val itemsState = behaviorRelay<List<Person>>(listOf())

    override val refreshCommand = publishRelay<Any>()
    override val itemClickCommand = publishRelay<Person>()

    override val createPersonCommand = publishRelay<Unit>()

    override val navigateToDetailConsumer = publishRelay<Person>()

    fun saveState(saver: (SavedState) -> Unit) {

    }

    fun restoreState(savedState: SavedState?) {

    }
}

data class SavedState(
    val listState: PersonsListState,
    val scrollState: Option<Int>,
    val newPersonInputState: String
)