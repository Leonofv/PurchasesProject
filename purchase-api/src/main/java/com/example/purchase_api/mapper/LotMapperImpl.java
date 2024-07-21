package com.example.purchase_api.mapper;

import com.example.purchase_api.dto.CreateLotRequest;
import com.example.purchase_api.dto.LotDto;
import com.example.purchase_api.dto.UpdateLotRequest;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Lot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LotMapperImpl implements LotMapper {

    @Override
    public LotDto toLotDto(Lot lot) {
        if (lot == null) {
            return null;
        }
        return new LotDto(
                lot.getId(),
                lot.getLotName(),
                lot.getCustomerCode(),
                lot.getPrice(),
                lot.getCurrencyCode(),
                lot.getNdsRate(),
                lot.getPlaceDelivery(),
                lot.getDateDelivery()
        );
    }

    @Override
    public Lot toLot(CreateLotRequest createLotRequest) {
        if(createLotRequest == null) {
            return null;
        }
        Lot lot = new Lot();
        lot.setLotName(createLotRequest.getLotName());
        lot.setCustomerCode(createLotRequest.getCustomerCode());
        lot.setPrice(createLotRequest.getPrice());
        lot.setCurrencyCode(createLotRequest.getCurrencyCode());
        lot.setNdsRate(createLotRequest.getNdsRate());
        lot.setPlaceDelivery(createLotRequest.getPlaceDelivery());
        lot.setDateDelivery(createLotRequest.getDateDelivery());
        return lot;
    }

    @Override
    public Lot toLot(UpdateLotRequest updateLotRequest) {
        if (updateLotRequest == null) {
            return null;
        }
        Lot lot = new Lot();
        lot.setLotName(updateLotRequest.getLotName());
        lot.setPrice(updateLotRequest.getPrice());
        lot.setCurrencyCode(updateLotRequest.getCurrencyCode());
        lot.setNdsRate(updateLotRequest.getNdsRate());
        lot.setPlaceDelivery(updateLotRequest.getPlaceDelivery());
        lot.setDateDelivery(updateLotRequest.getDateDelivery());
        return lot;
    }
}

