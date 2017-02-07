package arch.screen.persons.presentation.android

import com.jakewharton.rxrelay2.Relay
import extensions.CacheRelay


interface LifecycleStreams {
    val firstLaunchStream: Relay<Any>
}

class LifecycleStreamsImpl : LifecycleStreams {
    override val firstLaunchStream = CacheRelay.create<Any>()

}