package com.simaht.camara.view;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\u0012\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/simaht/camara/view/FunCamaraView;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/simaht/camara/contract/ContractInterface$View;", "()V", "funCamaraPresenter", "Lcom/simaht/camara/presenter/FunCamaraPresenter;", "tabLayoutCamara", "Lcom/google/android/material/tabs/TabLayout;", "viewPagerCamara", "Landroidx/viewpager/widget/ViewPager;", "finalizarEscaneo", "", "mostrarCamara", "scannerView", "Lme/dm7/barcodescanner/zxing/ZXingScannerView;", "mostrarResultado", "codigo", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class FunCamaraView extends androidx.appcompat.app.AppCompatActivity implements com.simaht.camara.contract.ContractInterface.View {
    private com.simaht.camara.presenter.FunCamaraPresenter funCamaraPresenter;
    private com.google.android.material.tabs.TabLayout tabLayoutCamara;
    private androidx.viewpager.widget.ViewPager viewPagerCamara;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void mostrarResultado(@org.jetbrains.annotations.NotNull()
    java.lang.String codigo) {
    }
    
    @java.lang.Override()
    public void mostrarCamara(@org.jetbrains.annotations.NotNull()
    me.dm7.barcodescanner.zxing.ZXingScannerView scannerView) {
    }
    
    @java.lang.Override()
    public void finalizarEscaneo() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    public FunCamaraView() {
        super();
    }
}