package com.example.purchase_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCustomerRequest {

    @Schema(description = "Наименование", example = "ООО Березка")
    @NotBlank
    private String customerName;

    @Schema(description = "ИНН", example = "0987654321")
    @NotBlank
    @Size(min = 10, max = 12)
    private String customerInn;

    @Schema(description = "КПП", example = "1357902468")
    @NotBlank
    @Size(min = 9, max = 9)
    private String customerKpp;

    @Schema(description = "Юридический адрес", maxLength = 255, example = "г. Воронеж, ул. Мира, д. 13")
    @NotBlank
    private String customerLegalAddress;

    @Schema(description = "Почтовый адрес", maxLength = 255, example = "г. Воронеж, ул. Мира, д. 1")
    @NotBlank
    private String customerPostalAddress;

    @Schema(description = "Электронная почта", example = "info@example.com")
    @Email
    private String customerEmail;

    @Schema(description = "Вышестоящий контрагент (код)", example = "CUST001")
    private String customerCodeMain;
}
