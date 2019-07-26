package com.baz.simaht.injection.component

import com.baz.simaht.base.BaseView
import com.baz.simaht.injection.module.ContextModule
import com.baz.simaht.injection.module.NetworkModule
import com.simaht.modules.login.presenter.LoginPresenterImpl
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Componente que proporciona inject() metodos para  presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {

    /**
     * Inyecta las dependencias requeridas en el PresenterImpl especificado.
     * @param PresenterImpl en el que inyecta las dependencias
     */
    fun inject(mLoginPresenterImpl: LoginPresenterImpl)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }

}