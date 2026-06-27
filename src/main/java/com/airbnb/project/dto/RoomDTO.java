package com.airbnb.project.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private Long id;
    private String type;
    private BigDecimal basePrice;
    private String[] photos;
    private String[] amentities;
    private Integer totalCount;
    private Integer capacity;
}
