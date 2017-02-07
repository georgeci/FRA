package arch.domain.interactor

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

val createPersonInteractor: CreatePersonsInteractor =
    { name, schedulers ->
        Observable.just(
            CreatePersonsResolution.Success as CreatePersonsResolution
        ).delay(5, TimeUnit.SECONDS, schedulers.io())
    }

sealed class CreatePersonsResolution {
    data class Error(val message: String) : CreatePersonsResolution()
    object Success : CreatePersonsResolution()
}