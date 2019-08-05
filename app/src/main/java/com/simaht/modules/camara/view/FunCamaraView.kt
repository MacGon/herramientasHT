package com.simaht.modules.camara.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.baz.simaht.login.extensions.postDelayed
import com.example.dashboard_mh.R
import com.google.android.material.tabs.TabLayout
import com.simaht.modules.camara.contract.ContractInterface
import com.simaht.modules.camara.presenter.FunCamaraPresenter
import com.simaht.modules.dashboard_mh.MainActivity
import me.dm7.barcodescanner.zxing.ZXingScannerView

class FunCamaraView: AppCompatActivity(), ContractInterface.View {

    private lateinit var funCamaraPresenter: FunCamaraPresenter

    //private lateinit var contenedorCamara: LinearLayout

    private var tabLayoutCamara: TabLayout? = null

    private var viewPagerCamara: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_camara_2)
        //contenedorCamara = findViewById(R.layout.layout_camera)
        //tabLayoutCamara = findViewById(R.id.tabLayoutCamara)
        //viewPagerCamara = findViewById(R.id.pagerCamara)
        funCamaraPresenter = FunCamaraPresenter(this)
        funCamaraPresenter.escanearCodigo(this)
        /*tabLayoutCamara!!.addTab(tabLayoutCamara!!.newTab().setText("Código de Barras"))
        tabLayoutCamara!!.addTab(tabLayoutCamara!!.newTab().setText("Código QR"))
        tabLayoutCamara!!.addTab(tabLayoutCamara!!.newTab().setText("Documento"))
        tabLayoutCamara!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapterCamera = AdapterCamera(this, supportFragmentManager, tabLayoutCamara!!.tabCount)
        viewPagerCamara!!.adapter = adapterCamera

        viewPagerCamara!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutCamara))

        tabLayoutCamara!!.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerCamara!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })*/
        //TabLayout.TabLayoutOnPageChangeListener(tabLayoutCamara)
        postDelayed(2000) {
            this.finish()
        }
    }

    /*fun escanearCodigo(view: View){
        funCamaraPresenter.escanearCodigo(this)
    }*/

    override fun mostrarResultado(codigo: String) {
        println("Codigo: " + codigo)
        //val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra("resultadoCodigo", codigo)
        //startActivity(intent)
        Toast.makeText(this, codigo, Toast.LENGTH_LONG).show()
    }

    override fun mostrarCamara(scannerView: ZXingScannerView) {
        println("Mostrando camara")
        if((ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 101)
        }

        //println(contenedorCamara)
        //contenedorCamara.addView(scannerView)
        setContentView(scannerView)

    }

    override fun finalizarEscaneo() {
        //setContentView(R.layout.activity_main)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //onBackPressed()

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
