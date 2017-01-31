package arch.screen.persons

import android.os.Bundle
import arch.R
import arch.screen.PersonsConnector
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import extensions.BaseActivity

class PersonsActivity : BaseActivity() {
    val injector = KodeinInjector()

    val view: PersonsView by injector.instance()
    val presenter: PersonsPresenter by injector.instance()
    val connector: PersonsConnector by injector.instance()
    val router: PersonsRouter by injector.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)
        injector.inject(Kodein{
            extend((application as KodeinAware).kodein)
            import(createDi())
        })
        presenter//init
    }

    override fun onResume() {
        super.onResume()
        connector.attachView(view)
        connector.attachRouter(router)
    }

    override fun onPause() {
        super.onPause()
        connector.dettachView()
        connector.dettachRouter()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}