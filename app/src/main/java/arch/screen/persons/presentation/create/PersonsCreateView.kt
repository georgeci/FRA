package arch.screen.persons.presentation.create

import android.view.View
import android.widget.EditText
import arch.R
import com.jakewharton.rxbinding.view.clicks
import com.jakewharton.rxbinding.widget.textChanges
import extensions.changeText
import extensions.rx2
import extensions.showToast
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PersonsCreateView {

    val titleInputStream: Observable<String>

    val createPersonClickStream: Observable<*>

    val showToastConsumer: Consumer<String>
    val titleInputConsumer: Consumer<String>
}

class PersonsCreateViewImpl(val view: View) : PersonsCreateView {
    private val newPersonInput = view.findViewById(R.id.title_input) as EditText

    override val titleInputConsumer = newPersonInput.changeText()

    private val newPersonCreateButton = view.findViewById(R.id.create)

    override val titleInputStream: Observable<String> = newPersonInput.textChanges().rx2().map(CharSequence::toString).share()

    override val createPersonClickStream: Observable<Unit> = newPersonCreateButton.clicks().rx2().share()

    override val showToastConsumer = view.showToast()
}