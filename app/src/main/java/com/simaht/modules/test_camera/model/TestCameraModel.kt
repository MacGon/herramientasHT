package com.simaht.modules.test_camera.model

import android.content.Context
import com.google.zxing.Result
import com.simaht.modules.test_camera.contract.ContractInterfaceTest
import com.simaht.modules.test_camera.presenter.testCameraPresenter
import me.dm7.barcodescanner.zxing.ZXingScannerView

class TestCameraModel(funCamaraPresenter: testCameraPresenter) : ContractInterfaceTest.Model, ZXingScannerView.ResultHandler {

    private var presenter: testCameraPresenter = funCamaraPresenter
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
        presenter.finalizarEscaneo(rawResult.text)
    }
}