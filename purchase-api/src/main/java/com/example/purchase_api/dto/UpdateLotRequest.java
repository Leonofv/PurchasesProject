package com.example.purchase_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateLotRequest {

    @Schema(description = "Наименование лота", example = "Лот №2")
    @NotNull
    private String lotName;

    @Schema(description = "Цена лота", example = "2000.00")
    @NotNull
    private BigDecimal price;

    @Schema(description = "Код валюты", example = "USD")
    @NotNull
    private String currencyCode;

    @Schema(description = "Ставка НДС", example = "18%")
    private String ndsRate;

    @Schema(description = "Место доставки", example = "г. Воронеж, ул. Мира, д. 1")
    @NotNull
    private String placeDelivery;

    @Schema(description = "Дата доставки", example = "2024-10-31T23:59:59")
    @NotNull
    private LocalDateTime dateDelivery;

}
