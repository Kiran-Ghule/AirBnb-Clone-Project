package com.airbnb.project.services;

import com.airbnb.project.Exception.ResourceNotFoundException;
import com.airbnb.project.dto.HotelDTO;
import com.airbnb.project.entities.Hotel;
import com.airbnb.project.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private  final ModelMapper modelMapper;
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
    public Boolean deleteHotelById(Long id) {
        if(!hotelRepository.existsById(id))
            {
            throw new ResourceNotFoundException("Hotel not found with id : " + id);
            }

        hotelRepository.deleteById(id);
        //TODO : delete the future invetories for this hotel
        return true;

    }

    @Override
    public void activateHotelById(Long id) {
        Hotel hotel = getById(id);
        hotel.setActive(true);

        //Todo : Create Inventory for all room for this hotel

    }


}
