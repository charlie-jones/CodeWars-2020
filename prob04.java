import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob04 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		int one = Integer.parseInt(f.readLine());
		int two = Integer.parseInt(f.readLine());
		
		for(int i = Math.max(one,  two); i <= one*two; i++) {
			if(i % one == 0 && i % two == 0) {
				System.out.println(i);
				break;
			}
		}
		
		f.close();
	}

}
