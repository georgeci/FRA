package arch.screen.person_detail

import arch.domain.interactor.PersonResolution
import arch.screen.persons.screen_state.PersonsScreenState
import extensions.state_machine.StateMachine

class PersonStateMachine(state: PersonScreenState) : StateMachine<PersonScreenState, PersonResolution>(state) {
    override fun change(old: PersonScreenState?, trigger: PersonResolution): PersonScreenState {
        when (old) {
            null -> when (trigger) {
                is PersonResolution.Progress -> PersonsScreenState.Progress
                else -> error("")
            }
            is PersonScreenState.Progress -> when (trigger) {
                is PersonResolution.Content -> PersonScreenState.Content(trigger.item)
                is PersonResolution.Error -> PersonScreenState.Error(trigger.message)
                is PersonResolution.Progress -> error("")
            }
            is PersonScreenState.Content -> when (trigger) {
                is PersonResolution.Progress -> PersonScreenState.ModalProgress(old.person)
                else -> error("")
            }
        }
    }
}