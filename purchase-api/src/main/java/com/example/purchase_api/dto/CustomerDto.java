package com.example.purchase_api.dto;

public record CustomerDto(Integer id,
                          String customerCode,
                          String customerName,
                          String customerInn,
                          String customerKpp,
                          String customerLegalAddress,
                          String customerPostalAddress,
                          String customerEmail,
                          String customerCodeMain,
                          Boolean isOrganization,
                          Boolean isPerson) {
}
