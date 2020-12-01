package com.ibrezhneva.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibrezhneva.dao.SettingDao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultSettingService implements SettingService {

	private final SettingDao settingDao;

	@Override
	@Transactional(transactionManager = "jdbcTransactionManager", readOnly = true)
	public String getPropertyValue(String name) {
		return settingDao.findByName(name);
	}
}
