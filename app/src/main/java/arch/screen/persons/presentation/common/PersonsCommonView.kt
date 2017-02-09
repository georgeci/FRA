package arch.screen.persons.presentation.common

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.Toast
import android.widget.ViewAnimator
import arch.R
import com.jakewharton.rxbinding.support.v4.widget.refreshes
import extensions.publishRelay
import extensions.rx2
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface PersonsCommonView {
    val refreshStream: Observable<*>

    val showModalProgress: Consumer<Any>
    val showModalError: Consumer<String>
    val showFullScreenError: Consumer<String>
    val showFullScreenProgress: Consumer<Any>
    val showContent: Consumer<Any>
    val showEmpty: Consumer<Any>
}

class PersonsCommonViewImpl(
    val view: View
) : PersonsCommonView {
    private val switcher = view.findViewById(R.id.switcher) as ViewAnimator
    private val refresh = view.findViewById(R.id.refresh) as SwipeRefreshLayout

    override val refreshStream: Observable<Unit> = refresh.refreshes().rx2()

    override val showContent = Consumer<Any> {
        switcher.displayedChild = 0
        refresh.isRefreshing = false
    }

    override val showEmpty = Consumer<Any> {
        switcher.displayedChild = 2
        refresh.isRefreshing = false
    }

    override val showModalProgress = Consumer<Any> {
        refresh.isRefreshing = true
        Toast.makeText(view.context, "Progress", Toast.LENGTH_SHORT).show()
    }
    override val showModalError = Consumer<String> {
        Toast.makeText(view.context, "Error", Toast.LENGTH_SHORT).show()
        refresh.isRefreshing = false
    }

    override val showFullScreenError = Consumer<String> {
        switcher.displayedChild = 1
        refresh.isRefreshing = false
    }

    override val showFullScreenProgress = Consumer<Any> {
        switcher.displayedChild = 3
        refresh.isRefreshing = false
    }
}