package com.kosign.webill.wehrmanagement.data.model

import com.google.gson.annotations.SerializedName

data class UserSummaryResponse(
    @SerializedName("attendanceCount") val attendanceCount: Int,
    @SerializedName("overtimeCount") val overtimeCount: Int,
    @SerializedName("totalOverTimePerMonth") val totalOverTimePerMonth: Int,
    @SerializedName("overtimeUsedCount") val overtimeUsedCount: Int,
    @SerializedName("leaveBalances") val leaveBalances: LeaveBalances
)

data class LeaveBalances(
    @SerializedName("annual_leave_available") val annualLeaveAvailable: Double,
    @SerializedName("sick_leave_taken") val sickLeaveTaken: Double,
    @SerializedName("sick_leave_available") val sickLeaveAvailable: Double,
    @SerializedName("special_leave_available") val specialLeaveAvailable: Double,
    @SerializedName("annual_leave_taken") val annualLeaveTaken: Double,
    @SerializedName("special_leave_taken") val specialLeaveTaken: Double
)