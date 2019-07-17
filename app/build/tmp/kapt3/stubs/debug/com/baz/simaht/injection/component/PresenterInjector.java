package com.baz.simaht.injection.component;

import java.lang.System;

/**
 * Componente que proporciona inject() metodos para  presenters.
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lcom/baz/simaht/injection/component/PresenterInjector;", "", "inject", "", "mLoginPresenterImpl", "Lcom/baz/simaht/login/presenter/LoginPresenterImpl;", "Builder", "app_debug"})
@dagger.Component(modules = {com.baz.simaht.injection.module.ContextModule.class, com.baz.simaht.injection.module.NetworkModule.class})
@javax.inject.Singleton()
public abstract interface PresenterInjector {
    
    /**
     * Inyecta las dependencias requeridas en el PresenterImpl especificado.
     * @param PresenterImpl en el que inyecta las dependencias
     */
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.baz.simaht.login.presenter.LoginPresenterImpl mLoginPresenterImpl);
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H\'J\b\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lcom/baz/simaht/injection/component/PresenterInjector$Builder;", "", "baseView", "Lcom/baz/simaht/base/BaseView;", "build", "Lcom/baz/simaht/injection/component/PresenterInjector;", "contextModule", "Lcom/baz/simaht/injection/module/ContextModule;", "networkModule", "Lcom/baz/simaht/injection/module/NetworkModule;", "app_debug"})
    @dagger.Component.Builder()
    public static abstract interface Builder {
        
        @org.jetbrains.annotations.NotNull()
        public abstract com.baz.simaht.injection.component.PresenterInjector build();
        
        @org.jetbrains.annotations.NotNull()
        public abstract com.baz.simaht.injection.component.PresenterInjector.Builder networkModule(@org.jetbrains.annotations.NotNull()
        com.baz.simaht.injection.module.NetworkModule networkModule);
        
        @org.jetbrains.annotations.NotNull()
        public abstract com.baz.simaht.injection.component.PresenterInjector.Builder contextModule(@org.jetbrains.annotations.NotNull()
        com.baz.simaht.injection.module.ContextModule contextModule);
        
        @org.jetbrains.annotations.NotNull()
        @dagger.BindsInstance()
        public abstract com.baz.simaht.injection.component.PresenterInjector.Builder baseView(@org.jetbrains.annotations.NotNull()
        com.baz.simaht.base.BaseView baseView);
    }
}