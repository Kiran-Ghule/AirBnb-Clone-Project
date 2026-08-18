package com.airbnb.project.stratergies;

import com.airbnb.project.entities.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {

    public BigDecimal calculateDynamicPrice(Inventory inventory) {
        PricingStrategy pricingStrategy = new BasePricingStrategy();

        // Apply additional stratergy
        pricingStrategy = new SurgePriceStrategy(pricingStrategy);
        pricingStrategy = new OccupancyPricingStrategy(pricingStrategy);
        pricingStrategy = new UrgencyPricingStrategy(pricingStrategy);
        pricingStrategy = new HolidayPricingStrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);
    }
}
