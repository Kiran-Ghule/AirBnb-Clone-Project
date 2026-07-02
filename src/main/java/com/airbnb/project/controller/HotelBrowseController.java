package com.airbnb.project.controller;

import com.airbnb.project.dto.HotelDTO;
import com.airbnb.project.dto.HotelInfoDTO;
import com.airbnb.project.dto.HotelSearchRequest;
import com.airbnb.project.services.HotelService;
import com.airbnb.project.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;


    @GetMapping("/search")
    public ResponseEntity<Page<HotelDTO>> searchHotel(@RequestBody HotelSearchRequest hotelSearchRequest){

        Page<HotelDTO> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/Info")
    public ResponseEntity<HotelInfoDTO> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }

}
