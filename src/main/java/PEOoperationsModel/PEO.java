package PEOoperationsModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 Managing parking Space
 
 */
public class PEO {
	
	private static String path = "/Users/jeshwanth/Desktop/Final/eecs3311_proj/CSVs/bookingpeo.csv";				
	private static List<String> parkingspot = Arrays.asList(new String[1500]);
	private final int spotPerLocation = 50;
	private static Scanner x;
			
	
 	/* 4.8.3-REQ-4
	 * add parking spot to the bookingpeo.csv
	
	 */
	public void addSpaces(String spotName) throws IOException {
		String delhi = ""; String yorkdale = ""; String square1 = ""; String yorku = ""; String stGeorge = "";
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
		pw.printf("%s\n", spotName);		// writes to the file
		pw.flush();							// flushes the data into the csv
		pw.close();							//close file
	}

	
	/* 
	 * Remove parking spot from bookingpeo.csv
	 */
	public void removeSpaces(String spotName) {
		String tmpFile = "tmp.csv";
		String target = "";
		File oldFile = new File(path);
		File newFile = new File(tmpFile);
		
		System.out.println(spotName);
		try {
			FileWriter fw = new FileWriter(tmpFile, true);
			BufferedWriter bfw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bfw);
			x = new Scanner(new File(path));
			x.useDelimiter("[,\n]");
			
			while (x.hasNext()) {
				target = x.next();
				if (!target.equals(spotName)) {
					pw.println(target);
				}
			}
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dmp = new File(path);
			newFile.renameTo(dmp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public boolean checkParkingSpace(String spotName) {
		String line = "";
		String[] val = null;
		boolean isExists = false;
		
 		try {
			BufferedReader bfr = new BufferedReader(new FileReader(path));
			while ((line = bfr.readLine())!= null) {
				val = line.split(",");
				
				// convert the array into list and put the val into the arraylists
				List<String> lst = Arrays.asList(val);
				System.out.println(lst);
				System.out.println("spotName:" + lst.get(0));
				
				if (lst.get(0).contains(spotName)) {
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
	 * Gives count of the available spot
	
	 */
	public int countParkingSpace() throws IOException {
		File file = new File(path);
		
		int cnt = 0;
		if (file.exists()) {
			FileReader fr = new FileReader(file);
			LineNumberReader ln = new LineNumberReader(fr);
			
			while (ln.readLine() != null) {
				cnt++;
			}
			System.out.println("Line number = " + cnt);
			ln.close();
		}
		return spotPerLocation - cnt;
	}
	
	/*
	 * views the booking by the PEO
	 */
	public List<String> viewBooking(String userName) {
		String line = "";
		String[] val = null;
		List<String> lst = new ArrayList<String>();
		
		try {
			BufferedReader bfr = new BufferedReader(new FileReader(path));
			while ((line = bfr.readLine()) != null) {
				val = line.split(",");
				String pp = val[0];
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
	
	
	
	public static void main(String[] args) throws IOException {
		System.out.println("PEO");

		PEO peo = new PEO();
		
		System.out.println(peo.viewBooking("adrian11"));
	}
}
