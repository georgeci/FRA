package arch.screen.persons

import android.os.Bundle
import arch.R
import arch.domain.interactor.GetPersonsInteractor
import arch.screen.persons.bindings.bindRootUiWithViewModel
import arch.screen.persons.bindings.bindViewModelsWithFeatures
import arch.screen.persons.features.PersonsViewModel
import arch.screen.persons.presentation.android.LifecycleStreams
import arch.screen.persons.presentation.common.PersonsCommonView
import arch.screen.persons.presentation.create.PersonsCreateViewHolder
import arch.screen.persons.presentation.create.PersonsCreateViewModel
import arch.screen.persons.presentation.list.PersonsListView
import arch.screen.persons.presentation.list.PersonsListViewModel
import arch.screen.persons.presentation.router.PersonsRouter
import arch.screen.persons.screen_state.PersonsStateMachine
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import extensions.BaseActivity
import extensions.SchedulersFactory
import extensions.plusAssign
import io.reactivex.disposables.CompositeDisposable

class PersonsActivity : BaseActivity() {
    private val injector = KodeinInjector()

    private val commonViewProvider by injector.provider<PersonsCommonView>()
    private val listViewProvider by injector.provider<PersonsListView>()
    private val createViewHolderProvider by injector.provider<PersonsCreateViewHolder>()
    private val routerProvider by injector.provider<PersonsRouter>()
    private val lifecycleStreams: LifecycleStreams by injector.instance()

    private val viewModel: PersonsViewModel by injector.instance()
    private val createViewModel: PersonsCreateViewModel by injector.instance()
    private val personsListViewModel: PersonsListViewModel by injector.instance()

    private val interactor: GetPersonsInteractor by injector.instance()
    private val stateMachine: PersonsStateMachine by injector.instance()
    private val schedulers: SchedulersFactory by injector.instance()

    private val screenDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(Kodein {
            extend((application as KodeinAware).kodein)
            import(createDi())
        })
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)
        if (savedInstanceState == null) {
            lifecycleStreams.firstLaunchStream.accept(Any())
        }
    }

    override fun onResume() {
        super.onResume()
        screenDisposable += bindViewModelsWithFeatures(
            viewModel,
            personsListViewModel,
            interactor,
            stateMachine,
            schedulers
        )
        screenDisposable += bindRootUiWithViewModel(
            commonViewProvider(),
            listViewProvider(),
            createViewHolderProvider(),
            routerProvider(),
            lifecycleStreams,
            viewModel,
            createViewModel,
            personsListViewModel,
            schedulers
        )
    }

    override fun onPause() {
        String
        super.onPause()
        screenDisposable.clear()
    }
}