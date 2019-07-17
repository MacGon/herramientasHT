package com.baz.simaht.login.presenter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lcom/baz/simaht/login/presenter/LoginPresenterImpl;", "Lcom/baz/simaht/model/LogInInteractor$OnLoginFinishedListener;", "loginView", "Lcom/simaht/login/presenter/LoginView;", "logInInteractor", "Lcom/baz/simaht/model/LogInInteractor;", "(Lcom/simaht/login/presenter/LoginView;Lcom/baz/simaht/model/LogInInteractor;)V", "getLogInInteractor", "()Lcom/baz/simaht/model/LogInInteractor;", "getLoginView", "()Lcom/simaht/login/presenter/LoginView;", "setLoginView", "(Lcom/simaht/login/presenter/LoginView;)V", "onDestroy", "", "onPasswordError", "onSuccess", "onUsernameError", "validateCredentials", "username", "", "password", "app_debug"})
public final class LoginPresenterImpl implements com.baz.simaht.model.LogInInteractor.OnLoginFinishedListener {
    @org.jetbrains.annotations.Nullable()
    private com.simaht.login.presenter.LoginView loginView;
    @org.jetbrains.annotations.NotNull()
    private final com.baz.simaht.model.LogInInteractor logInInteractor = null;
    
    public final void validateCredentials(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void onDestroy() {
    }
    
    @java.lang.Override()
    public void onUsernameError() {
    }
    
    @java.lang.Override()
    public void onPasswordError() {
    }
    
    @java.lang.Override()
    public void onSuccess() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.simaht.login.presenter.LoginView getLoginView() {
        return null;
    }
    
    public final void setLoginView(@org.jetbrains.annotations.Nullable()
    com.simaht.login.presenter.LoginView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.baz.simaht.model.LogInInteractor getLogInInteractor() {
        return null;
    }
    
    public LoginPresenterImpl(@org.jetbrains.annotations.Nullable()
    com.simaht.login.presenter.LoginView loginView, @org.jetbrains.annotations.NotNull()
    com.baz.simaht.model.LogInInteractor logInInteractor) {
        super();
    }
}