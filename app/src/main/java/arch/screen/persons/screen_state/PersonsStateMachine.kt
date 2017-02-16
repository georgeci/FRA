package arch.screen.persons.screen_state

import android.util.Log
import arch.domain.interactor.PersonsResolution
import extensions.state_machine.StateMachine
import io.reactivex.functions.Consumer

class PersonsStateMachine : StateMachine<PersonsScreenState, PersonsResolution>() {
    override fun change(old: PersonsScreenState?, trigger: PersonsResolution): PersonsScreenState {
        Log.d("StateMachineChange", "trigger: ${old?.javaClass?.simpleName ?: "null"} with ${(trigger).javaClass.simpleName}")
        return when (old) {
            null -> {
                when (trigger) {
                    is PersonsResolution.Error -> PersonsScreenState.Error(trigger.message)
                    is PersonsResolution.Content -> when (trigger.items.size) {
                        0 -> PersonsScreenState.Empty
                        else -> PersonsScreenState.Content
                    }
                    else -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
                }
            }
            is PersonsScreenState.Progress -> {
                when (trigger) {
                    is PersonsResolution.Error -> PersonsScreenState.Error(trigger.message)
                    is PersonsResolution.Content -> when (trigger.items.size) {
                        0 -> PersonsScreenState.Empty
                        else -> PersonsScreenState.Content
                    }
                    else -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
                }
            }
            is PersonsScreenState.ModalProgress -> {
                when (trigger) {
                    is PersonsResolution.Error -> PersonsScreenState.ModalError(trigger.message)
                    is PersonsResolution.Content -> PersonsScreenState.Content
                    else -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
                }
            }
            is PersonsScreenState.Error -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
            is PersonsScreenState.ModalError -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
            is PersonsScreenState.Empty -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
            is PersonsScreenState.Content -> error("Unsupported trigger: ${trigger.javaClass.canonicalName}")
        }.apply {
            Log.d("StateMachineChange", "change: ${old?.javaClass?.simpleName ?: "null"} -> ${this.javaClass.simpleName}")
        }
    }

    val changeModalErrorToContent = Consumer<Any> {
        Log.d("StateMachineChange", "change: ${getCurrentValue()?.javaClass?.simpleName ?: "null"} -> Content")
        getCurrentValue() as? PersonsScreenState.ModalError ?: error("")
        setCurrentValue(PersonsScreenState.Content)
    }

    val startProgress = Consumer<Any> {
        val state = getCurrentValue()
        Log.d("StateMachineChange", "change: ${state?.javaClass?.simpleName ?: "null"} -> any progress")
        when (state) {
            null -> PersonsScreenState.Progress
            is PersonsScreenState.Error -> PersonsScreenState.Progress
            is PersonsScreenState.Empty -> PersonsScreenState.Progress
            is PersonsScreenState.Content -> PersonsScreenState.ModalProgress
            else -> error("Unsupported screenState: ${state.javaClass.canonicalName} for starting progress")
        }.apply {
            setCurrentValue(this)
        }
    }
}