package arch.screen.persons.presentation.create

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay

interface PersonsCreateViewModel {
    val newPersonInputState: BehaviorRelay<String>

    val createPersonCommand: PublishRelay<Unit>
}