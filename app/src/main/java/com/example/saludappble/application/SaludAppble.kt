package com.example.saludappble.application

import androidx.multidex.MultiDexApplication
import com.example.saludappble.application.modules.databaseModule
import com.example.saludappble.application.modules.entities.comidaModule
import com.example.saludappble.application.modules.entities.usuarioModule
import com.example.saludappble.application.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SaludAppble: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        //Inicio el inyector de dependencias
        initiateKoin()

        //Activo Timber
        Timber.plant(Timber.DebugTree())

    }

    private fun initiateKoin(){

        startKoin {

            androidContext(this@SaludAppble)

            //Cargo los modulos
            modules(listOf(
                databaseModule,
                viewModelModule,
                usuarioModule, comidaModule
            ))
        }
    }
}