package com.ibrezhneva.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibrezhneva.dao.RoomDao;
import com.ibrezhneva.domain.Room;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultRoomService implements RoomService {

	private final RoomDao roomDao;
	private final SettingService settingService;

	@Override
	@Transactional(transactionManager = "transactionManager", readOnly = true)
	public Room getRoomById(long id) {
		return roomDao.findById(id);
	}

	@Override
	@Transactional(transactionManager = "transactionManager")
	public void createRoom(long id, String name) {
		roomDao.save(new Room(id, name));
	}

	@Override
	@Transactional(transactionManager = "transactionManager")
	public void createRoomWithException(long id, String name) {
		roomDao.save(new Room(id, name));
		roomDao.flush();
		settingService.getPropertyValue("setting1");
		throw new RuntimeException("Rollback should be here");
	}
}
