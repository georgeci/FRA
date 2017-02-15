package extensions

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.ReplayRelay

fun <T> publishRelay(): PublishRelay<T> = PublishRelay.create<T>()
fun <T> behaviorRelay(): BehaviorRelay<T> = BehaviorRelay.create<T>()
fun <T> behaviorRelay(default: T): BehaviorRelay<T> = BehaviorRelay.createDefault<T>(default)
fun <T> replayRelay(): ReplayRelay<T> = ReplayRelay.create<T>()