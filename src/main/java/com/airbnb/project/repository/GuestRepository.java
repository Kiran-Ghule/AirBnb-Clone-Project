package com.airbnb.project.repository;

import com.airbnb.project.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {
}
