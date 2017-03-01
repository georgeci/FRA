package arch.screen.person_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import arch.R
import arch.domain.model.Person
import arch.screen.persons.presentation.android.LifecycleStreams
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import io.reactivex.disposables.CompositeDisposable

class DetailsActivity : AppCompatActivity() {

    val injector = KodeinInjector()

    val state: PersonState by injector.instance()

    val inputView: PersonInputView by injector.instance()
    val outputView: PersonOutputView by injector.instance()
    val lifecycleStreams: LifecycleStreams by injector.instance()

    val screenDisposable = CompositeDisposable()

    companion object {
        fun intent(context: Context, item: Person) = Intent(context, DetailsActivity::class.java)
            .putExtra("PERSON", item.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        val personId = intent.getStringExtra("PERSON")

        injector.inject(Kodein {
            extend((application as KodeinAware).kodein)
            import(createDiConfig(personId))
        })

        if (savedInstanceState == null) {
            lifecycleStreams.firstLaunchStream.accept(Any())
        }
    }

    override fun onResume() {

        super.onResume()
    }

    override fun onPause() {
        screenDisposable.clear()
        super.onPause()
    }
}
