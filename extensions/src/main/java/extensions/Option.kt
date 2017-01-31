package extensions

/*
 * Copyright 2013 Mario Arias
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.NoSuchElementException

/**
 * Created by IntelliJ IDEA.
 * @author Mario Arias
 * Date: 17/05/13
 * Time: 12:53
 */
@Suppress("BASE_WITH_NULLABLE_UPPER_BOUND")
sealed class Option<out T> {
    abstract fun isEmpty(): Boolean

    fun nonEmpty(): Boolean = isDefined()

    fun isDefined(): Boolean = !isEmpty()

    abstract fun get(): T

    fun orNull(): T? = if (isEmpty()) {
        null
    } else {
        get()
    }

    inline fun <R> map(f: (T) -> R): Option<R> = if (isEmpty()) {
        None
    } else {
        Some(f(get()))
    }

    inline fun <P1, R> map(p1: Option<P1>, f: (T, P1) -> R): Option<R> = if (isEmpty()) {
        None
    } else {
        p1.map { pp1 -> f(get(), pp1) }
    }

    inline fun <R> fold(none: () -> R, some: (T) -> R): R = if (isEmpty()) {
        none()
    } else {
        some(get())
    }

    inline fun <R> flatMap(f: (T) -> Option<R>): Option<R> = if (isEmpty()) {
        None
    } else {
        f(get())
    }

    inline fun filter(predicate: (T) -> Boolean): Option<T> = if (nonEmpty() && predicate(get())) {
        this
    } else {
        None
    }

    inline fun filterNot(predicate: (T) -> Boolean): Option<T> = if (nonEmpty() && !predicate(get())) {
        this
    } else {
        None
    }

    inline fun exists(predicate: (T) -> Boolean): Boolean = nonEmpty() && predicate(get())

    inline fun forEach(f: (T) -> Unit) {
        if (nonEmpty()) f(get())
    }

    object None : Option<Nothing>() {
        override fun get() = throw NoSuchElementException("None.get")

        override fun isEmpty() = true

        override fun equals(other: Any?): Boolean = when (other) {
            is None -> true
            else -> false
        }

        override fun hashCode(): Int = Integer.MAX_VALUE
    }

    class Some<out T>(val t: T) : Option<T>() {
        override fun get() = t

        override fun isEmpty() = false

        override fun equals(other: Any?): Boolean = when (other) {
            is Some<*> -> t!! == other.get()
            is None -> false
            else -> false
        }

        override fun hashCode(): Int = t!!.hashCode() + 17

        override fun toString(): String = "Some<$t>"
    }
}

fun <T> Option<T>.getOrElse(default: () -> T): T = if (isEmpty()) {
    default()
} else {
    get()
}

fun <T> Option<T>.orElse(alternative: () -> Option<T>): Option<T> = if (isEmpty()) {
    alternative()
} else {
    this
}

@Suppress("BASE_WITH_NULLABLE_UPPER_BOUND") fun <T> T?.toOption(): Option<T> = if (this != null) {
    Option.Some(this)
} else {
    Option.None
}

fun <P1, R> Function1<P1, R>.optionLift(): (Option<P1>) -> Option<R> {
    return { it.map(this) }
}