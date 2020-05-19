import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob12 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		while(true) {
			String in = f.readLine();
			if(in == null) { break; }
			String[] arr = in.split(" ");
			int feet = 0;
			int inches = 0;
			feet += 3 * Integer.parseInt(arr[0]);
			if(arr.length > 1) {
				feet += Integer.parseInt(arr[1]);
			}
			if(arr.length > 2) {
				inches += Integer.parseInt(arr[2]);
			}
			double val = feet * 0.3048 * 100;
			val += inches * 2.54;
			//for rounding
			val += 0.005;
			String result = Double.toString(val);
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
			System.out.println(result);
		}

		f.close();
	}

}
