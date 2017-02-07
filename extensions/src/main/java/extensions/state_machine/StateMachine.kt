package extensions.state_machine

import extensions.behaviorRelay
import extensions.filterDefined
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.funktionale.option.Option
import org.funktionale.option.toOption

abstract class StateMachine<T, R>(initial: T? = null) {

    fun getCurrentValue(): T? = stateRelay.value.orNull()

    fun setCurrentValue(value: T) {
        stateRelay.accept(value.toOption())
    }

    private val stateRelay = behaviorRelay<Option<T>>()

    val trigger = Consumer<R> {
        val current = getCurrentValue()
        setCurrentValue(change(current, it))
    }

    init {
        stateRelay.accept(initial.toOption())
    }

    fun observe(): Observable<T> = stateRelay.filterDefined()

    abstract fun change(old: T?, trigger: R): T
}