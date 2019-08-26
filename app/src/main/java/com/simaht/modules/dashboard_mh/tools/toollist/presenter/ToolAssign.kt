package com.simaht.modules.dashboard_mh.tools.employeefound.assignment.presenter

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.network.data.OutModelTool
import com.simaht.utils.JsonFile

class ToolAssign {

    companion object {
        const val FILENAMETOOL = "Tool.json"
    }

    @Transient

    private var gson: Gson
    private val TAG: String = ToolAssign::class.java.name

    var toolsArray:ArrayList<OutModelTool>

    init {
        toolsArray = ArrayList()
        gson = Gson()
        loadInfoTool()
    }

    private fun loadInfoTool() {
        if (!JsonFile.existeArchivo(FILENAMETOOL)) {
            update()
        }
        val str: String = JsonFile.leerArchivo(FILENAMETOOL)
        val tools = gson.fromJson(str, JsonArray::class.java)

        if (tools != null) {
            for (tool in tools) {
                toolsArray.add(gson.fromJson(tool.asJsonObject,OutModelTool::class.java))
            }
        }
    }

    fun update() {
        JsonFile.guardarArchivo(FILENAMETOOL, gson.toJson(toolsArray))
    }

    fun validateControlId(tool :OutModelTool):Boolean {
        return toolsArray.contains(tool)
    }

    fun deleteTool(pos: Int){
        toolsArray.removeAt(pos)
        update()
    }

    fun clearFile(){
        toolsArray.clear()
        update()
    }
}