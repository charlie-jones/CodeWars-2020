import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class prob21 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(new FileReader("input.txt"));
		
		while (s.hasNextLine()) {
			String name = s.nextLine().split(" ")[1];
			double rate = Double.parseDouble(s.nextLine().split(" ")[1]);
			String in1 = s.nextLine().split(" ")[1];
			String out1 = s.nextLine().split(" ")[1];
			String in2 = s.nextLine().split(" ")[1];
			String out2 = s.nextLine().split(" ")[1];
			int totalMin = diffMinutes(in1, out1) + diffMinutes(in2, out2);
	        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	        double tot = (rate/60 * totalMin); // check this line
			System.out.println(name + " earned $" + decimalFormat.format(tot));
		}
		s.close();
		
	}
	static int diffMinutes(String in, String out) {
		return getMinutes(out)-getMinutes(in);
	}
	
	static int getMinutes(String time) {
		String p1 = time.substring(0,2);
		String p2 = time.substring(2);
		int hrs = Integer.parseInt(p1);
		int min = Integer.parseInt(p2);
		return hrs*60+min;
	}

}
