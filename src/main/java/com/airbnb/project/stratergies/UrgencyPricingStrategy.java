package com.airbnb.project.stratergies;

import com.airbnb.project.entities.Inventory;
import com.airbnb.project.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrgencyPricingStrategy implements  PricingStrategy
{
    private final PricingStrategy wrapped;

    public BigDecimal calculatePrice(Inventory inventory)
    {
        BigDecimal price = wrapped.calculatePrice(inventory);
        LocalDate today = LocalDate.now();
        if(!inventory.getDate().isBefore(today) && inventory.getDate().isBefore(today.plusDays(7)))
            price = price.multiply(BigDecimal.valueOf(1.5));
        return price;
    }
}
