package com.simaht.camara.contract;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/simaht/camara/contract/ContractInterface;", "", "Model", "Presenter", "View", "app_debug"})
public abstract interface ContractInterface {
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lcom/simaht/camara/contract/ContractInterface$View;", "", "finalizarEscaneo", "", "mostrarCamara", "scannerView", "Lme/dm7/barcodescanner/zxing/ZXingScannerView;", "mostrarResultado", "codigo", "", "app_debug"})
    public static abstract interface View {
        
        public abstract void mostrarCamara(@org.jetbrains.annotations.NotNull()
        me.dm7.barcodescanner.zxing.ZXingScannerView scannerView);
        
        public abstract void mostrarResultado(@org.jetbrains.annotations.NotNull()
        java.lang.String codigo);
        
        public abstract void finalizarEscaneo();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\r"}, d2 = {"Lcom/simaht/camara/contract/ContractInterface$Presenter;", "", "escanearCodigo", "", "context", "Landroid/content/Context;", "finalizarEscaneo", "mostrarCamara", "scannerView", "Lme/dm7/barcodescanner/zxing/ZXingScannerView;", "mostrarResultado", "codigo", "", "app_debug"})
    public static abstract interface Presenter {
        
        public abstract void mostrarCamara(@org.jetbrains.annotations.NotNull()
        me.dm7.barcodescanner.zxing.ZXingScannerView scannerView);
        
        public abstract void mostrarResultado(@org.jetbrains.annotations.NotNull()
        java.lang.String codigo);
        
        public abstract void escanearCodigo(@org.jetbrains.annotations.NotNull()
        android.content.Context context);
        
        public abstract void finalizarEscaneo();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/simaht/camara/contract/ContractInterface$Model;", "", "escanearCodigo", "", "context", "Landroid/content/Context;", "app_debug"})
    public static abstract interface Model {
        
        public abstract void escanearCodigo(@org.jetbrains.annotations.NotNull()
        android.content.Context context);
    }
}