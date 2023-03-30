package com.example.anyfileupload.data;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiCallinterface {
    @Headers({"Content-Type: multipart/form-data","accept: */*"})
    @Multipart
    @POST(Urls.ADD_DOCUMENT)
    Call<DocumentUploadResponse> addDocument(@Part("userDoc") RequestBody jsonData,
                                             @Part MultipartBody.Part file,
                                             @Path("userId") String userId,
                                             @Header("Authorization") String token);

}
