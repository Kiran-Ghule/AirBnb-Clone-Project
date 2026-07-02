package com.airbnb.project.repository;

import com.airbnb.project.dto.HotelDTO;
import com.airbnb.project.entities.Hotel;
import com.airbnb.project.entities.Inventory;
import com.airbnb.project.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByRoom( Room room);

    @Query("""
    SELECT distinct i.hotel
    FROM Inventory i
    WHERE i.city = :city
      AND i.date BETWEEN :startDate AND :endDate
      AND i.closed = false
      AND (i.totalCount - i.bookCount) >= :roomsCount
    GROUP BY i.hotel, i.room
    HAVING COUNT(i.date) = :dateCount
""")
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount,
            Pageable pageble
    );
}
