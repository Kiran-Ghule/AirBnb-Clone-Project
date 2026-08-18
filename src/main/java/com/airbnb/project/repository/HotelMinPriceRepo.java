package com.airbnb.project.repository;

import com.airbnb.project.dto.HotelPriceDTO;
import com.airbnb.project.entities.Hotel;
import com.airbnb.project.entities.HotelMinPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HotelMinPriceRepo extends JpaRepository<HotelMinPrice, Long> {
    @Query("""
    SELECT  new com.airbnb.project.dto.HotelPriceDTO(i.hotel,avg(i.price))
    FROM HotelMinPrice i
    WHERE i.hotel.city = :city
      AND i.date BETWEEN :startDate AND :endDate
      AND i.hotel.active = true
    GROUP BY i.hotel
""")
    Page<HotelPriceDTO> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount,
            Pageable pageble
    );
}
