package arch.screen.person_detail

import arch.domain.interactor.GetPersonInteractor
import arch.domain.interactor.GetPersonInteractorImpl
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

fun DetailsActivity.createDiConfig(id: String) = Kodein.Module {

    bind<PersonState>() with singleton {
        PersonState().apply {
            personIdState.accept(id)
        }
    }

    bind<GetPersonInteractor>() with singleton {
        GetPersonInteractorImpl()
    }

    bind<PersonViewImpl>() with singleton {
        PersonViewImpl(
            view = instance(),
            schedulers = instance()
        )
    }

    bind<PersonInputView>() with singleton {
        instance<PersonViewImpl>()
    }

    bind<PersonOutputView>() with singleton {
        instance<PersonViewImpl>()
    }
}