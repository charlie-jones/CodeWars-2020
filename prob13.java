import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class prob13 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		String line1 = f.readLine();
		String[] split = line1.split(" ");
		int yen = Integer.parseInt(split[0]);
		int itemCount = Integer.parseInt(split[1]);
		ArrayList<String> all = new ArrayList<String>();
		ArrayList<String> items = new ArrayList<String>();
		//Sort items into arraylist
		for(int i = 0; i < itemCount; i++) {
			String in = f.readLine();
			all.add(in);
			String[] parse = in.split(" ");
			int price = Integer.parseInt(parse[1]);
			if(items.size() == 0) { items.add(in); continue; }
			boolean inserted = false;
			for(int j = 0; j < items.size(); j++) {
				String comp = items.get(j);
				if(price < Integer.parseInt(comp.split(" ")[1])) {
					items.add(j, in);
					inserted = true;
					break;
				}
			}
			if(!inserted) { items.add(in); }
		}
		ArrayList<String> affordable = new ArrayList<String>();
		for(String str : items) {
			int price = Integer.parseInt(str.split(" ")[1]);
			if(price <= yen) {
				affordable.add(str);
				yen -= price;
			}
		}
		boolean somethingBought = false;
		for(String str : all) {
			if(affordable.contains(str)) {
				System.out.println("I can afford " + str.split(" ")[0]);
				somethingBought = true;
			} else {
				System.out.println("I can't afford " + str.split(" ")[0]);
			}
		}
		if(!somethingBought) {
			System.out.println("I need more Yen!");
		}
		System.out.println(yen);
		
		f.close();
	}

}
