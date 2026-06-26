package com.airbnb.project.services;

import com.airbnb.project.dto.HotelDTO;

public interface HotelService {
    HotelDTO createNewHotel(HotelDTO hotelDTO);
    HotelDTO getHotelById(Long id);
    HotelDTO updateHotelById(Long id,HotelDTO hotelDTO);
    Boolean deleteHotelById(Long id);
}
