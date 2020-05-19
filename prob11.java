import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob11 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		while(true) {
			String input = f.readLine();
			if(input == null) { break; }
			//convert to binary
			String in = Integer.toBinaryString(Integer.parseInt(input));
			boolean isCyclops = false;
			if(in.length() % 2 == 1) {
				isCyclops = true;
				for(int i = 0; i < in.length(); i++) {
					if(i == in.length()/2) {
						if(!(in.charAt(i) == '0')) { isCyclops = false; }
					} else {
						if(in.charAt(i) == '0') { isCyclops = false; }
					}
				}
			}
//			System.out.println(in);
			if(isCyclops) {
				System.out.println(input + " yes");
			} else {
				System.out.println(input + " no");
			}
			
		}
		
		
		f.close();
	}

}
