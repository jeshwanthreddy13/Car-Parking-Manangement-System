package AdminOperationsModel;

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
import java.util.List;
import java.util.Scanner;

import Interfaces.Security;



public class PaymentStatus implements Security {
	
	private static String path = "/Users/jeshwanth/Desktop/Final/eecs3311_proj/CSVs/booking.csv";				
	private static String path2 = "/Users/jeshwanth/Desktop/Final/eecs3311_proj/CSVs/admindatabase.csv";
	private static Scanner x;
	
	
	 
	public boolean authenticate(String userName, String password) {
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
				System.out.println("userName:" + lst.get(3) + ",  "+ "userType:" + lst.get(1) + ", " +  "password:" + lst.get(6));
				
				// check if the list index 4(username) and index 7(password) contains the input
				if (lst.get(3).contains(userName) && lst.get(6).contains(password)) {
					isExists = true;
					String msg = "User exists";
					System.out.println("is exists = " + isExists + " " + msg);
					break;
				} else {
					String msg = "User doesn't exist";
					System.out.println("is exists = " + isExists + " " + msg);
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
	

	public List<String> viewBooking() {
		String line = "";
		String[] val = new String[20];
		List<String> lst = new ArrayList<String>();
		List<String> lst2 = new ArrayList<String>();
		
 		try {
			BufferedReader bfr = new BufferedReader(new FileReader(path));
			while ((line = bfr.readLine()) != null) {
				val = line.split(",");
				String pp = val[0] + "," + val[1] + "," + val[2]+ "," + val[3] + "," + val[4] + "hrs" + ",  " + val[5] + ",  " + "INR"+ val[6] + "," +val[7];
				lst.add(pp);
				System.out.println(lst);
				val = new String[20];
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	
	/*
	The system must verify the customer’s payment of the parking space before changing their payment status
	
	 */
	public void confirmPayment(String name, String email, String parkingspace) throws IOException {
		File fw = new File(path2);							// path to the file
		FileWriter fwt = new FileWriter(fw, true);
		BufferedWriter bfw = new BufferedWriter(fwt);
		PrintWriter pw = new PrintWriter(bfw);				// prints to the file
		
		x = new Scanner(new File(path2));
		x.useDelimiter("[,\n]");
		
		if (!fw.exists()) {
			fw.createNewFile();
			System.out.println("File exists");
		} else {
			System.out.println("File doesn't exist");
		}
		
		pw.printf("%s,%s,%s\n", name, email,parkingspace);		// writes to the file
		pw.flush();		// flushes the data into the csv
		pw.close();		//close file
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("PAYMENT STATUS");
		PaymentStatus py = new PaymentStatus();
		System.out.println(py.viewBooking());
	}

}
