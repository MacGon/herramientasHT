package com.simaht.dashboard_mh.AssignTool.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.baz.simaht.utils.CoConstants.ASSIGN
import com.baz.simaht.utils.CoConstants.ASSIGN.COMFIRMATION
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.dashboard_mh.AssignTool.contracts.AssignToolChildContractI
import com.simaht.dashboard_mh.AssignTool.presenter.AssignToolChildPresenter
import com.simaht.modules.dashboard_mh.AssignTool.view.AddChildCommunication
import com.simaht.utils.SelectableItem
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
        private lateinit var childCommunication: AddChildCommunication
        fun getInstance(callBack: (Boolean) -> Unit, communication: AddChildCommunication): AssignToolChildFragment {
            fragment = AssignToolChildFragment()
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

        rvContentAssignTool.layoutManager = LinearLayoutManager(activity)

        rvContentAssignTool.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        assignToolAdapter = AssignToolAdapter(arrayListOf(), ASSIGN.ASSIGMENT) {
            it?.let {
                Log.e("TAG", "Tool Assigned _ _ _ _ _ _ ")
                childCommunication.onLongClickListener(true)
            }
        }
        //FIXME( this Adapter is an example to set the information inside recyclerview, implements the Adapter with information downloaded from internet)
        rvContentAssignTool.adapter = assignToolAdapter

        assignNewTool.setOnClickListener {
            Log.e("TAG", "--- mierdaaaaaaaaa")
            childCommunication.onClickListen()
        }

        onCreatedOk(true)
    }

    fun addNewTool(newTool: Tool) {
        assignToolAdapter.addNuewTool(SelectableItem(newTool))
        //TransitionManager.beginDelayedTransition(rvContentAssignTool)
    }

    fun addNewTools(toolsFound: ArrayList<SelectableItem<Tool>>) {
        assignToolAdapter.onView = COMFIRMATION
        assignToolAdapter.addNToolsFound(toolsFound)
        //TransitionManager.beginDelayedTransition(rvContentAssignTool)
    }

    fun haveElements() = assignToolAdapter.itemCount > 0

}
