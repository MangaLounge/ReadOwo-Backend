package com.readowo.api.publishing.Services.Communication;

import com.readowo.api.Shared.BaseResponse;
import com.readowo.api.publishing.Models.SagaStatus;

public class SagaStatusResponse extends BaseResponse<SagaStatus> {

    public SagaStatusResponse(String message) {super(message);}

    public SagaStatusResponse(SagaStatus resource) {super(resource);}
}
