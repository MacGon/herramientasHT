package com.simaht.dashboard_mh.AssignTool.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baz.simaht.utils.CoConstants.ASSIGN
import com.baz.simaht.utils.CoConstants.ASSIGN.COMFIRMATION
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.dashboard_mh.AssignTool.contracts.AssignToolChildContractI
import com.simaht.dashboard_mh.AssignTool.presenter.AssignToolChildPresenter
import kotlinx.android.synthetic.main.fragment_assign_tool_child.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AssignToolChildFragment : Fragment(), AssignToolChildContractI.View {

    private lateinit var presenter: AssignToolChildPresenter
    private lateinit var assignToolAdapter: AssignToolAdapter

    companion object {
        private lateinit var fragment: AssignToolChildFragment
        private lateinit var onCreatedOk: (Boolean) -> Unit
        fun getInstance(callBAck: (Boolean) -> Unit): AssignToolChildFragment {
            fragment = AssignToolChildFragment()
            onCreatedOk = callBAck
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assign_tool_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        rvContentAssignTool.layoutManager = LinearLayoutManager(activity)

        assignToolAdapter = AssignToolAdapter(arrayListOf(), ASSIGN.ASSIGMENT)
        //FIXME( this Adapter is an example to set the information inside recyclerview, implements the Adapter with information downloaded from internet)
//        rvContentAssignTool.adapter = AssignToolAdapter(
//            arrayListOf(Tool("Heramienta 1","PAX 4000", "TODAY", 124, 4213, "$$36",false,"www.myBill.com"),
//                Tool("Heramienta 2","Moto Italika 220", "TODAY", 421, 21423, "$$2624",true,"www.myITALIK.com")),
//            ASSIGN.ASSIGMENT)
        rvContentAssignTool.adapter = assignToolAdapter

        onCreatedOk(true)
    }

    fun addNewTool(newTool: Tool) {
        assignToolAdapter.addNuewTool(newTool)
        //TransitionManager.beginDelayedTransition(rvContentAssignTool)
    }

    fun addNewTools(toolsFound: ArrayList<Tool>) {
        assignToolAdapter.onView = COMFIRMATION
        assignToolAdapter.addNToolsFound(toolsFound)
        //TransitionManager.beginDelayedTransition(rvContentAssignTool)
    }

    //fun getElementsComfirmerd() = assignToolAdapter.getItemsConfirmd()

    fun haveElements() = assignToolAdapter.itemCount > 0

}
