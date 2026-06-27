package com.airbnb.project.controller;

import com.airbnb.project.dto.HotelDTO;
import com.airbnb.project.services.HotelService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private  final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        log.info("Attempting to create hotel with Name : {}", hotelDTO.getName());
        HotelDTO hotelDTO1 = hotelService.createNewHotel(hotelDTO);
        return new ResponseEntity<>(hotelDTO1, HttpStatus.ACCEPTED);

    }

    @GetMapping(path="/{hotelId}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long hotelId) {
        HotelDTO hotelDTO = hotelService.getHotelById(hotelId);
        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    @PutMapping(path="/{hotelId}")
    public ResponseEntity<HotelDTO> deleteHotelById(@PathVariable Long hotelId, @RequestBody HotelDTO hotelDTO) {
        HotelDTO hotelDTO1 = hotelService.updateHotelById(hotelId, hotelDTO);
        return new ResponseEntity<>(hotelDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId) {
        hotelService.deleteHotelById(hotelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path="/{hotelId}")
    public ResponseEntity<Void> activateHotelById(@PathVariable Long hotelId) {
        hotelService.activateHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

}
