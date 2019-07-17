package com.simaht.camara.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/simaht/camara/model/FunCamaraModel;", "Lcom/simaht/camara/contract/ContractInterface$Model;", "Lme/dm7/barcodescanner/zxing/ZXingScannerView$ResultHandler;", "funCamaraPresenter", "Lcom/simaht/camara/presenter/FunCamaraPresenter;", "(Lcom/simaht/camara/presenter/FunCamaraPresenter;)V", "mScannerView", "Lme/dm7/barcodescanner/zxing/ZXingScannerView;", "presenter", "escanearCodigo", "", "context", "Landroid/content/Context;", "handleResult", "rawResult", "Lcom/google/zxing/Result;", "app_debug"})
public final class FunCamaraModel implements com.simaht.camara.contract.ContractInterface.Model, me.dm7.barcodescanner.zxing.ZXingScannerView.ResultHandler {
    private com.simaht.camara.presenter.FunCamaraPresenter presenter;
    private me.dm7.barcodescanner.zxing.ZXingScannerView mScannerView;
    
    @java.lang.Override()
    public void escanearCodigo(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void handleResult(@org.jetbrains.annotations.NotNull()
    com.google.zxing.Result rawResult) {
    }
    
    public FunCamaraModel(@org.jetbrains.annotations.NotNull()
    com.simaht.camara.presenter.FunCamaraPresenter funCamaraPresenter) {
        super();
    }
}