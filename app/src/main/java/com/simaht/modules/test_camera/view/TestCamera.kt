package com.simaht.modules.test_camera.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dashboard_mh.R
import com.simaht.modules.test_camera.contract.ContractInterfaceTest
import com.simaht.modules.test_camera.presenter.testCameraPresenter
import kotlinx.android.synthetic.main.fragment_camara_asign.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class TestCamera : AppCompatActivity(), ContractInterfaceTest.View {

    private val KEY_DATA: String = "DATA"
    private lateinit var funCamaraPresenter: testCameraPresenter
    private lateinit var container: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_camara_asign)
        funCamaraPresenter = testCameraPresenter(this)
        funCamaraPresenter.escanearCodigo(this)
    }

    override fun mostrarResultado(codigo: String) {
        println("Codigo: ${codigo.replace("&#34;", "\"")}")
        //Toast.makeText(this, codigo, Toast.LENGTH_LONG).show()
    }

    override fun mostrarCamara(scannerView: ZXingScannerView) {
        println("Mostrando camara")
        if((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 101)
        }
        container = findViewById(R.id.fragmentCamaraAsign)
        tvLeyend.visibility = View.VISIBLE
        setContentView(scannerView)
    }

    override fun finalizarEscaneo(codigo:String) {
        val bundle = Bundle()
        bundle.putString(KEY_DATA, codigo.replace("&#34;", "\""))
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        this.finish()
    }
}
