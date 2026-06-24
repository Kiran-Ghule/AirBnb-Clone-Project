package com.airbnb.project.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class HotelContactInfo {
    private String address;
    private String phone;
    private String email;
    private String location;
}
