package extensions

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.funktionale.option.Option

fun <T> Observable<Option<T>>.filterDefined(): Observable<T> = filter { it.isDefined() }.map { it.get() }
fun <T> Single<Option<T>>.filterDefined(): Maybe<T> = filter { it.isDefined() }.map { it.get() }
fun <T> Maybe<Option<T>>.filterDefined(): Maybe<T> = filter { it.isDefined() }.map { it.get() }

fun <T> Observable<Option<T>>.filterNone(): Observable<Option.None> = filter { it.isDefined().not() }.map { Option.None }