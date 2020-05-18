import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob16 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		while(true) {
			String in = f.readLine();
			if(in == null) { break; }
			String[] arr = in.split(" ");
			double val1 = Double.parseDouble(arr[0]);
			double val2 = Double.parseDouble(arr[1]);
			double res = Double.parseDouble(arr[3]);
			if(arr[2].equals("ADD")) {
				String opp = " + ";
				double out = round1(val1 + val2);
				if(out == res) {
					System.out.println(res + " is correct for " + val1 + opp + val2);
				} else {
					System.out.println(val1 + opp + val2 + " = " + out + ", not " + res);
				}
			} else if (arr[2].equals("SUBTRACT")) {
				String opp = " - ";
				double out = round1(val1 - val2);
				if(out == res) {
					System.out.println(res + " is correct for " + val1 + opp + val2);
				} else {
					System.out.println(val1 + opp + val2 + " = " + out + ", not " + res);
				}
			} else if (arr[2].equals("MULTIPLY")) {
				String opp = " * ";
				double out = round1(val1 * val2);
				if(out == res) {
					System.out.println(res + " is correct for " + val1 + opp + val2);
				} else {
					System.out.println(val1 + opp + val2 + " = " + out + ", not " + res);
				}
			} else if (arr[2].equals("DIVIDE")) {
				String opp = " / ";
				double out = round1(val1 / val2);
				if(out == res) {
					System.out.println(res + " is correct for " + val1 + opp + val2);
				} else {
					System.out.println(val1 + opp + val2 + " = " + out + ", not " + res);
				}
			} else if (arr[2].equals("POWER")) {
				String opp = " ^ ";
				double out = 1;
				for(int i = 0; i < val2; i++) {
					out = out * val1;
				}
				out = round1(out);
				if(out == res) {
					System.out.println(res + " is correct for " + val1 + opp + val2);
				} else {
					System.out.println(val1 + opp + val2 + " = " + out + ", not " + res);
				}
			}
			
			
		}
		
		
		f.close();
	}
	
	public static double round1(double num) {
//		System.out.println(num);
		if(num > 0) { num += 0.05; } else if (num < 0) { num -= 0.05; }
		String str = Double.toString(num);
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '.') {
				return Double.parseDouble(str.substring(0, i+2));
			}
		}
		//Will never hit this
		return -1.38808;
	}

}
