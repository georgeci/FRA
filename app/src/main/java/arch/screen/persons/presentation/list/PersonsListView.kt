package arch.screen.persons.presentation.list

import android.support.v7.widget.RecyclerView
import android.view.View
import arch.R
import arch.domain.model.Person
import arch.screen.persons.presentation.list.PersonAdapter
import com.jakewharton.rxbinding.support.v7.widget.scrollStateChanges
import extensions.rx2
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PersonsListView {
    val listScrollPositionStream: Observable<Int>
    val listScrollPositionConsumer: Consumer<Int>

    val personClicksStream: Observable<Person>

    val updateItems: Consumer<List<Person>>
}

class PersonsListViewImpl(
    val view: View,
    val adapter: PersonAdapter,
    val layoutManager: RecyclerView.LayoutManager
) : PersonsListView {
    private val list = view.findViewById(R.id.list) as RecyclerView

    override val listScrollPositionStream: Observable<Int> = list.scrollStateChanges().rx2().share()

    override val personClicksStream: Observable<Person> = adapter.clicks.share()

    override val listScrollPositionConsumer = Consumer<Int> {
        list.scrollToPosition(it)
    }

    override val updateItems: Consumer<List<Person>> = adapter.update

    init {
        list.layoutManager = layoutManager
        list.adapter = adapter
    }
}