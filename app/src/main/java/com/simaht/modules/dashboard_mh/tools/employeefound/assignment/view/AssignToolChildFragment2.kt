package com.simaht.modules.dashboard_mh.tools.employeefound.assignment.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.baz.simaht.utils.CoConstants
import com.baz.simaht.utils.CoConstants.ASSIGN
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.contracts.AssignToolChildContractI
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.presenter.AssignToolChildPresenter
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.fragment_assign_tool_child.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AssignToolChildFragment2 : Fragment(), AssignToolChildContractI.View {


    private lateinit var presenter: AssignToolChildPresenter
    private lateinit var assignToolAdapter: AssignToolAdapter

    companion object {
        private lateinit var fragment: AssignToolChildFragment2
        private lateinit var onCreatedOk: (Boolean) -> Unit
        private lateinit var childCommunication: AddChildCommunication2
        fun getInstance(callBack: (Boolean) -> Unit, communication: AddChildCommunication2): AssignToolChildFragment2 {
            fragment = AssignToolChildFragment2()
            onCreatedOk = callBack
            childCommunication = communication
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assign_tool_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvContentTool.layoutManager = LinearLayoutManager(activity)
        rvContentTool.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        assignToolAdapter = AssignToolAdapter(arrayListOf(), ASSIGN.ASSIGMENT, false) {
            if (it)
                childCommunication.toolsNeedCustody(it)

        }
        //FIXME( this Adapter is an example to set the information inside recyclerview, implements the Adapter with information downloaded from internet)
        //TODO (Unassigned all tools remove the asignation botton available..
        rvContentTool.adapter = assignToolAdapter

        assignNewTool.setOnClickListener {
            childCommunication.onClickListen()
        }

        onCreatedOk(true)
    }

    fun addNewTool(newTool: Tool) {
        assignToolAdapter.addNuewTool(SelectableItem(newTool))
        //TransitionManager.beginDelayedTransition(rvContentAssignTool)
    }

    fun addNewTools(toolsFound: List<SelectableItem<Tool>>) {
        assignToolAdapter.addToolsFound(toolsFound)
        //TransitionManager.beginDelayedTransition(rvContentAssignTool)
    }

    fun haveElements() = assignToolAdapter.itemCount > 0

    fun haveAnIpad() = assignToolAdapter.haveCustody()

    override fun validateContinue() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun changeActionToDo(levelUp: Boolean) {
        if(levelUp)
            assignToolAdapter.chanveView(ASSIGN.SET_ACTION)
        else
            assignToolAdapter.chanveView(ASSIGN.ASSIGMENT)
    }

    fun custodiateTools() {
        assignToolAdapter.applyCustudy()
    }

    fun selectAction() {
        assignToolAdapter.setAction(CoConstants.ACTIONS.CUSTODY)
    }

    fun elements() = assignToolAdapter.itemCount

    fun removeToolsFound(){
        assignToolAdapter.removeTemporalTools()
    }
}
