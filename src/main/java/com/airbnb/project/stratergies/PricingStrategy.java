package com.airbnb.project.stratergies;

import com.airbnb.project.entities.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
