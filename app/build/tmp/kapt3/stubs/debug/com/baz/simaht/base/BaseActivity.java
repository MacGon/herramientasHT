package com.baz.simaht.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010\n\u001a\u00020\u0004H&J\u0006\u0010\u000b\u001a\u00020\u0004J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\r\u001a\u00020\u000eH\'\u00a8\u0006\u000f"}, d2 = {"Lcom/baz/simaht/base/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "hideKeyboard", "", "editText", "Landroid/widget/EditText;", "init", "savedInstanceState", "Landroid/os/Bundle;", "keyboardType", "navNotiltle", "onCreate", "setLayout", "", "app_debug"})
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity {
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @androidx.annotation.LayoutRes()
    public abstract int setLayout();
    
    public abstract void init(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState);
    
    public abstract void keyboardType();
    
    public abstract void hideKeyboard(@org.jetbrains.annotations.NotNull()
    android.widget.EditText editText);
    
    public final void navNotiltle() {
    }
    
    public BaseActivity() {
        super();
    }
}