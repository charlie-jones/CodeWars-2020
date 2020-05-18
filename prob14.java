import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob14 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		int start = Integer.parseInt(f.readLine());
		int end = Integer.parseInt(f.readLine());
		String result = "";
		for(int num = start; num < end; num++) {
			String str = Integer.toString(num);
			int evens = 0;
			int odds = 0;
			for(int i = str.length()-1; i >= 0; i--) {
				if(i % 2 == 0) {
					evens += Integer.parseInt(str.charAt(i) + "");
				} else {
					odds += Integer.parseInt(str.charAt(i) + "");
				}
			}
			if(evens == odds) {
				result += num + " ";
			}
		}
		if(result.equals("")) {
			System.out.println("No Numbers found with Equal Sum in the given range!!");
		} else {
			result = result.substring(0, result.length()-1);
			System.out.println(result);
		}
		
		f.close();
	}

}
