package com.simaht.modules.test_camera.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dashboard_mh.R
import com.simaht.modules.test_camera.contract.ContractInterfaceTest
import com.simaht.modules.test_camera.presenter.testCameraPresenter
import me.dm7.barcodescanner.zxing.ZXingScannerView

class TestCamera : AppCompatActivity(), ContractInterfaceTest.View {

    private val KEY_DATA: String = "DATA"
    private lateinit var funCamaraPresenter: testCameraPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_camara)
        funCamaraPresenter = testCameraPresenter(this)
        funCamaraPresenter.escanearCodigo(this)
    }

    override fun mostrarResultado(codigo: String) {
        println("Codigo: $codigo")
        Toast.makeText(this, codigo, Toast.LENGTH_LONG).show()
    }

    override fun mostrarCamara(scannerView: ZXingScannerView) {
        println("Mostrando camara")
        if((ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 101)
        }
        setContentView(scannerView)
    }

    override fun finalizarEscaneo(codigo:String) {
        val bundle = Bundle()
        bundle.putString(KEY_DATA, codigo)
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        this.finish()
    }
}
