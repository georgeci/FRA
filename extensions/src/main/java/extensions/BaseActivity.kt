package extensions

import com.trello.navi2.Event
import com.trello.navi2.component.support.NaviAppCompatActivity
import com.trello.navi2.rx.RxNavi
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.navi.NaviLifecycle
import io.reactivex.Observable

abstract class BaseActivity : NaviAppCompatActivity(), LifecycleProvider<ActivityEvent> {

    @Suppress("LeakingThis")
    private val activityLifecycleProvider = NaviLifecycle.createActivityLifecycleProvider(this)

    override fun <T : Any?> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> = activityLifecycleProvider.bindUntilEvent(event)

    override fun lifecycle(): Observable<ActivityEvent> = activityLifecycleProvider.lifecycle()

    override fun <T : Any?> bindToLifecycle(): LifecycleTransformer<T> = activityLifecycleProvider.bindToLifecycle()

    fun <T> observe(event: Event<T>): Observable<T> = RxNavi.observe(this, event)
}