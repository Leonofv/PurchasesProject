package com.example.purchase_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LotDto(Integer id,
                     String lotName,
                     String customerCode,
                     BigDecimal price,
                     String currencyCode,
                     String ndsRate,
                     String placeDelivery,
                     LocalDateTime dateDelivery) {
}
