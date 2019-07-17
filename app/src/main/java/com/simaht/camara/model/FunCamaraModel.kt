package com.simaht.camara.model

import android.content.Context
import com.google.zxing.Result
import com.simaht.camara.contract.ContractInterface
import com.simaht.camara.presenter.FunCamaraPresenter
import me.dm7.barcodescanner.zxing.ZXingScannerView

class FunCamaraModel(funCamaraPresenter: FunCamaraPresenter) : ContractInterface.Model, ZXingScannerView.ResultHandler {

    private var presenter: FunCamaraPresenter = funCamaraPresenter
    private var mScannerView: ZXingScannerView? = null

    override fun escanearCodigo(context: Context) {
        mScannerView = ZXingScannerView(context)
        mScannerView!!.setResultHandler(this)
        presenter.mostrarCamara(mScannerView!!)
        mScannerView!!.startCamera()
    }

    override fun handleResult(rawResult: Result) {
        presenter.mostrarResultado(rawResult.text)
        mScannerView!!.stopCamera()
        presenter.finalizarEscaneo()
    }


}