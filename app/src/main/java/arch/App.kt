package arch

import android.app.Application
import arch.di.createDiConfig
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware

class App : Application(), KodeinAware {
    override val kodein = Kodein.invoke {
        import(createDiConfig())
    }
}