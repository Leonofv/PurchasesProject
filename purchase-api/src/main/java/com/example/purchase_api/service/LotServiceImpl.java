package com.example.purchase_api.service;

import com.example.purchase_api.jooq.sample.model.Tables;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Lot;
import com.example.purchase_api.jooq.sample.model.tables.records.LotRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

import static com.example.purchase_api.jooq.sample.model.Tables.LOT;

@RequiredArgsConstructor
@Service
public class LotServiceImpl implements LotService {

    private final DSLContext dslContext;

    @Override
    public List<Lot> getAllLots() {
        return dslContext.selectFrom(LOT).fetchInto(Lot.class);
    }

    @Override
    public Lot getLotById(Long id) {
        return dslContext.selectFrom(LOT)
                .where(LOT.ID.eq(Math.toIntExact(id)))
                .fetchOneInto(Lot.class);
    }

    @Override
    public List<Lot> getLotsByName(String lotName) {
        return dslContext.selectFrom(LOT)
                .where(LOT.LOT_NAME.likeIgnoreCase("%" + lotName + "%"))
                .fetchInto(Lot.class);
    }

    @Override
    public Lot createLot(Lot lot) {
        dslContext.insertInto(LOT)
                .set(dslContext.newRecord(LOT, lot))
                .execute();
        return lot;
    }

    @Override
    public Lot updateLot(Long lotId, Lot lot) {
        LotRecord lotRecord = dslContext.selectFrom(LOT)
                .where(LOT.ID.eq(Math.toIntExact(lotId)))
                .fetchOne();

        if (lotRecord == null) {
            throw new IllegalArgumentException("Lot not found");
        }

        updateField(lot.getLotName(), lotRecord::setLotName, false);
        updateField(lot.getCustomerCode(), lotRecord::setCustomerCode, false);
        updateField(lot.getPrice(), lotRecord::setPrice, false);
        updateField(lot.getCurrencyCode(), lotRecord::setCurrencyCode, false);
        updateField(lot.getNdsRate(), lotRecord::setNdsRate, true);
        updateField(lot.getPlaceDelivery(), lotRecord::setPlaceDelivery, false);
        updateField(lot.getDateDelivery(), lotRecord::setDateDelivery, false);
        lotRecord.update();
        return lotRecord.into(Lot.class);
    }

    private <T> void updateField(T value, Consumer<T> setter, boolean allowNull) {
        if (allowNull || value != null) {
            setter.accept(value);
        }
    }

    @Override
    public void deleteLot(Long lotId) {
        dslContext.deleteFrom(LOT)
                .where(LOT.ID.eq(Math.toIntExact(lotId)))
                .execute();
    }
}
