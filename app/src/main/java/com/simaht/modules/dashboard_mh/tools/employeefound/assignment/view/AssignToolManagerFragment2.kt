package com.simaht.modules.dashboard_mh.tools.employeefound.assignment.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.baz.simaht.login.extensions.addChildFragment
import com.baz.simaht.login.extensions.toast
import com.baz.simaht.utils.CoConstants.Companion.COME_FROM_CAMERA
import com.baz.simaht.utils.CoConstants.STEP.*
import com.example.dashboard_mh.R
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.zxing.Result
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.dashboard_mh.AssignTool.presenter.AssignToolPresenter2
import com.simaht.modules.camara.view.FunCamaraView
import com.simaht.modules.dashboard_mh.scanner.IScanner
import com.simaht.modules.dashboard_mh.scanner.ScannerFragment
import com.simaht.modules.dashboard_mh.tools.DetailActivity
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.contracts.AssignToolContractI
import com.simaht.modules.test_camera.view.TestCamera
import com.simaht.utils.SelectableItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.dialog_notification.view.*
import kotlinx.android.synthetic.main.fragment_assign_tool_manager.*
import kotlinx.android.synthetic.main.fragment_search_employee.*


/**
 * A simple [Fragment] subclass.
 *
 */
class AssignToolManagerFragment2 : Fragment(), AssignToolContractI.View, AddChildCommunication2, IScanner {

    private lateinit var presenter: AssignToolContractI.Presenter
    private lateinit var presenter2: AssignToolPresenter2
    private var fromCamera: Boolean = false
    private var assignTool: Boolean = true
    private lateinit var childFragment: AssignToolChildFragment2
    private var custodyFlow = false
    private var currentStep = ADDING_TOOL
    //lateinit var dialog: AlertDialog
    //lateinit var alert: AlertDialog.Builder
    lateinit var viewAlert: View
    lateinit var alertDialog: AlertDialog
    lateinit var dialogView: View
    private val codeScanner: Int = 1
    private val KEY_DATA: String = "DATA"
    private lateinit var gson: Gson
    private var flagScaner: Boolean = false

    companion object {
        fun getInstance(comeFromCamera: Boolean): AssignToolManagerFragment2 {
            val fragment = AssignToolManagerFragment2()
            val args = Bundle()
            args.putBoolean(COME_FROM_CAMERA, comeFromCamera)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            fromCamera = arguments?.getBoolean(COME_FROM_CAMERA) ?: false
        }
        gson = Gson()
        presenter = AssignToolPresenter2(this)

        (activity as DetailActivity).addTitle(resources.getString(R.string.title_tools_flow))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragment = AssignToolChildFragment2.getInstance({
            if (it && fromCamera) {
                (activity as DetailActivity).nav_view.visibility = View.GONE
                presenter.openScanner()
            }
        }, this)


        addChildFragment(childFragment, containerAssignation.id)
        //Fixme ask in the service to get Tools (until Employee will be validate!)
        //presenter.validateInformationExistent()
        setOnclickListeners()

        Picasso.get()
                .load("//url.com.mx")
                .placeholder(R.drawable.avatar_employee)
                .error(R.drawable.avatar_employee)
                .into(employeeFace)

    }

    override fun onResume() {
        super.onResume()
        showCurrentView()
    }

    override fun showLoader() {
        //Loader.show
    }

    override fun hideLoader() {
        //Hide Loader
    }

    override fun putEmployeeData(infoEmployee: String) {
//        etEmployeeNumberAT.isEnabled = false
    }

    override fun putToolsFound(tools: ArrayList<SelectableItem<Tool>>) {
        childFragment.addNewTools(tools)
    }

    override fun addItem(scannedTool: Tool) {
        (activity as DetailActivity).nav_view.visibility = View.VISIBLE
        childFragment.addNewTool(scannedTool)
    }

    override fun addItemFound(toolFound: List<SelectableItem<Tool>>) {
        childFragment.addNewTools(toolFound)
    }

    override fun showMessage(msgInt: Int?, msgStr: String?) {
        when {
            msgInt != null ->
                toast(resources.getString(msgInt))
            msgStr != null ->
                toast(msgStr)
        }
    }

    override fun readQR() {
        val intent = Intent(activity, TestCamera::class.java)
        startActivityForResult(intent, codeScanner)
        //(activity as DetailActivity).addToBackStack(ScannerFragment.newIntance(this), "FragmentScanner")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == codeScanner) {
            if (resultCode == Activity.RESULT_OK) {
                val code: String = data?.extras!!.getString(KEY_DATA, "")
                println("HOLAAAAA $code")
                try {
                    val obj: JsonObject = gson.fromJson(code, JsonObject::class.java)
                    presenter.getToolInfo(obj.get("numControl").asString)
                    flagScaner = true
                } catch (e: java.lang.Exception) {
                    flagScaner = false
                    onMessageError("Error al obtener información del servicio")
                    println("Error al obtener información del servicio")
                }
            } else {
                //presenter.onBackPressed()
            }
        }
    }

    override fun onMessageError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

    override fun enableAssignationBtn(enable: Boolean) {
        btnContinue.isEnabled = enable
        btnContinue.setTextAppearance(context, R.style.ButtonContinue)
        //btnAssignTools.setTextColor(R.color.colorWhite)

    }

    override fun onClickListen() {
        Log.i("TAG", "OMG ..... ")
        presenter.openScanner()
    }

    @SuppressLint("ResourceAsColor")
    override fun onLongClickListener(itemSelected: Boolean) {
        if (itemSelected) {
            btnAssignTools.isEnabled = true
            btnAssignTools.setTextAppearance(context, R.style.ButtonComfirmToolAvailable)
            btnAssignTools.setTextColor(R.color.colorWhite)
        }
    }

    override fun toolsNeedCustody(custody: Boolean) {
        custodyFlow = custody
    }

    fun prepareAlertDialog() {


        //viewAlert = activity!!.layoutInflater.inflate(R.layout.dialog_notification, null)
        //alert = AlertDialog.Builder(context)
        //.setView(viewAlert)
        //.setCancelable(false)


        val dialogBuilder = AlertDialog.Builder(activity)
        dialogView = layoutInflater.inflate(R.layout.dialog_notification, null)

        when (currentStep) {
            SEARCHING_EMPLOYEE -> {

            }
            CUSTODY -> {
                dialogView.ivImageAlert.visibility = View.VISIBLE
                dialogView.tvNormalDialog.text = String.format(resources.getString(R.string.msg_custody_tool), employeeName.text.toString())
                currentStep = DONE
                showCurrentView()
            }
            EMPLOYEE_FOUND -> {
                //TODO SOMETHING
            }
            else -> {

            }
        }

        dialogBuilder.setView(dialogView)
                .setCancelable(true)
        alertDialog = dialogBuilder.create()
        alertDialog.show()


        /*val builder = AlertDialog.Builder(context, R.style.DialogStyle)//Context is activity context
        viewAlert = activity!!.layoutInflater.inflate(R.layout.dialog_notification, null, false)*/


        /*
        builder.setView(viewAlert)
                .setCancelable(false)
                .setMessage("")
                .setTitle(null)
                .setMessage(null)
        val lp = /*LinearLayout.LayoutParams(500,
                LinearLayout.LayoutParams.MATCH_PARENT)*/
        ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
        viewAlert.layoutParams = lp
        builder.setView(viewAlert)



        dialog = builder.create()
        setDimentionsDialog()*/
        //dialog.show()

        dialogView.btnDialogOK.setOnClickListener {
            alertDialog.dismiss()
            if (currentStep == EMPLOYEE_FOUND) {
                childFragment.selectAction()
                btnContinue.text = resources.getString(R.string.msg_ok)
                currentStep = CUSTODY
                showCurrentView()
            } else if(currentStep == CUSTODY) {
                currentStep = DONE
                showCurrentView()
            }
        }

        dialogView.btnDialogCancel.setOnClickListener {
            alertDialog.dismiss()
            if (currentStep == EMPLOYEE_FOUND) {
                currentStep = SEARCHING_EMPLOYEE
                showCurrentView()
            } else if( currentStep == DONE )
                showCurrentView()
        }
    }

    /**************************
     * Result From Scanner QR *
     **************************
     *
     * @param rawResult QR Object result
     */
    override fun returnValue(rawResult: Result?) {
        println("ADIOS $rawResult")
        presenter.getToolInfo(rawResult?.text!!)
        //presenter.addScanedElement(presenter.addTool())
    }

    fun setOnclickListeners() {

        svEmployeeNumber.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                //presenter.onButtonClick()
                currentStep = EMPLOYEE_FOUND
                //prepareAlertDialog()
                //FIXME replace this text by the service responce
                hideKeyboardEvent(svEmployeeNumber)
                employeeAbstract.text = "4253 - JCR"
                employeeName.text = "Armando de los Santos"
                //FIXME this is a fake tools addition
                presenter.toolsFound()
                showCurrentView()
                return@OnKeyListener true
            }
            false
        })

        clTools.setOnClickListener {
            hideKeyboardEvent(svEmployeeNumber)
        }

        btnContinue.setOnClickListener {
            when (currentStep) {
                ADDING_TOOL -> {
                    if (childFragment.haveElements()) {
                        currentStep = SEARCHING_EMPLOYEE
                        showCurrentView()
                        //childFragment.validateContinue()
                    } else {
                        btnContinue.isEnabled = false
                        toast("You must add a tool to continue!")
                    }
                }
                SEARCHING_EMPLOYEE -> {
                    currentStep = EMPLOYEE_FOUND
                    prepareAlertDialog()
                    //FIXME replace this text by the service responce
                    employeeAbstract.text = "4253 - JCR"
                    employeeName.text = "Armando de los Santos"

                    //FIXME this is a fake tools addition
                    presenter.toolsFound()



                    showCurrentView()
                }
                EMPLOYEE_FOUND -> {
                    //prepareAlertDialog()
                    if (custodyFlow) {
                        currentStep = CUSTODY
                    } else
                        currentStep = NEW_EMPLOYEE

                    tvInstructions.text = resources.getText(R.string.msg_gestion_tool)
                    //TODO ASET THE VIEW ON Actions TODO
                    showMessage(msgStr = "Custodia de Herramientas")
                }
                CUSTODY -> {
                    toast("CUSTODY SUCCESS **TEST**")
                    prepareAlertDialog()
                    childFragment.custodiateTools()
                }
                NEW_EMPLOYEE -> {
                }

                DONE -> {
                    clProcesFinshed.visibility = View.VISIBLE
                    currentStep = FINISH

                }
                FINISH -> {
                    (activity as DetailActivity).proceesDone()
                }

            }
        }

        btnAddTool.setOnClickListener {
            presenter.openScanner()
            (activity as DetailActivity).nav_view.visibility = View.GONE
        }

        svEmployeeNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                btnContinue.isEnabled = !s.isEmpty()
            }
        })


    }

    fun showCurrentView() {
        when (currentStep) {
            ADDING_TOOL -> {
                rlEmployeeInfo.visibility = View.GONE
                svEmployeeNumber.visibility = View.GONE
                containerAssignation.visibility = View.VISIBLE
                btnAddTool.visibility = View.VISIBLE
                newEmployee.visibility = View.GONE
            }
            SEARCHING_EMPLOYEE -> {
                btnContinue.isEnabled = false
                rlEmployeeInfo.visibility = View.GONE
                svEmployeeNumber.visibility = View.VISIBLE
                containerAssignation.visibility = View.INVISIBLE
                btnAddTool.visibility = View.GONE
                newEmployee.visibility = View.GONE
                btnContinue.text = resources.getString(R.string.msg_search)

            }
            EMPLOYEE_FOUND -> {
                rlEmployeeInfo.visibility = View.VISIBLE
                svEmployeeNumber.visibility = View.GONE
                containerAssignation.visibility = View.VISIBLE
                btnAddTool.visibility = View.GONE
                //TODO validate new employee
                newEmployee.visibility = View.VISIBLE
                btnContinue.text = resources.getString(R.string.msg_continue) //Continuar

                if (custodyFlow) {
                    prepareAlertDialog()
                }

            }
            CUSTODY -> {
                rlEmployeeInfo.visibility = View.VISIBLE
                svEmployeeNumber.visibility = View.GONE
                containerAssignation.visibility = View.VISIBLE
                btnAddTool.visibility = View.GONE
            }
            NEW_EMPLOYEE -> {
                rlEmployeeInfo.visibility = View.VISIBLE
                newEmployee.visibility = View.VISIBLE
                svEmployeeNumber.visibility = View.GONE
                containerAssignation.visibility = View.INVISIBLE
                btnAddTool.visibility = View.GONE
                showMessage(msgStr = "Aplicando Custodia")
            }
            DONE -> {
                rlEmployeeInfo.visibility = View.VISIBLE
                svEmployeeNumber.visibility = View.GONE
                containerAssignation.visibility = View.VISIBLE
                btnAddTool.visibility = View.GONE

                //activity?.finish()
            }
        }
    }

    fun hideKeyboardEvent(view: View) {
        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setDimentionsDialog() {

        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        //dialog.window!!.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        val metrics = alertDialog.context.resources.displayMetrics
        val dispWidth = metrics.widthPixels
        val dispHeight = metrics.heightPixels

        val lp = WindowManager.LayoutParams()

        lp.copyFrom(alertDialog.window!!.attributes)
        //val dialogWindowWidth = (dispWidth * 0.80f).toInt()
        //val dialogWindowHeight = (dispHeight * 0.83f).toInt()

        //lp.width = dialogWindowWidth
        //lp.height = dialogWindowHeight


        alertDialog?.window?.attributes = lp
    }

}
