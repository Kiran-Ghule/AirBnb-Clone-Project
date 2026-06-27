package com.airbnb.project.controller;

import com.airbnb.project.dto.RoomDTO;
import com.airbnb.project.entities.Room;
import com.airbnb.project.services.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@PathVariable Long hotelId, @RequestBody RoomDTO roomDTO){
        RoomDTO roomDTO1= roomService.createNewRoom(hotelId, roomDTO);
        return new  ResponseEntity<>(roomDTO1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms(@PathVariable Long hotelId){
        return ResponseEntity.ok(roomService.getAllRooms(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoom( @PathVariable Long roomId){
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId){
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }

}
