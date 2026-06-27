package com.airbnb.project.services;

import com.airbnb.project.Exception.ResourceNotFoundException;
import com.airbnb.project.dto.RoomDTO;
import com.airbnb.project.entities.Hotel;
import com.airbnb.project.entities.Room;
import com.airbnb.project.repository.HotelRepository;
import com.airbnb.project.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.Converters.Collection.map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;

    @Override
    public RoomDTO createNewRoom(Long hotelId,RoomDTO roomDTO) {
        log.info("Creating room in hotel with hotelId {}",hotelId );
        Hotel hotel = hotelRepository.findById(hotelId)
                        .orElseThrow(
                        ()->new ResourceNotFoundException("Hotel not found with id: "+hotelId)
                    );
        Room room = modelMapper.map(roomDTO,Room.class);
        room.setHotel(hotel);
        room=roomRepository.save(room);

        //Todo : Create Inventory as soon as room is created and if hotel is Active
        return modelMapper.map(room,RoomDTO.class);

    }

    @Override
    public List<RoomDTO> getAllRooms(Long hotelId) {
        log.info("Getting all room in hotel with hotelId {}",hotelId );
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Hotel not found with id: "+hotelId)
                );

        return hotel.getRooms().stream()
                .map(element -> modelMapper.map(element,RoomDTO.class))
                .collect(Collectors.toList());

    }



    @Override
    public RoomDTO getRoomById(Long roomId) {
        log.info("Getting room  with room Id {}",roomId );
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with id: "+roomId));
        return modelMapper.map(room,RoomDTO.class);

    }

    @Override
    public void deleteRoomById(Long id) {
        log.info("Deleting room with room Id {}",id);
        if(roomRepository.existsById(id)){
            roomRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("Room not found with id: "+id);
        }

        //Todo : Delete all feature Inventories

    }
}
