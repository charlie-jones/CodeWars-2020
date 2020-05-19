import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class prob19 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		String encoded = f.readLine();
//		String question = f.readLine();
		
		String mash = "";
		String line1 = "";
		char[] chars = encoded.toCharArray();
		for(char c : chars) {
			if(c == ' ') { continue; }
			int ascii = (int)c;
			String hex = Integer.toHexString(ascii);
			mash += hex;
			line1 += hex + " ";
		}
		//print first line of output
		System.out.println(line1.substring(0, line1.length()-1));
		
		String code = "";
		for(int i = mash.length()-1; i >= 0; i -= 4) {
			code = mash.charAt(i) + code;
		}
		//print second line of output
		System.out.println(code);
		
		String message = "";
		for(int i = 0; i < code.length()-1; i+=2) {
			String str = code.substring(i, i+2);
			int ascii = Integer.valueOf(str, 16);
			message += (char)ascii;
		}
		//finally: the message
		System.out.println(message);
		
		f.close();
	}

}
