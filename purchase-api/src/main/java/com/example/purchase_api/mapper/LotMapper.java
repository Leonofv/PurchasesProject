package com.example.purchase_api.mapper;

import com.example.purchase_api.dto.CreateLotRequest;
import com.example.purchase_api.dto.LotDto;
import com.example.purchase_api.dto.UpdateLotRequest;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Lot;

public interface LotMapper {

    LotDto toLotDto(Lot lot);

    Lot toLot(CreateLotRequest createLotRequest);

    Lot toLot(UpdateLotRequest updateLorRequest);
}
