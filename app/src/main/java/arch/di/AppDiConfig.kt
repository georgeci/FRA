package arch.di

import arch.App
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.singleton
import extensions.SchedulersFactory
import extensions.SchedulersFactoryImpl

fun App.createDiConfig() = Kodein.Module {
    bind<SchedulersFactory>() with singleton { SchedulersFactoryImpl() }
}