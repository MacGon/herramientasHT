package com.simaht.modules.test_camera.contract

import android.content.Context
import me.dm7.barcodescanner.zxing.ZXingScannerView

interface ContractInterfaceTest {

    interface View{
        fun mostrarCamara(scannerView: ZXingScannerView)
        fun mostrarResultado(codigo: String)
        fun finalizarEscaneo(codigo: String)
    }

    interface Presenter{
        fun mostrarCamara(scannerView: ZXingScannerView)
        fun mostrarResultado(codigo: String)
        fun escanearCodigo(context: Context)
        fun finalizarEscaneo(codigo: String)
    }

    interface Model{
        fun escanearCodigo(context: Context)
    }
}