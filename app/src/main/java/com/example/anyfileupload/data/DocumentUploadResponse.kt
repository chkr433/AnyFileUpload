package com.example.anyfileupload.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class DocumentUploadResponse {
    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("response")
    @Expose
    private var response: Any? = null

    @SerializedName("serverTime")
    @Expose
    private var serverTime: Int? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getResponse(): Any? {
        return response
    }

    fun setResponse(response: Any?) {
        this.response = response
    }

    fun getServerTime(): Int? {
        return serverTime
    }

    fun setServerTime(serverTime: Int?) {
        this.serverTime = serverTime
    }
}