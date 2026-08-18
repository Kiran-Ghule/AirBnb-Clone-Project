package com.airbnb.project.stratergies;

import com.airbnb.project.entities.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy{
    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price =wrapped.calculatePrice(inventory);
        Boolean istodayHoliday = true; // todo call Api or check with local
        if(istodayHoliday)
            price=price.multiply(BigDecimal.valueOf(1.25));
        return price;
    }
}
