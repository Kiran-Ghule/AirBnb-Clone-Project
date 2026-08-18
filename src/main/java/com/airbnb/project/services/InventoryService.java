package com.airbnb.project.services;

import com.airbnb.project.dto.HotelDTO;
import com.airbnb.project.dto.HotelPriceDTO;
import com.airbnb.project.dto.HotelSearchRequest;
import com.airbnb.project.entities.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForYear(Room room);
    void deleteFutureInventory(Room room);
    Page<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest);

}
