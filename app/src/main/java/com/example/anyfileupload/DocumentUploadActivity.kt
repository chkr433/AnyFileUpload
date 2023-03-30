package com.example.anyfileupload

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DocumentUploadActivity : AppCompatActivity(),DocumentsView {
    var documentsPresenter: DocumentsPresenter? = null
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_upload)
        documentsPresenter = DocumentsPresenter()
        documentsPresenter!!.attachView(this)
        var uploadDoc = findViewById<Button>(R.id.button)
        uploadDoc.setOnClickListener { view: View? ->
            if (ContextCompat.checkSelfPermission(
                    this@DocumentUploadActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(
                    this@DocumentUploadActivity,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                ActivityCompat.requestPermissions(
                    this@DocumentUploadActivity,
                    arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    2
                )
            }else {
                openFile()
            }

        }
    }

    override fun showProgress() {
        try {
            if (dialog != null && !dialog!!.isShowing) dialog!!.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun hideProgress() {
        try {
            if (dialog != null && dialog!!.isShowing) dialog!!.dismiss()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun documentDetailsResponse(result: Int?) {
        TODO("Not yet implemented")
    }
    fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            addCategory(Intent.CATEGORY_OPENABLE)
            flags = flags or Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        startActivityForResult(intent, 2)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if (((resultCode == Activity.RESULT_OK) && (resultData != null))&&(requestCode == 2)) {

            resultData?.data?.also { documentUri ->
                contentResolver.takePersistableUriPermission(documentUri,Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val documentForm = DocumentForm()
                documentForm.docTypeId = "1006"
                documentForm.orgId = "1015"
                documentForm.userId = "1144"
                documentForm.active = true
                documentForm.fileType = "1"
                val documentFile = File(resultData?.data?.path)
                val requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), documentFile)
                val body = MultipartBody.Part.createFormData("file", documentFile.name, requestFile)
                documentsPresenter!!.sendDocumentData(body, documentForm, "1144")


            }

        }

    }

}