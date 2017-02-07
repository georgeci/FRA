package arch.screen.persons.screen_state

sealed class PersonsListState {
    object Empty : PersonsListState()
    object Content : PersonsListState()
    object Progress : PersonsListState()
    data class Error(val message: String) : PersonsListState()
    object ModalProgress : PersonsListState()
    data class ModalError(val message: String) : PersonsListState()
}