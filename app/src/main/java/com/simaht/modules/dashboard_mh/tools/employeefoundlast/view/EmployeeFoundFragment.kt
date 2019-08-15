package com.simaht.modules.dashboard_mh.tools.employeefoundlast.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.FragmentCommunication
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.adapter.ToolListFoundAdapter
import com.simaht.utils.SelectableItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_employee_found.*
import kotlinx.android.synthetic.main.fragment_employee_found.btnContinue
import kotlinx.android.synthetic.main.fragment_employee_found.rvContentTool


class EmployeeFoundFragment : Fragment() {

    private val NEW_EMPLOYE = "IS_NEW_EMPLOYEE"
    private val TOOLS_FOUND = "TOOLS_FOUND"
    private var newEmployee: Boolean? = null
    private var toolsFound: Boolean? = null
    private lateinit var parentView : FragmentCommunication
    private lateinit var toolListFound : ArrayList<SelectableItem<Tool>>
    private lateinit var adapter : ToolListFoundAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newEmployee = it.getBoolean(NEW_EMPLOYE)
            toolsFound = it.getBoolean(TOOLS_FOUND)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_found, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newEmployee?.let {
            btnAddTool.isEnabled = it
        }

        rvContentTool.layoutManager = LinearLayoutManager(activity)
        rvContentTool.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        adapter = ToolListFoundAdapter(toolListFound, true)

        rvContentTool.adapter = adapter

        Picasso.get()
                .load("//url.com.mx")
                .placeholder(R.drawable.avatar_employee)
                .error(R.drawable.avatar_employee)
                .into(employeeFace)

        btnContinue.setOnClickListener {
            parentView.nextFragment()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(isNewEmployee: Boolean, toolsFound: Boolean, tools: ArrayList<SelectableItem<Tool>> , communication : FragmentCommunication) =
                EmployeeFoundFragment().apply {
                    toolListFound = tools
                    parentView = communication
                    arguments = Bundle().apply {
                        putBoolean(NEW_EMPLOYE, isNewEmployee)
                        putBoolean(TOOLS_FOUND, toolsFound)
                    }
                }
    }
}
