package arch.screen.person_detail

import arch.domain.interactor.GetPersonInteractor
import arch.domain.interactor.getPersonInteractor
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.github.salomonbrys.kodein.singleton

fun DetailsActivity.createDiConfig() = Kodein.Module {
    bind<GetPersonInteractor>() with singleton {
        getPersonInteractor
    }

    bind<PersonViewImpl>() with provider {
        PersonViewImpl(
            view = instance(),
            schedulers = instance()
        )
    }

    bind<PersonInputView>() with provider {
        provider<PersonViewImpl>().invoke()
    }

    bind<PersonOutputView>() with provider {
        provider<PersonViewImpl>().invoke()
    }
}