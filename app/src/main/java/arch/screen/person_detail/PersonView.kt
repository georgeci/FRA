package arch.screen.person_detail

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.Button
import android.widget.TextView
import arch.R
import arch.domain.model.Person
import com.jakewharton.rxbinding.support.v4.widget.refreshes
import com.jakewharton.rxbinding.view.clicks
import extensions.SchedulersFactory
import extensions.rx2
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PersonInputView {
    val showContent: Consumer<Person>

    val showScreenState: Consumer<PersonScreenState>
}

interface PersonOutputView {
    val editClickStream: Observable<*>
    val refreshStream: Observable<Unit>
}

class PersonViewImpl(
    view: View,
    schedulers: SchedulersFactory
) : PersonInputView,
    PersonOutputView {

    val refresher = view.findViewById(R.id.refresh) as SwipeRefreshLayout
    val title = view.findViewById(R.id.person_title) as TextView
    val editButton = view.findViewById(R.id.edit_button) as Button

    override val editClickStream: Observable<Unit> = editButton
        .clicks()
        .rx2()
        .share()
        .subscribeOn(schedulers.mainThread())

    override val refreshStream: Observable<Unit> = refresher
        .refreshes()
        .rx2()
        .share()
        .subscribeOn(schedulers.mainThread())

    override val showContent = Consumer<Person> {
        title.text = it.name
    }

    override val showScreenState = Consumer<PersonScreenState> {

        when (it) {

        }
    }
}