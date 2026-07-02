package com.airbnb.project.services;

import com.airbnb.project.dto.BookingDTO;
import com.airbnb.project.dto.BookingRequest;
import com.airbnb.project.dto.GuestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookingService {
    BookingDTO initialiseBooking(BookingRequest bookingRequest);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guestDTOList);
}
