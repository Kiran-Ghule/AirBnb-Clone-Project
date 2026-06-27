package com.airbnb.project.services;


import com.airbnb.project.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createNewRoom(Long hotelId,RoomDTO roomDTO);
    List<RoomDTO> getAllRooms(Long hotelId);
    RoomDTO getRoomById(Long id);
    void deleteRoomById(Long id);

}
