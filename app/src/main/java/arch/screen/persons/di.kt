package arch.screen.persons

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import arch.domain.interactor.GetPersonsInteractor
import arch.domain.interactor.personInteractor
import arch.screen.persons.presentation.android.LifecycleStreams
import arch.screen.persons.presentation.android.LifecycleStreamsImpl
import arch.screen.persons.presentation.common.PersonsCommonState
import arch.screen.persons.presentation.common.PersonsCommonView
import arch.screen.persons.presentation.common.PersonsCommonViewImpl
import arch.screen.persons.presentation.create.PersonsCreateState
import arch.screen.persons.presentation.create.PersonsCreateView
import arch.screen.persons.presentation.create.PersonsCreateViewImpl
import arch.screen.persons.presentation.filter.PersonsFilterState
import arch.screen.persons.presentation.filter.PersonsFilterView
import arch.screen.persons.presentation.filter.PersonsFilterViewImpl
import arch.screen.persons.presentation.list.PersonAdapter
import arch.screen.persons.presentation.list.PersonsListState
import arch.screen.persons.presentation.list.PersonsListView
import arch.screen.persons.presentation.list.PersonsListViewImpl
import arch.screen.persons.presentation.router.PersonsRouter
import arch.screen.persons.presentation.router.PersonsRouterImpl
import arch.screen.persons.screen_state.PersonsStateMachine
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

fun PersonsActivity.createDi() = Kodein.Module {

    bind<LifecycleStreams>() with singleton {
        LifecycleStreamsImpl()
    }

    bind<View>() with singleton {
        this@createDi.window.decorView
    }

    bind<PersonsCommonView>() with singleton {
        PersonsCommonViewImpl(view = instance())
    }

    bind<PersonsCreateView>() with singleton {
        PersonsCreateViewImpl(view = instance())
    }

    bind<PersonsFilterView>() with singleton {
        PersonsFilterViewImpl(view = instance())
    }

    bind<PersonAdapter>() with singleton {
        PersonAdapter()
    }

    bind<RecyclerView.LayoutManager>() with singleton {
        LinearLayoutManager(this@createDi)
    }

    bind<PersonsListView>() with singleton {
        PersonsListViewImpl(
            view = this@createDi.window.decorView,
            adapter = instance(),
            layoutManager = instance()
        )
    }

    bind<PersonsRouter>() with singleton {
        PersonsRouterImpl(
            context = this@createDi,
            navigator = { this@createDi.startActivity(it) },
            view = instance()
        )
    }

    bind<GetPersonsInteractor>() with singleton {
        personInteractor
    }

    bind<PersonsStateMachine>() with singleton {
        PersonsStateMachine()
    }

    bind<PersonsListState>() with singleton {
        PersonsListState()
    }

    bind<PersonsCreateState>() with singleton {
        PersonsCreateState()
    }

    bind<PersonsFilterState>() with singleton {
        PersonsFilterState()
    }

    bind<PersonsCommonState>() with singleton {
        PersonsCommonState()
    }

    bind<PersonsScreen>() with singleton {
        PersonsScreen(
            commonView = instance(),
            createView = instance(),
            filterView = instance(),
            listView = instance(),
            router = instance()
        )
    }

    bind<PersonsStateContainer>() with singleton {
        PersonsStateContainer(
            commonState = instance(),
            createState = instance(),
            filterState = instance(),
            listState = instance()
        )
    }
}