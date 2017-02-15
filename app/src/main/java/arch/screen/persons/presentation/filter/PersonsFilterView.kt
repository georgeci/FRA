package arch.screen.persons.presentation.filter

import android.view.View
import android.widget.EditText
import arch.R
import com.jakewharton.rxbinding.widget.textChanges
import extensions.changeText
import extensions.rx2
import extensions.showToast
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PersonsFilterView {
    val filterValueStream: Observable<String>

    val showToast: Consumer<String>
    val filterInputConsumer: Consumer<String>
}

class PersonsFilterViewImpl(val view: View) : PersonsFilterView {
    private val filterInput = view.findViewById(R.id.filter) as EditText

    override val filterInputConsumer = filterInput.changeText()

    override val filterValueStream: Observable<String> = filterInput.textChanges().rx2().map(CharSequence::toString).share()

    override val showToast = view.showToast()
}