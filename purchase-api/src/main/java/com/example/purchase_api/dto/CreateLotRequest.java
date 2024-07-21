package com.example.purchase_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateLotRequest {

    @Schema(description = "Наименование лота", example = "Лот №1")
    @NotNull
    private String lotName;

    @Schema(description = "Код контрагента", example = "CUST001")
    @NotNull
    private String customerCode;

    @Schema(description = "Цена лота", example = "1000.00")
    @NotNull
    private BigDecimal price;

    @Schema(description = "Код валюты", example = "RUB")
    @NotNull
    private String currencyCode;

    @Schema(description = "Ставка НДС", example = "20%")
    private String ndsRate;

    @Schema(description = "Место доставки", example = "г. Москва, ул. Ленина, д. 1")
    @NotNull
    private String placeDelivery;

    @Schema(description = "Дата доставки", example = "2023-12-31T23:59:59")
    @NotNull
    private LocalDateTime dateDelivery;
}
