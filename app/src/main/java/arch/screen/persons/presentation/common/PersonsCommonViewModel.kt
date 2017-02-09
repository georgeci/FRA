package arch.screen.persons.presentation.common

import arch.screen.persons.screen_state.PersonsListState
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay

interface PersonsCommonViewModel {
    val screenState: BehaviorRelay<PersonsListState>

    val refreshCommand: PublishRelay<Any>
}