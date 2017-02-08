package arch.screen.persons.presentation.create

import com.jakewharton.rxrelay2.Relay
import extensions.behaviorRelay
import extensions.publishRelay

interface PersonsCreateViewModel {
    val titleState: Relay<String>

    val createPersonCommand: Relay<Any>

    val showToastInteraction: Relay<String>
}

class PersonsCreateViewModelImpl : PersonsCreateViewModel {
    override val titleState = behaviorRelay<String>()

    override val createPersonCommand = publishRelay<Any>()

    override val showToastInteraction = publishRelay<String>()

    fun saveState(saver: (String) -> Unit) {

    }

    fun restoreState(savedState: String?) {

    }
}