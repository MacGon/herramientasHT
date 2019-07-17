package com.baz.simaht.injection.module

import android.app.Application
import android.content.Context
import com.baz.simaht.base.BaseView
import dagger.Module
import dagger.Provides

/**
 *Módulo que proporciona todas las dependencias requeridas sobre el contexto.
 */

@Module
//Aquí ya que estamos tratando con un módulo Dagger 2
@Suppress("unused")
object NetworkModule {
    /**
     * Proporciona el contexto
     * @param baseView la BaseView utilizada para proporcionar el contexto
     * @return el contexto a ser proporcionado
     */
    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context {
        return baseView.getContext()
    }

    /**
     * Proporciona el contexto de la aplicación
     * @param context Contexto en el que se ejecuta la aplicación
     * @volver el contexto de la aplicación a proporcionar
     */
    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application {
        return context.applicationContext as Application
    }

}