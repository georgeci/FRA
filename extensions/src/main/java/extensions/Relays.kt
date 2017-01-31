package extensions

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.ReplayRelay

//fun <T> cacheRelay(): CacheRelay<T> = CacheRelay.create<T>()

fun <T> publishRelay(): PublishRelay<T> = PublishRelay.create<T>()
fun <T> behaviorRelay(): BehaviorRelay<T> = BehaviorRelay.create<T>()
fun <T> replayRelay(): ReplayRelay<T> = ReplayRelay.create<T>()