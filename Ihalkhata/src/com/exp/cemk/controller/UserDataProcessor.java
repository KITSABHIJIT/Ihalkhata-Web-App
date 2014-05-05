package com.exp.cemk.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.Test;
import domainmodel.Person;

public class UserDataProcessor {
	private static UserDataProcessor _instance = new UserDataProcessor();
	private static final Logger logger = Logger
			.getLogger(UserDataProcessor.class);

	public static UserDataProcessor getInstance() {
		// log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}

	public JSONArray getUserData(int groupId) {
		logger.info("Controller-->getUserData-->Controller-->getSgetSelectedUserDataelectedPaymentGrid");

		JSONArray JSONUserData = new JSONArray();

		JSONUserData = getSelectedUserData(groupId);

		// log.debug("In getMatchingBrokers");
		return JSONUserData;
	}

	public JSONArray getUserGroup(String userId) {
		logger.info("Controller-->getUserData-->Controller-->getSelectedUserGroup");

		JSONArray JSONUserData = new JSONArray();

		JSONUserData = getSelectedUserGroup(userId);

		// log.debug("In getMatchingBrokers");
		return JSONUserData;
	}
	
	public JSONArray getSelectedUserGroup(String userId) {

		logger.info("Controller-->getSelectedUserGroup-->Spring Data Access-->getUserData");
		try {

			JSONArray arrayObj = new JSONArray();
			List<Person> usersList = new ArrayList<Person>();
			Test usersListData = new Test();
			usersList = usersListData.getUserGroup(userId);
			for (int i = 0; i < usersList.size(); i++) {
				JSONObject jsonItems = new JSONObject();
				jsonItems.put("groupName", usersList.get(i).getGroupName());
				jsonItems.put("groupId", usersList.get(i).getGroupId());
				arrayObj.add(jsonItems);
			}
			// logger.debug("Controller-->getSelectedUserData"+arrayObj.toString());
			return arrayObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
			return null;
		} finally {
			// BaseDBUtil.closeDbResources(con, stmt, rs);
		}

	}

	public int setdefaultPassword(String userId, String secAns,
			String password, String flag) {
		logger.info("Controller-->setdefaultPassword-->Spring Data Access-->updateDefaultPassword");
		Test setdefaultPassword = new Test();
		int rowsAffected = setdefaultPassword.updateDefaultPassword(userId,
				secAns, password, flag);
		// logger.debug("No of rows affected:-->"+rowsAffected);
		return rowsAffected;
	}

	public List<Person> isUserPresent(String userName) {
		logger.info("Controller-->isUserPresent-->Spring Data Access-->isUserNamePresent");
		Test isUserPresent = new Test();
		List<Person> user = isUserPresent.isUserNamePresent(userName);
		return user;
	}

	public int updateLoginTime(String time, String userId) {
		logger.info("Controller-->updateLoginTime-->Spring Data Access-->updateLoginTime");
		Test updateLoginTimeInfo = new Test();
		int rowsAffected = updateLoginTimeInfo
				.updateUserLoginTime(time, userId);
		// logger.debug("No of rows affected:-->"+rowsAffected);
		return rowsAffected;
	}

	public int updateSessionId(String sessionId, String userId) {
		logger.info("Controller-->updateSessionId-->Spring Data Access-->updateUserSessionId");
		Test updateLoginTimeInfo = new Test();
		int rowsAffected = updateLoginTimeInfo.updateUserSessionId(sessionId,
				userId);
		// logger.debug("No of rows affected:-->"+rowsAffected);
		return rowsAffected;
	}

	public int insertUser(Person user) {
		logger.info("Controller-->insertUser-->Spring Data Access-->insertUserData");
		Test insertUserData = new Test();
		int rowsAffected = insertUserData.insertUserData(user);
		// logger.debug("No of rows affected:-->"+rowsAffected);
		return rowsAffected;
	}

	public int updateUser(Person user) {
		logger.info("Controller-->updateUser-->Spring Data Access-->updateUserData");
		Test updateUserData = new Test();
		int rowsAffected = updateUserData.updateUserData(user);
		// logger.debug("No of rows affected:-->"+rowsAffected);
		return rowsAffected;
	}

	public List<Person> getLoginUser(String userId, String password) {

		logger.info("Controller-->getLoginUser-->Spring Data Access-->getLoginUserData");
		try {

			List<Person> usersList = new ArrayList<Person>();
			Test loginUserData = new Test();
			usersList = loginUserData.getLoginUserData(userId, password);
			return usersList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
			return null;
		} finally {
			// BaseDBUtil.closeDbResources(con, stmt, rs);
		}

	}

	public File getUserImage(String userId) {

		logger.info("Controller-->getUserImage-->Spring Data Access-->getUserImage");
		try {

			List<Person> usersList = new ArrayList<Person>();
			Test loginUserData = new Test();
			usersList = loginUserData.getUserImage(userId);
			if (!usersList.isEmpty()) {
				return usersList.get(0).getImage();
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public JSONArray getSelectedUserData(int groupId) {

		logger.info("Controller-->getSelectedUserData-->Spring Data Access-->getUserData");
		try {

			JSONArray arrayObj = new JSONArray();
			List<Person> usersList = new ArrayList<Person>();
			Test usersListData = new Test();
			usersList = usersListData.getUserData(groupId);
			for (int i = 0; i < usersList.size(); i++) {
				JSONObject jsonItems = new JSONObject();
				jsonItems.put("userName", usersList.get(i).getUserName());
				jsonItems.put("userId", usersList.get(i).getUserId());
				jsonItems.put("password", usersList.get(i).getPassword());
				jsonItems.put("email", usersList.get(i).getEmail());
				arrayObj.add(jsonItems);
			}
			// logger.debug("Controller-->getSelectedUserData"+arrayObj.toString());
			return arrayObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
			return null;
		} finally {
			// BaseDBUtil.closeDbResources(con, stmt, rs);
		}

	}

}
