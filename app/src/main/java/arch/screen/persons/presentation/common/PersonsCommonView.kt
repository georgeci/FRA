package arch.screen.persons.presentation.common

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.Toast
import android.widget.ViewAnimator
import arch.R
import arch.screen.persons.screen_state.PersonsScreenState
import com.jakewharton.rxbinding.support.v4.widget.refreshes
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import extensions.publishRelay
import extensions.rx2
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PersonsCommonView {
    val refreshStream: Observable<*>

    val changeStateConsumer: Consumer<PersonsScreenState>
}

class PersonsCommonViewImpl(
    val view: View
) : PersonsCommonView {
    private val refreshRelay = publishRelay<Any>()

    private val switcher = view.findViewById(R.id.switcher) as ViewAnimator
    private val refresh = view.findViewById(R.id.refresh) as SwipeRefreshLayout
    override val refreshStream: Observable<Any> = refreshRelay

    override val changeStateConsumer = Consumer<PersonsScreenState> {
        when (it) {
            is PersonsScreenState.Content -> showContent()
            is PersonsScreenState.Empty -> showEmpty()
            is PersonsScreenState.Error -> showFullScreenError(it.message)
            is PersonsScreenState.ModalError -> showModalError(it.message)
            is PersonsScreenState.Progress -> showFullScreenProgress()
            is PersonsScreenState.ModalProgress -> showModalProgress()
        }
    }

    init {
        refresh.refreshes().rx2()
            .bindToLifecycle(refresh)
            .subscribe(refreshRelay)
    }

    fun showContent() {
        switcher.displayedChild = 0
        refresh.isRefreshing = false
    }

    fun showEmpty() {
        switcher.displayedChild = 2
        refresh.isRefreshing = false
    }

    fun showModalProgress() {
        refresh.isRefreshing = true
        Toast.makeText(view.context, "Progress", Toast.LENGTH_SHORT).show()
    }

    fun showModalError(value: String) {
        Toast.makeText(view.context, "Error", Toast.LENGTH_SHORT).show()
        refresh.isRefreshing = false
    }

    fun showFullScreenError(value: String) {
        switcher.displayedChild = 1
        refresh.isRefreshing = false
    }

    fun showFullScreenProgress() {
        switcher.displayedChild = 3
        refresh.isRefreshing = false
    }
}