import java.io.*;
import java.util.*;

public class prob20 {
	static HashMap<String, Integer> outToppings; // create output hm
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
//		Scanner s = new Scanner(System.in);
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		// create hashmaps
		HashMap<String, Integer> toppingToNum = new HashMap<String, Integer>();
		HashMap<String, List<String>> typeToToppings = new HashMap<String, List<String>>();
		
		// init topping --> num 
		toppingToNum.put("Pepperoni", 32);
		toppingToNum.put("Red Peppers", 16);
		toppingToNum.put("Pineapple", 84);
		toppingToNum.put("Olives", 20);
		toppingToNum.put("Sardines", 12);
		toppingToNum.put("Onion", 28);
		toppingToNum.put("Sausage", 40);
		toppingToNum.put("Ham", 36);
		
		// init type --> toppings
		List<String> topping1 = new ArrayList<String>();
		topping1.add("Pineapple");
		topping1.add("Ham");
		typeToToppings.put("Hawaiian", topping1);
		
		List<String> topping2 = new ArrayList<String>();
		topping2.add("Red Peppers");
		topping2.add("Olives");
		topping2.add("Onion");
		topping2.add("Sausage");
		typeToToppings.put("Combo", topping2);
		
		List<String> topping3 = new ArrayList<String>();
		topping3.add("Sardines");
		topping3.add("Onion");
		typeToToppings.put("Fishaster", topping3);
		
		List<String> topping4 = new ArrayList<String>();
		topping4.add("Pepperoni");
		topping4.add("Sausage");
		topping4.add("Ham");
		typeToToppings.put("Meat-Lovers", topping4);
		
		List<String> topping5 = new ArrayList<String>();
		typeToToppings.put("Cheese", topping5);
		
		// init output hashmap
		outToppings = new HashMap<String, Integer>();
		outToppings.put("Pepperoni", 0);
		outToppings.put("Red Peppers", 0);
		outToppings.put("Pineapple", 0);
		outToppings.put("Olives", 0);
		outToppings.put("Sardines", 0);
		outToppings.put("Onion", 0);
		outToppings.put("Sausage", 0);
		outToppings.put("Ham", 0);
		
		// loop thru input
		String str;
		while ((str=f.readLine()) != null) {
			String[] pieces = str.split(" ");
			for (int i = 0; i < pieces.length; i++) {
				if (pieces[i].equals("Olive")) {
					pieces[i] = "Olives";
				}
			}
			for (int i = 0; i < pieces.length; i++) {
				if (pieces[i].equals("Red")) {
					pieces[i] = "Red Peppers";
					int len = pieces.length;
					String[] cop = pieces.clone();
					pieces = new String[len-1];
					for (int j = 0; j <= i; j++) {
						pieces[j] = cop[j];
					}
					for (int j = i+2; j < cop.length; j++) {
						pieces[j-1] = cop[j];
					}
				}
			}
			int mult = Integer.parseInt(pieces[0]);
			if (typeToToppings.containsKey(pieces[1])) {
				for (String st : typeToToppings.get(pieces[1])) {
					addTopping(st, mult*toppingToNum.get(st));
				}
			} else if (toppingToNum.containsKey(pieces[1])) {
				addTopping(pieces[1], mult*toppingToNum.get(pieces[1]));
			} else {
				for (int i = 1; i < pieces.length-1; i+=3) {
					int mul = Integer.parseInt(String.valueOf(pieces[i].charAt(pieces[i].length()-1)));
					if (typeToToppings.containsKey(pieces[i+1])) {
						for (String st : typeToToppings.get(pieces[i+1])) {
							addTopping(st, mult*(toppingToNum.get(st)/mul));
						}
					} else if (toppingToNum.containsKey(pieces[i+1])) {
						addTopping(pieces[i+1], mult*(toppingToNum.get(pieces[i+1])/mul));
					}
				}
			}
		}
		printTopping("Pepperoni");
		printTopping("Red Peppers");
		printTopping("Pineapple");
		printTopping("Olives");
		printTopping("Sardines");
		printTopping("Onion");
		printTopping("Sausage");
		printTopping("Ham");
	}
	static void addTopping(String topping, int amt) {
		outToppings.put(topping, outToppings.get(topping)+amt);
	}
	static void printTopping(String st) {
		System.out.println(st + ": " + outToppings.get(st));
	}

}
