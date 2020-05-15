import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class prob15 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		//Note: Output will not match exactly with test cases, because the order and case of duplicate words
		//	does not matter for printing. Any order or case will be marked correct, as stated in the question.
		while(true) {
			String in = f.readLine();
			if(in.equals("0 0")) { break; }
			String one = f.readLine();
			String presOne = one;
			one = one.toLowerCase();
			String two = f.readLine();
			String presTwo = two;
			two = two.toLowerCase();
			String[] first = one.split(" ");
			String[] second = two.split(" ");
			ArrayList<String> dups = new ArrayList<String>();
			for(String str : first) {
				for(String comp : second) {
					if(str.equals(comp) && !dups.contains(str)) {
						dups.add(str);
						break;
					}
				}
			}
			System.out.println(presOne);
			System.out.println(presTwo);
			String result = Integer.toString(dups.size());
			for(String str : dups) {
				result += " " + str;
			}
			System.out.println(result);
			
		}
		
		
		f.close();
	}

}
