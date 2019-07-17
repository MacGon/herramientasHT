package com.simaht.dashboard_mh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.dashboard_mh.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.simaht.camara.view.FunCamaraView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,DashBoardFragment.resultInterface, DashBoardFragment.chargeToolInterface, DashBoardFragment.assignToolInterface, DashBoardFragment.liftInventoryInterface, DashBoardFragment.unsubscribeTool, DashBoardFragment.integrateFile {


    lateinit var container: FrameLayout
    val fm = supportFragmentManager

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->


        when (item.itemId) {
            R.id.navigation_home -> {

                val fragmentDashBoard = DashBoardFragment()
                val transactionDashBoard = fm.beginTransaction()
                transactionDashBoard.replace(container.id,fragmentDashBoard)
                transactionDashBoard.commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_calendario -> {

                val fragmentCalendar = CalendarFragment()
                val transactionCalendar = fm.beginTransaction()
                transactionCalendar.replace(container.id, fragmentCalendar)
                transactionCalendar.commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_camara -> {

                /*val fragmentCamara = CamaraFragment()
                val transactionCamara = fm.beginTransaction()
                transactionCamara.replace(container.id, fragmentCamara)
                transactionCamara.commit()*/
                val intent = Intent(this@MainActivity, FunCamaraView::class.java)
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_busqueda -> {

                val fragmentIntelligentSearch = IntelligentSearchFragment()
                val transactionIntelligentSearch = fm.beginTransaction()
                transactionIntelligentSearch.replace(container.id, fragmentIntelligentSearch)
                transactionIntelligentSearch.commit()

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Frame DashBoard
        container = findViewById(R.id.frameContainer)


        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        nav_view.selectedItemId = R.id.navigation_home
        //Tab bar - Fin

        //MH
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_container)
        val navViewTwo: NavigationView = findViewById(R.id.nav_view_MH)
        val toggle = ActionBarDrawerToggle(
           this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
       )
       drawerLayout.addDrawerListener(toggle)
       toggle.syncState()

        navViewTwo.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_container)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val notificationsActivity = Intent(this, Notifications::class.java)
        val c4Activity = Intent(this, C4Help::class.java)

         when (item.itemId) {
             R.id.action_help -> startActivity(c4Activity)
             R.id.action_notifications -> startActivity(notificationsActivity)
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val notificationsActivity = Intent(this, Notifications::class.java)
        when (item.itemId) {
            R.id.itemMisPendientes -> {
                nav_view.selectedItemId = R.id.navigation_calendario
            }
            R.id.itemRegion -> {

            }
            R.id.itemHome -> {
                nav_view.selectedItemId = R.id.navigation_home

            }
            R.id.itemInventario -> {

            }
            R.id.itemFormatos -> {

            }
            R.id.itemExpedientes -> {

            }
            R.id.itemFacturas -> {

            }
            R.id.itemAsignarHerramienta -> {
                    showAssignToolFragment()
            }
            R.id.itemCobrarHerramienta -> {
                    showChargeToolFragment()
            }
            R.id.itemComunicados -> {
                startActivity(notificationsActivity)
            }
            R.id.itemCapacitaciones -> {

            }
            R.id.itemEnvios -> {

            }
            R.id.itemAyuda -> {

            }
            R.id.itemCerrarSesion -> {
                finish()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_container)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showResultsFragment() {
        val fragmentResults = ResultsFragment()
        val transactionResults = fm.beginTransaction()
        transactionResults.replace(container.id,fragmentResults)
        transactionResults.commit()
    }

    override fun showChargeToolFragment() {
        val fragmentChargeTool = ChargeToolFragment()
        val transactionChargeTool = fm.beginTransaction()
        transactionChargeTool.replace(container.id,fragmentChargeTool)
        transactionChargeTool.commit()
    }

    override fun showAssignToolFragment() {
        val fragmentAssignTool = AssignToolFragment()
        val transactionAssignTool = fm.beginTransaction()
        transactionAssignTool.replace(container.id,fragmentAssignTool)
        transactionAssignTool.commit()
    }

    override fun showLiftInventoryFragment() {
        val fragmentLiftInventory = LiftInventoryFragment()
        val transactionLiftInventory = fm.beginTransaction()
        transactionLiftInventory.replace(container.id,fragmentLiftInventory)
        transactionLiftInventory.commit()
    }

    override fun showUnsubscribeToolFragment() {
        val fragmentUnsubscribeTool = UnsubscribeToolFragment()
        val transactionUnsubscribeTool = fm.beginTransaction()
        transactionUnsubscribeTool.replace(container.id,fragmentUnsubscribeTool)
        transactionUnsubscribeTool.commit()
    }

    override fun showIntegrateFileFragment() {
        val fragmentIntegrateFile = IntegrateFileFragment()
        val transactionIntegrateFile = fm.beginTransaction()
        transactionIntegrateFile.replace(container.id,fragmentIntegrateFile)
        transactionIntegrateFile.commit()
    }
}

