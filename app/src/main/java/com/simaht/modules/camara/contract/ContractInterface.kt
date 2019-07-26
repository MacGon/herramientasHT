package com.simaht.modules.camara.contract

import android.content.Context
import me.dm7.barcodescanner.zxing.ZXingScannerView

interface ContractInterface {

    interface View{
        fun mostrarCamara(scannerView: ZXingScannerView)
        fun mostrarResultado(codigo: String)
        fun finalizarEscaneo()
    }

    interface Presenter{
        fun mostrarCamara(scannerView: ZXingScannerView)
        fun mostrarResultado(codigo: String)
        fun escanearCodigo(context: Context)
        fun finalizarEscaneo()
    }

    interface Model{
        fun escanearCodigo(context: Context)
    }

}