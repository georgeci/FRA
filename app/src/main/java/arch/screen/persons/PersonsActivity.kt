package arch.screen.persons

import android.os.Bundle
import arch.R
import arch.domain.interactor.GetPersonsInteractor
import arch.screen.persons.bindings.bindScreenWithFeatures
import arch.screen.persons.bindings.bindUiWithStates
import arch.screen.persons.presentation.android.LifecycleStreams
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

    private val uiProvider by injector.provider<PersonsUiContainer>()
    private val states by injector.instance<PersonsStateContainer>()

    private val lifecycleStreams: LifecycleStreams by injector.instance()

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
        screenDisposable += bindScreenWithFeatures(
            ui = uiProvider(),
            states = states,
            schedulers = schedulers,
            stateMachine = stateMachine,
            interactor = interactor,
            lifecycleStreams = lifecycleStreams
        )
        screenDisposable += bindUiWithStates(
            ui = uiProvider(),
            states = states,
            schedulers = schedulers
        )
    }

    override fun onPause() {
        String
        super.onPause()
        screenDisposable.clear()
    }
}