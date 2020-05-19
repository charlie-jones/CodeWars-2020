import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob18 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		while(true) {
			String in = f.readLine();
			if(in == null) { break; }
			String[] arr = in.split(":");
			double hours = Integer.parseInt(arr[0]);
			double mins = Integer.parseInt(arr[1]);
			double hDeg = (hours * 30) + (mins * 0.5);
			while(hDeg > 360) {
				hDeg -= 360;
			}
			double mDeg = mins * 6;
			while(mDeg > 360) {
				mDeg -= 360;
			}
			double diff = Math.abs(hDeg-mDeg);
//			System.out.println(hDeg + ", " + mDeg);
			if(diff > 180.0) {
				diff = 360 - diff;
			}
			String result = Double.toString(diff);
			for(int i = 0; i < result.length(); i++) {
				if(result.charAt(i) == '.') {
					if(result.length() >= i+3) {
						result = result.substring(0, i+3);
					} else {
						while(result.length() < i+3) {
							result = result + "0";
						}
					}
				}
			}
			System.out.println("The angle between the Hour hand and Minute hand is " + result + " degrees.");
		}
		
		
		f.close();
	}

}
