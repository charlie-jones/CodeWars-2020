import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class prob17 {
	
	public static class city {
		
		public String name;
		public ArrayList<city> neighbors;
		
		public city(String name) {
			this.name = name;
			neighbors = new ArrayList<city>();
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		int numberOfCities = Integer.parseInt(f.readLine());
		
		ArrayList<city> cities = new ArrayList<city>();
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i = 0; i < numberOfCities; i++) {
			String name = f.readLine();
			names.add(name);
			cities.add(new city(name));
		}
		
		//make graph
		while(true) {
			String connection = f.readLine();
			if(connection == null) { break; }
			String[] arr = connection.split(" ");
			if(arr[8].equals("air")) { continue; }
			String city1Str = arr[1];
			String city2Str = arr[6];
			city city1 = cities.get(names.indexOf(city1Str));
			city city2 = cities.get(names.indexOf(city2Str));
			if(!city1.neighbors.contains(city2)) {
				city1.neighbors.add(city2);
			}
			if(!city2.neighbors.contains(city1)) {
				city2.neighbors.add(city1);
			}
		}
		
		//traverse graph
		for(city c : cities) {
			ArrayList<city> all = new ArrayList<city>();
			for(city nei : c.neighbors) {
				all.add(nei);
			}
			for(int i = 0; i < all.size(); i++) {
				city cit = all.get(i);
				for(city more : cit.neighbors) {
					if(!all.contains(more) && !c.name.equals(more.name)) {
						all.add(more);
					}
				}
			}
			if(all.size()==0) { System.out.println("City " + c.name + " is remote and has no neighbours!"); continue; }
			String result = "City " + c.name + " is neighbour to Cities ";
			for(String str : names) {
				for(city theCit : all) {
					if(str.equals(theCit.name)) {
						result += str + ",";
						break;
					}
				}
			}
			result = result.substring(0, result.length()-1);
			System.out.println(result);
		}
		
		
		f.close();
	}

}
