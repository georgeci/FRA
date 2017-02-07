package arch.screen.persons.presentation.create

import android.view.View
import android.widget.Button
import android.widget.EditText
import arch.R
import com.jakewharton.rxbinding.view.clicks
import com.jakewharton.rxbinding.widget.textChanges
import extensions.changeText
import extensions.rx2
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PersonsCreateView {
    val titleStream: Observable<String>
    val titleConsumer: Consumer<String>

    val createClickStream: Observable<Unit>
}

class PersonsCreateViewImpl(val view: View) : PersonsCreateView {
    private val titleInput = view.findViewById(R.id.title_input) as EditText
    private val createButton = view.findViewById(R.id.create) as Button

    override val titleStream: Observable<String> = titleInput.textChanges().rx2().map(CharSequence::toString)
    override val titleConsumer = titleInput.changeText()
    override val createClickStream = createButton.clicks().rx2()
}
