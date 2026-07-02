package com.airbnb.project.services;

import com.airbnb.project.dto.HotelDTO;
import com.airbnb.project.dto.HotelInfoDTO;

public interface HotelService {
    HotelDTO createNewHotel(HotelDTO hotelDTO);
    HotelDTO getHotelById(Long id);
    HotelDTO updateHotelById(Long id,HotelDTO hotelDTO);
    void deleteHotelById(Long id);
    void activateHotelById(Long id);
    HotelInfoDTO getHotelInfoById(Long id);


}