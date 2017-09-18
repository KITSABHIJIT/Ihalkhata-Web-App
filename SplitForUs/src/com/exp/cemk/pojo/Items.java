package com.exp.cemk.pojo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;

public class Items implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashMap<String, String> obMap = new HashMap<String, String>();

	/* Create object of HashMap */

	public void setItems() {

		/* Add value in HashMap */
		try {

			// Open the file t0hat is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream("itemFile.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
				obMap.put(strLine, strLine);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		// obMap.put("BUS FARE","BUS FARE");
		// obMap.put("BREAK FAST","BREAK FAST");
		// obMap.put("DAILY EXPENSE","DAILY EXPENSE");
		// obMap.put("WEEKLY MARKETTING","WEEKLY MARKETTING");
		// obMap.put("INTERNET BILL","INTERNET BILL");
		// obMap.put("CABLE TV BILL","CABLE TV BILL");
		// obMap.put("DINNER","DINNER");
		// obMap.put("DINNER @ SANJOG","DINNER @ SANJOG");
		// obMap.put("DINNER @ NAVRAS","DINNER @ NAVRAS");
		// obMap.put("AUTO FARE","AUTO FARE");
		// obMap.put("TRANSPORTATION","TRANSPORTATION");
		// obMap.put("DINNER@OmSai","DINNER@OmSai");
		// obMap.put("Travel","Travel");
		// obMap.put("Tea","Tea");
		// obMap.put("Movie Ticket","Movie Ticket");
		// obMap.put("McDonalds","McDonalds");
		// obMap.put("KFC","KFC");
		// obMap.put("Ice Cream","Ice Cream");
		// obMap.put("T-Shirt","T-Shirt");
		// obMap.put("Adjustment","Adjustment");
		// obMap.put("Mobile Recharge","Mobile Recharge");
		// obMap.put("Rail Reservation","Rail Reservation");
		// obMap.put("Shopping","Shopping");
		// obMap.put("Hair Cut","Hair Cut");
		// obMap.put("Cigarette","Cigarette");

	}

}