package com.baz.simaht.injection.module;

import java.lang.System;

@kotlin.Suppress(names = {"unused"})
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u00a2\u0006\u0002\b\u0007J\u0015\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0001\u00a2\u0006\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lcom/baz/simaht/injection/module/ContextModule;", "", "()V", "provideApplication", "Landroid/app/Application;", "context", "Landroid/content/Context;", "provideApplication$app_debug", "provideContext", "baseView", "Lcom/baz/simaht/base/BaseView;", "provideContext$app_debug", "app_debug"})
@dagger.Module()
public final class ContextModule {
    public static final com.baz.simaht.injection.module.ContextModule INSTANCE = null;
    
    /**
     * Proporciona el contexto
     * @param baseView la BaseView utilizada para proporcionar el contexto
     * @return Regresa el contexto a ser proporcionado
     */
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final android.content.Context provideContext$app_debug(@org.jetbrains.annotations.NotNull()
    com.baz.simaht.base.BaseView baseView) {
        return null;
    }
    
    /**
     * Proporciona el contexto de la aplicación
     * @param context Contexto en el que se ejecuta la aplicación
     * @return Volver el contexto de aplicación que se proporcionará
     */
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final android.app.Application provideApplication$app_debug(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    private ContextModule() {
        super();
    }
}