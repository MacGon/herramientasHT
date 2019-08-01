package com.simaht.network.remote

import com.google.gson.annotations.SerializedName

class ModelTest(@SerializedName("name") var name : String,
                @SerializedName("brand") var brand : String,
                @SerializedName("activedOn")var activedOn : String,
                @SerializedName("serialNumber")var serialNumber: Int,
                @SerializedName("status") var status : Boolean,
                @SerializedName("bill") var bill: String)