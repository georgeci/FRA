package arch.screen.persons.screen_state

import android.util.Log
import arch.domain.interactor.PersonsResolution
import extensions.state_machine.StateMachine
import io.reactivex.functions.Consumer

class PersonsStateMachine : StateMachine<PersonsListState, PersonsResolution>() {
    override fun change(old: PersonsListState?, trigger: PersonsResolution): PersonsListState {
        Log.d("StateMachineChange", "trigger: ${old?.javaClass?.simpleName ?: "null"} with ${(trigger).javaClass.simpleName}")
        return when (old) {
            null -> {
                when (trigger) {
                    is PersonsResolution.Error -> PersonsListState.Error(trigger.message)
                    is PersonsResolution.Content -> when (trigger.items.size) {
                        0 -> PersonsListState.Empty
                        else -> PersonsListState.Content
                    }
                    else -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
                }
            }
            is PersonsListState.Progress -> {
                when (trigger) {
                    is PersonsResolution.Error -> PersonsListState.Error(trigger.message)
                    is PersonsResolution.Content -> when (trigger.items.size) {
                        0 -> PersonsListState.Empty
                        else -> PersonsListState.Content
                    }
                    else -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
                }
            }
            is PersonsListState.ModalProgress -> {
                when (trigger) {
                    is PersonsResolution.Error -> PersonsListState.ModalError(trigger.message)
                    is PersonsResolution.Content -> PersonsListState.Content
                    else -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
                }
            }
            is PersonsListState.Error -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
            is PersonsListState.ModalError -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
            is PersonsListState.Empty -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
            is PersonsListState.Content -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
        }.apply {
            Log.d("StateMachineChange", "change: ${old?.javaClass?.simpleName ?: "null"} -> ${this.javaClass.simpleName}")
        }
    }

    val changeModalErrorToContent = Consumer<Any> {
        Log.d("StateMachineChange", "change: ${getCurrentValue()?.javaClass?.simpleName ?: "null"} -> Content")
        getCurrentValue() as? PersonsListState.ModalError ?: error("")
        setCurrentValue(PersonsListState.Content)
    }

    val startProgress = Consumer<Any> {
        val state = getCurrentValue()
        Log.d("StateMachineChange", "change: ${state?.javaClass?.simpleName ?: "null"} -> any progress")
        when (state) {
            null -> PersonsListState.Progress
            is PersonsListState.Error -> PersonsListState.Progress
            is PersonsListState.Empty -> PersonsListState.Progress
            is PersonsListState.Content -> PersonsListState.ModalProgress
            else -> error("Unsupported screenState: ${state.javaClass.canonicalName} for starting progress")
        }.apply {
            setCurrentValue(this)
        }
    }
}