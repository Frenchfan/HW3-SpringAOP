package com.sumkin.hw3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @Schema(description = "UserId")
    private Long userId;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Статус")
    @Enumerated(EnumType.STRING)
    private Status status;
}
