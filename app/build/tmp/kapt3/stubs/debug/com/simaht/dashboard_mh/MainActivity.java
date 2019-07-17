package com.simaht.dashboard_mh;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\bB\u0005\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010$\u001a\u00020\u0018H\u0016J\b\u0010%\u001a\u00020\u0018H\u0016J\b\u0010&\u001a\u00020\u0018H\u0016J\b\u0010\'\u001a\u00020\u0018H\u0016J\b\u0010(\u001a\u00020\u0018H\u0016J\b\u0010)\u001a\u00020\u0018H\u0016R\u001a\u0010\n\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/simaht/dashboard_mh/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/material/navigation/NavigationView$OnNavigationItemSelectedListener;", "Lcom/simaht/dashboard_mh/DashBoardFragment$resultInterface;", "Lcom/simaht/dashboard_mh/DashBoardFragment$chargeToolInterface;", "Lcom/simaht/dashboard_mh/DashBoardFragment$assignToolInterface;", "Lcom/simaht/dashboard_mh/DashBoardFragment$liftInventoryInterface;", "Lcom/simaht/dashboard_mh/DashBoardFragment$unsubscribeTool;", "Lcom/simaht/dashboard_mh/DashBoardFragment$integrateFile;", "()V", "container", "Landroid/widget/FrameLayout;", "getContainer", "()Landroid/widget/FrameLayout;", "setContainer", "(Landroid/widget/FrameLayout;)V", "fm", "Landroidx/fragment/app/FragmentManager;", "kotlin.jvm.PlatformType", "getFm", "()Landroidx/fragment/app/FragmentManager;", "onNavigationItemSelectedListener", "Lcom/google/android/material/bottomnavigation/BottomNavigationView$OnNavigationItemSelectedListener;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onNavigationItemSelected", "item", "Landroid/view/MenuItem;", "onOptionsItemSelected", "showAssignToolFragment", "showChargeToolFragment", "showIntegrateFileFragment", "showLiftInventoryFragment", "showResultsFragment", "showUnsubscribeToolFragment", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity implements com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener, com.simaht.dashboard_mh.DashBoardFragment.resultInterface, com.simaht.dashboard_mh.DashBoardFragment.chargeToolInterface, com.simaht.dashboard_mh.DashBoardFragment.assignToolInterface, com.simaht.dashboard_mh.DashBoardFragment.liftInventoryInterface, com.simaht.dashboard_mh.DashBoardFragment.unsubscribeTool, com.simaht.dashboard_mh.DashBoardFragment.integrateFile {
    @org.jetbrains.annotations.NotNull()
    public android.widget.FrameLayout container;
    private final androidx.fragment.app.FragmentManager fm = null;
    private final com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.FrameLayout getContainer() {
        return null;
    }
    
    public final void setContainer(@org.jetbrains.annotations.NotNull()
    android.widget.FrameLayout p0) {
    }
    
    public final androidx.fragment.app.FragmentManager getFm() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onNavigationItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void showResultsFragment() {
    }
    
    @java.lang.Override()
    public void showChargeToolFragment() {
    }
    
    @java.lang.Override()
    public void showAssignToolFragment() {
    }
    
    @java.lang.Override()
    public void showLiftInventoryFragment() {
    }
    
    @java.lang.Override()
    public void showUnsubscribeToolFragment() {
    }
    
    @java.lang.Override()
    public void showIntegrateFileFragment() {
    }
    
    public MainActivity() {
        super();
    }
}