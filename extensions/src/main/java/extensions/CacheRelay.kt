package extensions

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observer
import java.util.concurrent.ConcurrentLinkedQueue

class CacheRelay<T> private constructor() : Relay<T>() {

    companion object {

        fun <T> create(): CacheRelay<T> {
            return CacheRelay()
        }
    }

    private val queue = ConcurrentLinkedQueue<T>()

    private val relay = PublishRelay.create<T>()

    override fun accept(value: T) {
        if (relay.hasObservers()) {
            relay.accept(value)
        } else {
            queue.add(value)
        }
    }

    override fun hasObservers() = relay.hasObservers()

    override fun subscribeActual(observer: Observer<in T>) {
        if (!hasObservers()) {
            var element: T = queue.poll()
            while (element != null) {
                observer.onNext(element)
                element = queue.poll()
            }
        }
        relay.subscribeActual(observer)
    }
}