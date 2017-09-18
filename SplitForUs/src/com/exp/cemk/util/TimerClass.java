package com.exp.cemk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import test.SpringDataAccessConstants;

public class TimerClass {
	private static final Logger logger = Logger.getLogger(TimerClass.class);
	public static void scheduledBackup(int hour, int min) {
		Timer timer = new Timer();
		long milliseconds = DateUtil.getTimeDiffFromCurrentTime(hour, min);
		 long delay = 24 * 60 * 60 * 1000;
		 logger.info("Timer will start after: "+DateUtil.getHourMinSecond(milliseconds));
		//long delay = 1000;
		timer.schedule(new TimerTask() {
			public void run() {
				logger.info("+--------------------------------------+");
				logger.info("|    MYSQL Data Scheduler Started      |");
				logger.info("+--------------------------------------+");	
				
				String username = CommonUtil.getFromEnvironment(SpringDataAccessConstants.DBUSERNAME_CONTEXT);
				String password = CommonUtil.getFromEnvironment(SpringDataAccessConstants.DBPASSWD_CONTEXT);
				String databaseName =CommonUtil.getFromEnvironment(SpringDataAccessConstants.DBNAME_CONTEXT);
				
				String path=System.getProperty("user.home")+File.separator+"Ihalkhata Data Backup";
				logger.info("Backup Output path: " + path);
				File backup =new File(path);
				if(!backup.exists())
					backup.mkdirs();
				if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) && !StringUtils.isEmpty(databaseName) && !StringUtils.isEmpty(path)){
					if(TimerClass.backupDB(username,password,databaseName,backup)){
						logger.info("+--------------------------------------+");
						logger.info("|    MYSQL Data Scheduler Job ended    |");
						logger.info("+--------------------------------------+");	
					}
				}else{
					logger.info("+--------------------------------------+");
					logger.info("|   Context Reading Failed             |");
					logger.info("+--------------------------------------+");	
				}
				backupDB(username,password,databaseName,backup);
			}
		}, milliseconds, delay);

	}
	public static boolean backupDB(String dbUserName, String dbPassword, String dbName, File path) {
		String outputFileName=dbName+"_"+DateUtil.getFormatedCurrentDate("dd-MM-yyyy-HH-mm-ss")+".sql";
        String executeCmd = "mysqldump --user="+dbUserName+" --password="+dbPassword+" "+dbName+" -r "+outputFileName;
        //logger.info("executeCmd: "+executeCmd);
        Process runtimeProcess;
        try {
        	String line;
            runtimeProcess =Runtime.getRuntime().exec(executeCmd,null, path);
            BufferedReader input =new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
            BufferedReader error =new BufferedReader(new InputStreamReader(runtimeProcess.getErrorStream()));
           
            if(input.readLine()!= null)
            	logger.info("OUTPUT");
            while ((line = input.readLine()) != null)
              logger.info(line);
            input.close();

           if(error.readLine()!= null)
        	   logger.info("ERROR");
            while ((line = error.readLine()) != null)
              logger.info(line);
            error.close();
            
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
            	logger.info("+--------------------------------------+");
				logger.info("|   Backup created successfully        |");
				logger.info("+--------------------------------------+");
				logger.info("+----------------------------------------------------+");
				logger.info("|   Backup File: "+outputFileName+"       |");
				logger.info("+----------------------------------------------------+");
				return true;
            } else {
            	logger.info("+--------------------------------------+");
				logger.info("|   Could not create the backup        |");
				logger.info("+--------------------------------------+");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
