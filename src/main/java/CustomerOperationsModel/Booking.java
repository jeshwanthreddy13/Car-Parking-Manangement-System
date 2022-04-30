
package CustomerOperationsModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import Interfaces.CSVOps;

/*
 * Booking a parking space
 * 		
 */
public class Booking implements CSVOps {
	
	private static String path = "/Users/jeshwanth/Desktop/Final/eecs3311_proj/CSVs/booking.csv";	

	/* 
	 * READS THHROUGH BOOKING.CSV INDEX 5 AND SEES IF THE ENTERED SPOT IS THERE OR NOT
	 */
	public boolean checkParkingSpace(String spotName) {
		String line = "";
		String[] val = null;
		boolean isExists = false;
		
 		try {
			BufferedReader bfr = new BufferedReader(new FileReader(path));
			while ((line = bfr.readLine())!= null) {
				val = line.split(",");
//				System.out.println("userName:" + val[3] + ",  "+ "userType:" + val[1] + ", " +  "password:" + val[6]);
				
				// convert the array into list and put the val into the arraylists
				List<String> lst = Arrays.asList(val);
				System.out.println(lst);
				System.out.println("spotName:" + lst.get(5));
				
				// check if the list contains index 4(spotName) and index 7(password) contains the input
				if (lst.get(5).contains(spotName)) {
					isExists = true;
					String msg = "Spot is occupied";
					System.out.println("is exists = " + isExists + "; " + msg);
					break;
				} else {
					String msg = "Spot is free";
					System.out.println("is exists = " + isExists + "; " + msg);
					isExists = false;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isExists;
	}
	
	/*
	 *The system allows a customer to book up to three parking spaces  [in a given location;ie.3 space in york at a time]
	 *  The system must display an error message if the parking space the customer booked is occupied in the system
	 * BOOKING.CSV schema -> USERNAME,BOOKINGID,DATE,TIME OF BOOKING,DURATION,BOOKING SPOT,PRICE,PAYMENT STATUS, LICENSE NUMBER, EXPIRY TIME
	 */
	public void bookparkingSpace(String userName, String bookingID, String date, String time, 
			String duration,String spotName, String price, String paymentStat, String licenceNum) throws IOException {
		File fw = new File(path);							// path to the file
		FileWriter fwt = new FileWriter(fw, true);
		BufferedWriter bfw = new BufferedWriter(fwt);
		PrintWriter pw = new PrintWriter(bfw);				// prints to the file
		
		if (!fw.exists()) {
			fw.createNewFile();
			System.out.println("File exists");
		} else {
			System.out.println("File doesn't exist");
		}
		
		pw.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", userName,bookingID,date,time,duration,spotName,price,paymentStat,licenceNum);		// writes to the file
		pw.flush();							// flushes the data into the csv
		pw.close();							//close file
		
	}
	
	 // 4.4.3-REQ-5:
	public int userCount(String userName) {
		String line = "";
		String[] val = null;
		List<String> lst = new ArrayList<String>();
		int count = 0;
		
 		try {
			BufferedReader bfr = new BufferedReader(new FileReader(path));
			while ((line = bfr.readLine())!= null) {
				val = line.split(",");
				
				if (val[0].contains(userName)) {
					String pp = val[0];
					lst.add(pp);
					count = Collections.frequency(lst, userName);
					System.out.println("usercount: " + lst);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 		System.out.println("count: " + count);
		return count;
	}
	
	/* 4.4.3-REQ-6  */
	public String bookingID() {
		int len = 4;
		String chr = "abcdefghijk0123456789";
		Random rn = new Random();
		StringBuilder str = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			str.append(chr.charAt(rn.nextInt(chr.length())));
		}
		return str.toString();
	}

	public void writeUser(String name, String userType, String email, String userName, String phoneNumber,
			String address, String password) throws IOException {
		// TODO Auto-generated method stub	
	}

	public void removeUser(String name, String userType, String email, String userName, String phoneNumber,
			String address, String password) {
		// TODO Auto-generated method stub	
	}

	public void bookparkingSpace(String userName, String bookingID, String date, String time, String duration,
			String spotName, String price, String paymentStat, String licenceNum, String paymentType) {
		// TODO Auto-generated method stub	
	}

	public void cancelBooking(String userName, String bookingID, String date, String time, String duration,
			String spotName, String price, String paymentStat, String licenceNum) {
		// TODO Auto-generated method stub	
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Booking Backend");
		
		Booking bk = new Booking();
		String spotName= "ec3";
		bk.checkParkingSpace(spotName);
		
		System.out.println(bk.bookingID());
		
		String user = "jesh";
		bk.userCount(user);	
	}
}