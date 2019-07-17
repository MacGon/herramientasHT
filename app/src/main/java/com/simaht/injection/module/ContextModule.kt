package com.baz.simaht.injection.module

import android.app.Application
import android.content.Context
import com.baz.simaht.base.BaseView
import dagger.Module
import dagger.Provides

@Module
//Aquí ya que estamos tratando con un módulo Dagger 2
@Suppress("unused")
object ContextModule {

    /**
    * Proporciona el contexto
    * @param baseView la BaseView utilizada para proporcionar el contexto
    * @return Regresa el contexto a ser proporcionado
    */
    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context {
        return baseView.getContext()
    }

    /**
    * Proporciona el contexto de la aplicación
    * @param context Contexto en el que se ejecuta la aplicación
    * @return Volver el contexto de aplicación que se proporcionará
    */
    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application {
        return context.applicationContext as Application
    }

}