import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class prob08 {
	public static void main (String[] args) throws IOException {
		Scanner s = new Scanner(new FileReader("input.txt"));
		String st = "";
		while (s.hasNextLine() || !st.equals("")) {
			String str = "";
			if (s.hasNextLine()) str = s.nextLine();
			if (str.length() + st.length() > 80) {
				if (!st.equals("") && !str.equals("")) {
					str = st + " " + str;
				} else if (str.equals("")) {
					str = st;
				}
				String[] in = str.split(" ");
			
				int i;
				int tot_chars = 0;
				boolean print = true;
				for (i = 0; i < in.length; i++) {
					if (tot_chars+in[i].length()<=80) {
						tot_chars+=in[i].length();
						if (tot_chars+1 > 80) {
							i++;
							break;
						} else {
							tot_chars++;
						}
					} else {
						for (int j = i; j < in.length; j++) {
							st += in[j];
						}
						print = false;
						break;
					}
				}
				if (print) {
					for (int j = 0; j < i-1; j++) {
						System.out.print(in[j] + " ");
					}
					if (i-1 > 0)
						System.out.println(in[i-1]);
					for (int j = i; j < in.length; j++) {
						st += in[j];
					}
				}
				
			} else {
				System.out.println(str);
				st = "";
			}
		}
		s.close();
	}

}

