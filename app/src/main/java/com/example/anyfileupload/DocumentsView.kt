package com.example.anyfileupload


interface DocumentsView {
    fun showProgress()
    fun hideProgress()
    fun documentDetailsResponse(result: Int?)
}