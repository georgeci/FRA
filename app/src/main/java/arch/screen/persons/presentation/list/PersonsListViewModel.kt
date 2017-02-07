package arch.screen.persons.presentation.list

import arch.domain.model.Person
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import org.funktionale.option.Option

interface PersonsListViewModel {
    val itemsState: BehaviorRelay<List<Person>>
    val scrollPositionState: BehaviorRelay<Option<Int>>

    val itemClickCommand: PublishRelay<Person>
}