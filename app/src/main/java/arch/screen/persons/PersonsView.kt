package arch.screen.persons

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import arch.R
import arch.domain.model.Person
import com.jakewharton.rxbinding.support.v4.widget.refreshes
import extensions.rx2
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PersonsView {
    val personClicksStream: Observable<Person>
    val refreshesStream: Observable<Unit>

    val updatePersonsList: Consumer<List<Person>>
    val showProgress: Consumer<Unit>
}

class PersonsViewImpl(
    view: View,
    adapter: PersonAdapter,
    layoutManager: RecyclerView.LayoutManager
) : PersonsView {
    private val list = view.findViewById(R.id.list) as RecyclerView

    private val refresh = view.findViewById(R.id.refresh) as SwipeRefreshLayout

    init {
        list.layoutManager = layoutManager
        list.adapter = adapter
    }

    override val refreshesStream = this.refresh.refreshes().rx2()

    override val personClicksStream = adapter.clicks

    override val updatePersonsList: Consumer<List<Person>> = Consumer {
        with(adapter.items) {
            clear()
            addAll(it)
        }
        adapter.notifyDataSetChanged()
    }

    override val showProgress: Consumer<Unit> = Consumer {
        Toast.makeText(view.context, "Progress", Toast.LENGTH_SHORT).show()
    }
}