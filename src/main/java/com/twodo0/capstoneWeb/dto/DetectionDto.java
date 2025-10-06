package com.twodo0.capstoneWeb.dto;

import java.util.List;

// 외부 API 응답 DTO
public record DetectionDto(List<ClassProbDto> classProbDto, int x, int y, int w, int h) {}