package com.simaht.camara.presenter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/simaht/camara/presenter/FunCamaraPresenter;", "Lcom/simaht/camara/contract/ContractInterface$Presenter;", "_view", "Lcom/simaht/camara/contract/ContractInterface$View;", "(Lcom/simaht/camara/contract/ContractInterface$View;)V", "model", "Lcom/simaht/camara/contract/ContractInterface$Model;", "view", "escanearCodigo", "", "context", "Landroid/content/Context;", "finalizarEscaneo", "mostrarCamara", "scannerView", "Lme/dm7/barcodescanner/zxing/ZXingScannerView;", "mostrarResultado", "codigo", "", "app_debug"})
public final class FunCamaraPresenter implements com.simaht.camara.contract.ContractInterface.Presenter {
    private com.simaht.camara.contract.ContractInterface.View view;
    private com.simaht.camara.contract.ContractInterface.Model model;
    
    @java.lang.Override()
    public void mostrarResultado(@org.jetbrains.annotations.NotNull()
    java.lang.String codigo) {
    }
    
    @java.lang.Override()
    public void escanearCodigo(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void mostrarCamara(@org.jetbrains.annotations.NotNull()
    me.dm7.barcodescanner.zxing.ZXingScannerView scannerView) {
    }
    
    @java.lang.Override()
    public void finalizarEscaneo() {
    }
    
    public FunCamaraPresenter(@org.jetbrains.annotations.NotNull()
    com.simaht.camara.contract.ContractInterface.View _view) {
        super();
    }
}