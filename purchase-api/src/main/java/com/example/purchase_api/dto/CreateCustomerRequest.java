package com.example.purchase_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateCustomerRequest {

    @Schema(description = "Код контрагента", example = "CUST001")
    @NotBlank
    private String customerCode;

    @Schema(description = "Наименование", example = "ООО Ромашка")
    @NotBlank
    private String customerName;

    @Schema(description = "ИНН", example = "1234567890")
    @NotBlank
    @Size(min = 10, max = 12)
    private String customerInn;

    @Schema(description = "КПП", example = "987654321")
    @NotBlank
    @Size(min = 9, max = 9)
    private String customerKpp;

    @Schema(description = "Юридический адрес", maxLength = 255, example = "г. Москва, ул. Ленина, д. 1")
    @NotBlank
    private String customerLegalAddress;

    @Schema(description = "Почтовый адрес", maxLength = 255, example = "г. Москва, ул. Ленина, д. 2")
    @NotBlank
    private String customerPostalAddress;

    @Schema(description = "Электронная почта", example = "info@example.com")
    @Email
    private String customerEmail;

    @Schema(description = "Вышестоящий контрагент (код)", example = "CUST001")
    private String customerCodeMain;

    @Schema(description = "Признак юридического лица", example = "true")
    @NotNull
    private Boolean isOrganization;

    @Schema(description = "Признак физического лица", example = "false")
    @NotNull
    private Boolean isPerson;
}
