package com.ibrezhneva.dao;

import com.ibrezhneva.domain.Room;

public interface RoomDao {

    void save(Room room);

    Room findById(Long id);

	void flush();
}
