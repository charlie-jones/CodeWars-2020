import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prob01 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		String name = f.readLine();
		System.out.println("Welcome to CodeWars, " + name + "!");
		f.close();
	}

}
