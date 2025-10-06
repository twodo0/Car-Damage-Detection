package com.twodo0.capstoneWeb.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.twodo0.capstoneWeb.domain.enums.DamageType;

import java.util.List;

// FastAPI가 PredictionApp에 내려주는 응답 DTO
@JsonIgnoreProperties(ignoreUnknown = true)
public record FastApiPredictRes(
        String model,

        ImageInfo imageInfo, // 프로튼에서 원본 위에 박스를 그릴 때 기준이 필요함.

        @JsonProperty("threshold_used")
        Double thresholdUsed,

        @JsonProperty("boxes")
        @JsonSetter(nulls = Nulls.AS_EMPTY)
        List<BoxDto> boxes

){

    // FastAPI 응답 바인딩 전용(전송/어댑터 계층 DTO)
    public record BoxDto(
            @JsonProperty("class_probs")
            @JsonSetter(nulls = Nulls.AS_EMPTY)
            List<ApiClassProbDto> classProbs,
            double x, double y, double w, double h
    ) {};
    public record ApiClassProbDto(
            @JsonProperty("label")
            DamageType damageType,

            @JsonProperty("prob")
            double prob
    ){}

    public record ImageInfo(
            int width,
            int height
    ){};
}

