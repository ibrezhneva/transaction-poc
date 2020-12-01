package com.ibrezhneva.service;

import com.ibrezhneva.domain.Room;

public interface RoomService {
    Room getRoomById(long id);

    void createRoom(long id, String name);

    void createRoomWithException(long id, String name);
}
