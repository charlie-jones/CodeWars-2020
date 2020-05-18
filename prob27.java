import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class prob27 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(new FileReader("input.txt"));
		
		while (s.hasNextLine()) {
			String in = s.nextLine();
			if (in.equals("0")) break;
			String[] line = in.split(" ");
			String out = in.substring(2) + " ";
			// check copy
			String first = line[1];
			boolean isCop = true;
			for (int i = 2; i < line.length; i++) {
				if (!(line[i].equals(first))) {
					isCop = false;
					break;
				}
			}
			if (isCop) {
				System.out.println(out + "COPY");
				continue;
			}
			
			String order = "IAEOU";
			int i;
			for (i = 0; i < line[1].length(); i++) {
				if (line[1].charAt(i) != line[2].charAt(i)) break;
			}
			if (!(line[1].substring(0,i).equals(line[2].substring(0,i))) || line[1].length() != line[2].length() || i == 0 || !(order.contains(String.valueOf(line[1].charAt(i))) && order.contains(String.valueOf(line[2].charAt(i))))) {
				// check shm
				
				if (line.length==3 && (line[1].substring(0, 3).equals("SHM") || line[2].substring(0, 3).equals("SHM"))) { 
					System.out.println(out + "SHM");
					continue;
				} 
				// rhyming
				System.out.println(out + "RHYMING");
				continue;
			}
			// check ablaut/progressive
			String type = (order.indexOf(line[1].charAt(i)) < order.indexOf(line[2].charAt(i))) ? "PROGRESSIVE" : "ABLAUT";
			System.out.println(out + type);
			
			
			
			
		}
		s.close();
		
	}
	

}
