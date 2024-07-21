package com.example.purchase_api.controller;

import com.example.purchase_api.dto.*;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Lot;
import com.example.purchase_api.mapper.LotMapper;
import com.example.purchase_api.service.LotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lots")
public class LotController {

    private final LotService lotService;
    private final LotMapper lotMapper;

    @GetMapping("/all")
    public List<LotDto> getAllLots() {
        return lotService.getAllLots().stream()
                .map(lotMapper::toLotDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public LotDto getLotById (@PathVariable Long id) {
        return lotMapper.toLotDto(lotService.getLotById(id));
    }

    @GetMapping("/lotsName/{lotName}")
    public List<LotDto> getLotsByName(@PathVariable String lotName) {
        List<Lot> lots = lotService.getLotsByName(lotName);
        return lots.stream()
                .map(lotMapper::toLotDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public LotDto createLot(@Valid @RequestBody CreateLotRequest createLotRequest) {
        Lot lot = lotMapper.toLot(createLotRequest);
        return lotMapper.toLotDto(lotService.createLot(lot));
    }

    @PutMapping("/update/{lotId}")
    public LotDto updateLot(@PathVariable Long lotId, @Valid @RequestBody UpdateLotRequest updateLotRequest) {
        Lot lot = lotMapper.toLot(updateLotRequest);
        return lotMapper.toLotDto(lotService.updateLot(lotId, lot));
    }

    @DeleteMapping("/delete/{lotId}")
    public void deleteLot(@PathVariable Long lotId) {
        lotService.deleteLot(lotId);
    }
}

