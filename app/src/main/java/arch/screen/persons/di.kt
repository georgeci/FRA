package arch.screen.persons

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import arch.domain.interactor.personInteractor
import arch.screen.PersonsConnector
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

fun PersonsActivity.createDi() = Kodein.Module {
    bind<PersonAdapter>() with singleton {
        PersonAdapter()
    }

    bind<RecyclerView.LayoutManager>() with singleton {
        LinearLayoutManager(this@createDi)
    }

    bind<PersonsView>() with singleton {
        PersonsViewImpl(
            view = this@createDi.window.decorView,
            adapter = instance(),
            layoutManager = instance()
        )
    }

    bind<PersonsRouter>() with singleton {
        PersonsRouterImpl(
            activity = this@createDi
        )
    }

    bind<PersonsConnector>() with singleton {
        PersonsConnector()
    }

    bind<PersonsPresenter>() with singleton {
        PersonsPresenter(
            connector = instance(),
            interactor = personInteractor
        )
    }
}