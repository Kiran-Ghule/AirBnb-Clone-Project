package com.airbnb.project.services;

import com.airbnb.project.Exception.ResourceNotFoundException;
import com.airbnb.project.dto.HotelDTO;
import com.airbnb.project.entities.Hotel;
import com.airbnb.project.entities.Room;
import com.airbnb.project.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.ResourceAccessException;

import java.lang.reflect.Field;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private  final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomService roomService;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDTO) {
        log.info("Creating a new Hotel with Name : {}", hotelDTO.getName());
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        hotel.setActive(false);
        hotel=hotelRepository.save(hotel);
        log.info("Hotel created with Id : {}", hotel.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    private Hotel getById(Long id)
    {
        return hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id : " + id)
                );
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting Hotel with Id : {}", id);
        Hotel hotel = getById(id);
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO updateHotelById(Long id, HotelDTO hotelDTO) {
        log.info("Updating Hotel with Id : {}", id);
        Hotel hotel = getById(id);
        modelMapper.map(hotelDTO, hotel);
        hotel.setId(id);
        hotel=hotelRepository.save(hotel);
        log.info("Hotel updated with Id : {}", hotel.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel = getById(id);

        for(Room room : hotel.getRooms())
            roomService.deleteRoomById(room.getId());

        hotelRepository.deleteById(id);
    }

    @Override
    public void activateHotelById(Long id) {
        Hotel hotel = getById(id);
        hotel.setActive(true);
        hotel=hotelRepository.save(hotel);

        for(Room room : hotel.getRooms())
            inventoryService.initializeRoomForYear(room);



    }


}
