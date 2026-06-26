package com.airbnb.project.dto;

import com.airbnb.project.entities.HotelContactInfo;
import com.airbnb.project.entities.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amentities;
    private Boolean active;
    private HotelContactInfo contactInfo;

}
