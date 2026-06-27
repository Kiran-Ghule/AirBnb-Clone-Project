package com.airbnb.project.services;

import com.airbnb.project.entities.Room;

public interface InventoryService {

    void initializeRoomForYear(Room room);
    void deleteFutureInventory(Room room);


}
