package extensions

import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

infix operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}

fun <T> rx.Observable<T>.rx2(): Observable<T> = RxJavaInterop.toV2Observable(this)