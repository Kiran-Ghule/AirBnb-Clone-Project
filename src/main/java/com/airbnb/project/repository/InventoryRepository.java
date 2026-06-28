package com.airbnb.project.repository;

import com.airbnb.project.entities.Inventory;
import com.airbnb.project.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByRoom( Room room);
}
