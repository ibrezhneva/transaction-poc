package com.ibrezhneva.dao;


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JdbcSettingDao implements SettingDao {
	public static final String SELECT_VALUE_FROM_SETTING_BY_NAME = "select value from setting where name = ?";

	private final JdbcTemplate jdbcTemplate;

	@Override
	public String findByName(String name) {
		return jdbcTemplate.queryForObject(SELECT_VALUE_FROM_SETTING_BY_NAME, List.of(name).toArray(), String.class);
	}
}
