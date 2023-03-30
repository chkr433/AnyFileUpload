package com.example.anyfileupload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentForm {
    @SerializedName("docTypeId")
    @Expose
    private String docTypeId;
    @SerializedName("orgId")
    @Expose
    private String orgId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("fileType")
    @Expose
    private String fileType;

    public String getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(String docTypeId) {
        this.docTypeId = docTypeId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
