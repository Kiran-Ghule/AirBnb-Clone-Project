package com.airbnb.project.controller;

import com.airbnb.project.dto.BookingDTO;
import com.airbnb.project.dto.BookingRequest;
import com.airbnb.project.dto.GuestDTO;
import com.airbnb.project.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDTO> InitializeBooking9(@RequestBody BookingRequest bookingRequest)
    {
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDTO> addGuests(@PathVariable Long bookingId,@RequestBody List<GuestDTO> guestDTOList)
    {
        return ResponseEntity.ok(bookingService.addGuests( bookingId,guestDTOList));
    }

}
