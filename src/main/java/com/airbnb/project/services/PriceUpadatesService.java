package com.airbnb.project.services;

import com.airbnb.project.entities.Hotel;
import com.airbnb.project.entities.HotelMinPrice;
import com.airbnb.project.entities.Inventory;
import com.airbnb.project.repository.HotelMinPriceRepo;
import com.airbnb.project.repository.HotelRepository;
import com.airbnb.project.repository.InventoryRepository;
import com.airbnb.project.stratergies.PricingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceUpadatesService {

    // Schedular to update the inventory and hotelMinPrice tables every hour

    private final HotelRepository hotelRepository;
    private final InventoryRepository inventoryRepository;
    private final HotelMinPriceRepo hotelMinPriceRepo;
    private final PricingService pricingService;


    public void priceUpdates()
    {
        int page=0;
        int batchSize=100;

        while(true)
        {

            Page<Hotel> hotelPage= hotelRepository.findAll(PageRequest.of(page,batchSize));
            if(hotelPage.isEmpty())
                break;

            hotelPage.getContent().forEach(this::updateHotelPrices);

            page++;
        }

    }

    private void updateHotelPrices(Hotel hotel){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusYears(1);

        List<Inventory> inventoryList = inventoryRepository.findByHotelAndDateBetween(hotel,startDate,endDate);

        updateInventoryPrices(inventoryList);

        updateHotelMinPrice(hotel, inventoryList, startDate, endDate);

    }

    private void updateHotelMinPrice(Hotel hotel, List<Inventory> inventoryList, LocalDate startDate, LocalDate endDate) {
        //Compute min price per day for the hotel
        Map<LocalDate,BigDecimal> dailyMinPrices = inventoryList.stream()
                .collect(Collectors.groupingBy(
                        Inventory::getDate,
                        Collectors.mapping(Inventory::getPrice, Collectors.minBy(Comparator.naturalOrder()))
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e-> e.getValue().orElse(BigDecimal.ZERO)));

        //Prepare  Hotelprice entities in bulk

        List<HotelMinPrice> hotelMinPrices = new ArrayList<>();
        dailyMinPrices.forEach((date,price)->{
            HotelMinPrice
        })


    }

    private void updateInventoryPrices(List<Inventory> inventory){

        inventory.forEach(inventoryItem -> {
            BigDecimal dynamicPrice = pricingService.calculateDynamicPrice(inventoryItem);
            inventoryItem.setPrice(dynamicPrice);
        });

         inventoryRepository.saveAll(inventory);

    }

}
