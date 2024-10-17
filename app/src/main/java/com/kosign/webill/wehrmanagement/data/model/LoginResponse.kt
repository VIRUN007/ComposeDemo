package com.kosign.webill.wehrmanagement.data.model

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("id") val id: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("use_intt_id") val useInttId: String,
    @SerializedName("token") val token: String,
    @SerializedName("eml") val email: String,
    @SerializedName("dvsn_CD") val divisionCode: String,
    @SerializedName("dvsn_NM") val divisionName: String,
    @SerializedName("jbcl_NM") val jobClassName: String,
    @SerializedName("flnm") val fullName: String,
    @SerializedName("clph_NO") val phoneNumber: String,
    @SerializedName("prfl_PHTG") val profilePhoto: String?
)