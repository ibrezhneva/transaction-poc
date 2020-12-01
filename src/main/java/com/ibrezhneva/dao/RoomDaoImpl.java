package com.ibrezhneva.dao;

import org.springframework.stereotype.Repository;

import com.ibrezhneva.domain.Room;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository
@RequiredArgsConstructor
public class RoomDaoImpl implements RoomDao {

	private final SessionFactory sessionFactory;

	@Override
	public void save(Room room) {
		currentSession().save(room);
	}

	@Override
	public Room findById(Long id) {
		return currentSession().get(Room.class, id);
	}

	@Override
	public void flush() {
		currentSession().flush();
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
}
