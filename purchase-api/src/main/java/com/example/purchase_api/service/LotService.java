package com.example.purchase_api.service;

import com.example.purchase_api.jooq.sample.model.tables.pojos.Lot;

import java.util.List;

public interface LotService {

    List<Lot> getAllLots();

    Lot getLotById(Long id);

    List<Lot> getLotsByName(String lotName);

    Lot createLot(Lot lot);

    Lot updateLot(Long lotId, Lot lot);

    void deleteLot(Long lotId);
}
