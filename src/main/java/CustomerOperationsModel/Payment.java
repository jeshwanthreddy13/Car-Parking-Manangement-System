package CustomerOperationsModel;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
  calulates the payment/price of the parking 
 * 		
 */
public class Payment {
	
	
	public double price(double duration) {
		double prc = 0.0;
		if (duration < 1 || duration > 24) {
			throw new IllegalArgumentException("Timing has to be between 1 to 24 hrs");
		}
		// price -> $2/hr
		prc = 2 * duration;
		return prc;
	}
	
	// to be done in the client side
	public String selectPaymentMethod() {
		return null;
	}
	

	public String timeStamp() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());	// gets the current time
		System.out.println(ts);
		return ts.toString();
	}
	
	/* 
	 *  This calculates the expire time 
	 * */
	public String counter(int duration) {
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.HOUR_OF_DAY, +duration);
	    System.out.println("Updated Date = " + calendar.getTime());
	    return calendar.toString();
	}
	

	public void authenticate() {
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Payment");
		
		Payment pay = new Payment();
		System.out.println(pay.price(24));
		
		pay.timeStamp();
		pay.counter(4);
	}


}
