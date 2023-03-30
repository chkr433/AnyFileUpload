package com.example.anyfileupload

import android.util.Log
import com.example.anyfileupload.data.ApiCallinterface
import com.example.anyfileupload.data.DocumentUploadResponse
import com.example.anyfileupload.data.ServiceGenerator
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentsPresenter : BasePresenter<DocumentsView?>() {
    fun sendDocumentData(file: MultipartBody.Part?, obj: DocumentForm?, user_id: String?) {
        view!!.showProgress()
        val service = ServiceGenerator.createService(ApiCallinterface::class.java)
        val jsonData = RequestBody.create(MediaType.parse("multipart/form-data"), Gson().toJson(obj))
        val call = service.addDocument(jsonData, file, user_id, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMTQ0Iiwic3ViIjoiMTAxNS9rb3RpQGd6LmNvbSIsInNjb3BlcyI6IkZfQURIT0NfRFVUWSxGX0xFQVZFLFJPTEVTX1dSSVRFLFBST0RfUkVBRCxGX1VOSVQsRU1FUkdFTkNZX0hJR0gsTk9USUZJQ0FUSU9OU19SRUFELFZFTkRPUl9SRUFELFJPU1RFUl9SRUFELEtZRSxBVURJVF9SRUFELE1BTkFHRVIsRl9OT1RJRklDQVRJT04sQURNSU5fQVRURU5EQU5DRSxWRU5ET1JfV1JJVEUsRl9OT19UUkFDS0lORyxTSElGVF9SRUFELERVVFlfV1JJVEUsRl9TSU5HTEVfU0hJRlQsUkVHSU9OX1dSSVRFLEZfRkFDRV9SRUMsQVBQX0xPR0lOLEZfUE9TVCxCUkFOQ0hfV1JJVEUsRl9QUE9JTlQsUk9TVEVSX0FQUFJPVkFMLEVNRVJHRU5DWV9BTEVSVFNfR0xPQkFMLFJPU1RFUl9XUklURSxWSVNJVE9SX01BTkFHTUVOVCxQRVJNSVNTSU9OX1dSSVRFLFBFUk1JU1NJT05fUkVBRCxVU0VSX1dSSVRFLEVNRVJHRU5DWV9MT1csUkVHSU9OX1JFQUQsTk9USUZJQ0FUSU9OU19XUklURSxMRUFWRV9XUklURSxQUk9EX1dSSVRFLEVNRVJHRU5DWV9NRURJVU0sUE9TVF9XUklURSxGX1JPU1RFUixGX0FVVE9fS0lPU0ssRE9DVU1FTlRTLEZfUkVHSU9OLEZfTVVMVElQTEVfU0hJRlQsRklOQU5DRSxGX09OREVNQU5ELFBBWVJPTExfR0VOLEZfREFTSEJPQVJELEVNRVJHRU5DWV9XUklURSxGX1NUUklDVF9BUkVBLFNISUZUX1dSSVRFLFNBTEVfUkVBRCxQUE9JTlRTX1dSSVRFLFBPTExfUkVBRCxMRUFWRV9SRUFELEZfRFVUWSxEVVRZX1JFQUQsREVGQVVMVFBBU1NXT1JELEVNRVJHRU5DWV9SRUFELFNBTEVfV1JJVEUsQlJBTkNIX1JFQUQsUkVQT1JUU19XUklURSxBVFRFTkRBTkNFLFBPU1RfUkVBRCxQT0xMX1dSSVRFLEZfT0ZGTElORV9UUkFDS0lORyxQUE9JTlRTX1JFQUQsRl9BVFRFTkRBTkNFLEZfVVNFUixGX1NBTEUsRl9BTEVSVF9UUkFDS0lORyxST0xFU19SRUFELExFQVZFX0FQUFJPVkFMLEFETUlOX1BPUlRBTCxGX0NPTlRJTk9VU19UUkFDS0lORyxGX0xPQ0FUSU9OX1RSQUNLLFNZU1RFTV9TRVRUSU5HUyxGX0VfQUxFUlRTIiwiaWF0IjoxNjgwMTY1NjUxLCJleHAiOjE2OTU3MTc2NTF9.Fro8ZZyWB7NY3vurDFjmYxfEvNVIhf_qXEvwLDuad04")
        call.enqueue(object : Callback<DocumentUploadResponse?> {
            override fun onResponse(call: Call<DocumentUploadResponse?>, response: Response<DocumentUploadResponse?>) {
                view!!.hideProgress()
                assert(response.body() != null)
                try {
                    view!!.documentDetailsResponse(response.code())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<DocumentUploadResponse?>, t: Throwable) {
                Log.i("DocumentError",t.toString());

            }
        })
    }
}