package com.exp.cemk.servlets;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import test.SpringDataAccessConstants;

import com.exp.cemk.util.CommonUtil;
import com.exp.cemk.util.TimerClass;

/**
 * Servlet implementation class GenerateBackupServlet
 */
public class GenerateBackupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(GenerateBackupServlet.class);

	public GenerateBackupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		String username = CommonUtil
				.getFromEnvironment(SpringDataAccessConstants.DBUSERNAME_CONTEXT);
		String password = CommonUtil
				.getFromEnvironment(SpringDataAccessConstants.DBPASSWD_CONTEXT);
		String databaseName = CommonUtil
				.getFromEnvironment(SpringDataAccessConstants.DBNAME_CONTEXT);

		String path = System.getProperty("user.home") + File.separator
				+ "Ihalkhata Data Backup";
		String logPath = System.getProperty("user.home") + File.separator
				+ "Ihalkhata Logs";
		logger.info("Ihalkhata Data Backup Output path: " + path);
		logger.info("Ihalkhata Logs output path: " + path);
		File backup = new File(path);
		if (!backup.exists())
			backup.mkdirs();
		File logFile = new File(logPath);
		if (!logFile.exists())
			logFile.mkdirs();
		boolean deleteStatus = CommonUtil.deleteAllFiles(path, ".sql");
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)
				&& !StringUtils.isEmpty(databaseName)
				&& !StringUtils.isEmpty(path) && deleteStatus) {
			TimerClass.backupDB(username, password, databaseName, backup);
			TimerClass.scheduledBackup(9, 0);
		} else {
			logger.info("+--------------------------------------+");
			logger.info("|   Context Reading Failed             |");
			logger.info("+--------------------------------------+");
		}
	}

}
