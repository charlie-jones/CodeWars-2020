import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob02 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		StringBuilder in = new StringBuilder(f.readLine());
		String reverse = in.reverse().toString();
		System.out.println(reverse);
		
		f.close();
	}

}
