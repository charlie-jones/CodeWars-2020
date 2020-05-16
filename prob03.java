import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob03 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		int one = Integer.parseInt(f.readLine());
		int two = Integer.parseInt(f.readLine());
		int i = Math.min(one, two);
		while(i > 0) {
			if(one % i == 0 && two % i == 0) {
				System.out.println(i);
				break;
			}
			i--;
		}
		f.close();
	}

}
