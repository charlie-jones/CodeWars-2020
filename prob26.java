import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class prob26 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		ArrayList<String> times = new ArrayList<String>();
		//read in all cases
		while(true) {
			String in = f.readLine();
			if(in == null) { break; }
			times.add(in);
		}
		for(String time : times) {
			String[] arr = time.split(" ");
//			String[]arr = {arr2[2], arr2[3], arr2[0], arr2[1], arr2[4]};
			
			String[] d1 = arr[0].split("-");
			String[] t1 = arr[1].split(":");
			Calendar c = Calendar.getInstance();
//			System.out.println("Month 1: " + Integer.parseInt(d1[1]));
			c.set(Integer.parseInt(d1[0]), Integer.parseInt(d1[1])-1, Integer.parseInt(d1[2]), Integer.parseInt(t1[0]), Integer.parseInt(t1[1]), Integer.parseInt(t1[2]));
			
			String[] d2 = arr[2].split("-");
			String[] t2 = arr[3].split(":");
			Calendar c2 = Calendar.getInstance();
//			System.out.println("Month 2: " + Integer.parseInt(d2[1]));
			c2.set(Integer.parseInt(d2[0]), Integer.parseInt(d2[1])-1, Integer.parseInt(d2[2]), Integer.parseInt(t2[0]), Integer.parseInt(t2[1]), Integer.parseInt(t2[2]));

//			System.out.println("Time1: " + (c2.getTimeInMillis()/1000) + ", Time2: " + (c2.getTimeInMillis()/1000));
			
			long secs = ChronoUnit.SECONDS.between(c.toInstant(), c2.toInstant());
			//secs now equals the seconds between the two dates
			
			TimeZone first = c.getTimeZone();
			TimeZone second = c2.getTimeZone();
//			System.out.println(first.getOffset(c.getTimeInMillis()));
//			System.out.println(second.getOffset(c2.getTimeInMillis()));
			long diff = second.getOffset(c2.getTimeInMillis()) - first.getOffset(c.getTimeInMillis());
			long hoursDiff = diff/(60*60*24);
			if(hoursDiff < 0 ) {
				secs -= 60*60;
			} else if (hoursDiff > 0) {
				secs += 60*60;
			}
			
			
			String result = "there are ";
			if(arr[4].indexOf('D') != -1) {
				long days = secs/(60*60*24);
				secs -= days*60*60*24;
				result += days + " days ";
			}
			if(arr[4].indexOf('H') != -1) {
				long hours = secs/(60*60);
				secs -= hours*60*60;
				result += hours + " hours ";
			}
			if(arr[4].indexOf('M') != -1) {
				long mins = secs/(60);
				secs -= mins*60;
				result += mins + " minutes ";
			}
			if(arr[4].indexOf('S') != -1) {
				long sec = secs;
				secs -= sec;
				result += sec + " seconds ";
			}
			result += "remaining until " + arr[2] + " " + arr[3];

			System.out.println(result);
		}
		f.close();
	}
}
