package com.simaht.modules.dashboard_mh.tools.employeefound.assignment.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.baz.simaht.login.extensions.addChildFragment
import com.baz.simaht.login.extensions.toast
import com.baz.simaht.utils.CoConstants
import com.baz.simaht.utils.CoConstants.Companion.COME_FROM_CAMERA
import com.baz.simaht.utils.CoConstants.STEP.*
import com.example.dashboard_mh.R
import com.google.zxing.Result
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.presenter.ToolTransferPresenter
import com.simaht.modules.dashboard_mh.scanner.IScanner
import com.simaht.modules.dashboard_mh.scanner.ScannerFragment
import com.simaht.modules.dashboard_mh.tools.DetailActivity
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.contracts.IToolTransferContract
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
class ToolTransferManagerFragment : Fragment(), IToolTransferContract.View, AddChildCommunication2, IScanner {

    private lateinit var presenter: IToolTransferContract.Presenter
    private var fromCamera: Boolean = false
    private var assignTool: Boolean = true
    private lateinit var childFragment: AssignToolChildFragment2
    private var custodyFlow = false
    private var currentStep = ADDING_TOOL
    private lateinit var alertDialog: AlertDialog
    private lateinit var dialogView: View

    companion object {
        private lateinit var fragmentTransfer: ToolTransferManagerFragment
        fun newInstance(comeFromCamera: Boolean): ToolTransferManagerFragment {
            fragmentTransfer = ToolTransferManagerFragment()
            val args = Bundle()
            args.putBoolean(COME_FROM_CAMERA, comeFromCamera)
            fragmentTransfer.arguments = args
            return fragmentTransfer
        }

        fun getInstance(): ToolTransferManagerFragment {
            return fragmentTransfer
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragmentTransfer to allow an interaction in this fragmentTransfer to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            fromCamera = arguments?.getBoolean(COME_FROM_CAMERA) ?: false
        }
        presenter = ToolTransferPresenter(this)

        (activity as DetailActivity).addTitle(resources.getString(R.string.title_tools_flow))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragmentTransfer
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

        presenter.changeStep(true)

        Picasso.get()
                .load("//url.com.mx")
                .placeholder(R.drawable.avatar_employee)
                .error(R.drawable.avatar_employee)
                .into(employeeFace)

    }

    override fun onResume() {
        super.onResume()
        //presenter.changeStep(true)
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
        if (childFragment.haveAnIpad()) {
            prepareAlertDialog()
            custodyFlow = true
        }
        //else
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
        //val intent = Intent(activity, FunCamaraView::class.java)
        //startActivity(intent)
        (activity as DetailActivity).addToBackStack(ScannerFragment.newIntance(this), "FragmentScanner")
    }

    override fun enableAssignationBtn(enable: Boolean) {
        btnContinue.isEnabled = enable
        btnContinue.setTextAppearance(context, R.style.ButtonContinue)
        //btnAssignTools.setTextColor(R.color.colorWhite)

    }

    override fun currentStep(step: CoConstants.STEP) {
        currentStep = step
        //presenter.currentStep()
        //showCurrentView()
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

    private fun prepareAlertDialog() {
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
                //currentStep = DONE
                presenter.changeStep(true)
                presenter.currentStep()
                //showCurrentView()
            }
            EMPLOYEE_FOUND -> {
                //TODO SOMETHING
            }
            else -> {

            }
        }

        dialogBuilder.setView(dialogView)
                .setCancelable(false)
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
                childFragment.changeActionToDo(true) //fixme this is no acceptedhuyvvguyjyc
                btnContinue.text = resources.getString(R.string.msg_ok)
                //currentStep = CUSTODYç
                //presenter.changeStep(true)
                presenter.currentStep()
                //showCurrentView()
            } else if(currentStep == CUSTODY) {
                //currentStep = DONE
                presenter.changeStep(true)
                presenter.currentStep()
                //showCurrentView()
            }
        }

        dialogView.btnDialogCancel.setOnClickListener {
            alertDialog.dismiss()
            if (currentStep == EMPLOYEE_FOUND) {
                //currentStep = SEARCHING_EMPLOYEE
                presenter.changeStep(false)
                //showCurrentView()
            } else if( currentStep == DONE )
                presenter.currentStep()
                //showCurrentView()
        }
    }

    /**************************
     * Result From Scanner QR *
     **************************
     *
     * @param rawResult QR Object result
     */
    override fun returnValue(rawResult: Result?) {
        btnContinue.isEnabled = true
        presenter.addScanedElement(presenter.addTool())
    }

    private fun setOnclickListeners() {

        svEmployeeNumber.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                //presenter.onButtonClick()
                //currentStep = EMPLOYEE_FOUND
                presenter.changeStep(true)
                //prepareAlertDialog()
                //FIXME replace this text by the service responce
                hideKeyboardEvent(svEmployeeNumber)
                employeeAbstract.text = "4253 - JCR"
                employeeName.text = "Armando de los Santos"
                //FIXME this is a fake tools addition
                presenter.toolsFound()
                presenter.currentStep()
                //showCurrentView()
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
                        //currentStep = SEARCHING_EMPLOYEE
                        //showCurrentView()
                        presenter.changeStep(true)
                        //childFragment.validateContinue()
                    } else {
                        btnContinue.isEnabled = false
                        toast("You must add a tool to continue!")
                    }
                }
                SEARCHING_EMPLOYEE -> {
                    //currentStep = EMPLOYEE_FOUND
                    presenter.changeStep(true)
                    //prepareAlertDialog()
                    //FIXME replace this text by the service responce
                    employeeAbstract.text = "4253 - JCR"
                    employeeName.text = "Armando de los Santos"
                    //FIXME this is a fake tools addition
                    presenter.toolsFound()

                    //showCurrentView()
                }
                EMPLOYEE_FOUND -> {
                    //prepareAlertDialog()
                    if (custodyFlow) {
                        //currentStep = CUSTODY
                        presenter.changeStep(true)
                        //FIXME new employee needs a different way
                    } else
                        presenter.changeStep(true, true)
                        //currentStep = NEW_EMPLOYEE

                    tvInstructions.text = resources.getText(R.string.msg_gestion_tool)
                    //TODO ASET THE VIEW ON Actions TODO
                    showMessage(msgStr = "Custodia de Herramientas")
                }
                CUSTODY -> {
                    toast("CUSTODY SUCCESS **TEST**")
                    //prepareAlertDialog()
                    childFragment.custodiateTools()
                    presenter.changeStep(true)
                }
                NEW_EMPLOYEE -> {
                }

                DONE -> {
                    clProcesFinshed.visibility = View.VISIBLE
                    //currentStep = FINISH
                    presenter.changeStep(true)

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


    /**
     * Update the view components every time that current step change
     */
    /*private fun showCurrentView() {
        when (currentStep) {
            ADDING_TOOL -> {

            }
            SEARCHING_EMPLOYEE -> {

            }
            EMPLOYEE_FOUND -> {

            }
            CUSTODY -> { //TODO It could be named different

            }
            NEW_EMPLOYEE -> {

            }
            DONE -> {

                //activity?.finish()
            }
        }
    }*/

    override fun showAddingToolView() {
        rlEmployeeInfo.visibility = View.GONE
        svEmployeeNumber.visibility = View.GONE
        containerAssignation.visibility = View.VISIBLE
        val text = resources.getQuantityString(R.plurals.msg_step_search_employee, 1)
        tvInstructions.text = text
        btnAddTool.visibility = View.VISIBLE
        newEmployee.visibility = View.GONE
    }

    override fun showSearchingEmployeeView() {
        //btnContinue.isEnabled = false
        rlEmployeeInfo.visibility = View.GONE
        svEmployeeNumber.visibility = View.VISIBLE
        containerAssignation.visibility = View.INVISIBLE
        btnAddTool.visibility = View.GONE
        btnContinue.text = resources.getString(R.string.msg_search)
        childFragment.removeToolsFound()
    }

    override fun showEmployeeFoundView() {
        rlEmployeeInfo.visibility = View.VISIBLE
        svEmployeeNumber.visibility = View.GONE
        containerAssignation.visibility = View.VISIBLE
        btnAddTool.visibility = View.GONE
        //TODO validate new employee
        //newEmployee.visibility = View.VISIBLE
        btnContinue.text = resources.getString(R.string.msg_continue) //Continuar
        childFragment.changeActionToDo(false)
        //FIXME check actions to set in every tool
        //btnContinue.isEnabled = false
        clProcesFinshed.visibility = View.GONE

        if (custodyFlow) {
            //prepareAlertDialog()
        }
    }

    override fun showActionsView() {
        rlEmployeeInfo.visibility = View.VISIBLE
        svEmployeeNumber.visibility = View.GONE
        containerAssignation.visibility = View.VISIBLE
        btnAddTool.visibility = View.GONE
        clProcesFinshed.visibility = View.GONE
    }

    override fun showNewEmployeeView() {
        rlEmployeeInfo.visibility = View.VISIBLE
        newEmployee.visibility = View.VISIBLE
        svEmployeeNumber.visibility = View.GONE
        containerAssignation.visibility = View.INVISIBLE
        btnAddTool.visibility = View.GONE
        //TODO change by a resource and depends if it its a unic mesagge for all @{actions}
        showMessage(msgStr = "Aplicando Custodia")
    }

    override fun showProcesDoneView() {
        rlEmployeeInfo.visibility = View.VISIBLE
        svEmployeeNumber.visibility = View.GONE
        containerAssignation.visibility = View.VISIBLE
        btnAddTool.visibility = View.GONE
        clProcesFinshed.visibility = View.GONE
    }

    override fun finishProcess() {
        (activity as DetailActivity).proceesDone()
    }

    private fun hideKeyboardEvent(view: View) {
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

    /**
     * In this Case True is used to increase its step, False to decrease its step
     *
     */
    fun goBack(){
        presenter.changeStep(false) //False because back means return back \/ (decrease)
    }

}
