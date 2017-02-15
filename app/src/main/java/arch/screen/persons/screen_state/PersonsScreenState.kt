package arch.screen.persons.screen_state

sealed class PersonsScreenState {
    object Empty : PersonsScreenState()
    object Content : PersonsScreenState()
    object Progress : PersonsScreenState()
    data class Error(val message: String) : PersonsScreenState()
    object ModalProgress : PersonsScreenState()
    data class ModalError(val message: String) : PersonsScreenState()
}