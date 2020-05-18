import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import java.util.*;

public class prob28 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(new FileReader("input.txt"));
		
		while (s.hasNextLine()) {
			String in = s.nextLine();
			String[] line = in.split(" ");
			int n = line.length;
			BigInteger[] series = new BigInteger[n];
			for (int i = 0; i < n; i++) {
				series[i] = new BigInteger(line[i]);
			}
			// check exp
			BigInteger prev = series[0];
			boolean isSQ = true;
			for (int i = 1; i < n; i++) {
				if (series[i].compareTo(prev.multiply(prev)) != 0) {
					isSQ = false;
					break;
				}
				prev = series[i];	
			} 
			if (isSQ) {
				System.out.println("X^2");
				continue;
			}
			boolean isCU = true;
			prev = series[0];
			for (int i = 1; i < n; i++) {
				if (series[i].compareTo(prev.multiply(prev.multiply(prev))) != 0) {
					isCU = false;
					break;
				}
				prev = series[i];
						
			} 
			if (isCU) {
				System.out.println("X^3");
				continue;
			}
			// fib
			if (n>2) {
				BigInteger first = series[0];
				BigInteger second = series[1];
				boolean isFib = true;
				for (int i = 2; i < n; i++) {
					if (series[i].compareTo(first.add(second)) != 0) {
						isFib = false;
						break;
					}
					first = second;
					second = series[i];
							
				}
				if (isFib) {
					System.out.println("Fibonacci");
					continue;
				}
			}
			// geo 
			HashMap<BigInteger, Integer> factors = new HashMap<BigInteger, Integer>();
			prev = series[0];
			if (series[0].compareTo(series[1]) > 0) {
				boolean unknown = false;
				for (int i = 1; i < n; i++) {
					if (prev.mod(series[i]) != BigInteger.ZERO) { 
						System.out.println(in + " is an Unknown series");
						unknown = true;
						break;
					}
					if (!factors.containsKey(prev.divide(series[i]))) {
						factors.put(prev.divide(series[i]), 1);
					} else {
						factors.put(prev.divide(series[i]), factors.get(prev.divide(series[i]))+1);
					}
					prev = series[i];
				} 
				if (unknown) {
					continue;
				}
			} else {
				boolean unknown = false;
				for (int i = 1; i < n; i++) {
					if (series[i].mod(prev) != BigInteger.ZERO) { 
						System.out.println(in + " is an Unknown series");
						unknown = true;
						break;
					}
					if (!factors.containsKey(series[i].divide(prev))) {
						factors.put(series[i].divide(prev), 1);
					} else {
						factors.put(series[i].divide(prev), factors.get(series[i].divide(prev))+1);
					}
					prev = series[i];
				} 
				if (unknown) {
					continue;
				}
			}
			if (factors.size()==1) {
				System.out.println("Geometric");
				continue;
			} 
			else if (factors.size()==2) {
				BigInteger first = null;
				BigInteger second = null;
				
				for (BigInteger b : factors.keySet()) {
					if (first == null) {
						first = b.abs();
					} else if (b.abs().compareTo(first) == 1) {
						second = first;
						first = b.abs();
					} else {
						second = b.abs();
					}
				}
//				System.out.println(first.intValue());
//				System.out.println(second.intValue());
				if (first.mod(second) == BigInteger.ZERO && factors.get(first) <= factors.get(second)) { 
					System.out.println("Geometric (With Gaps)");
					continue;
				}
				
			}
			System.out.println(in + " is an Unknown series");
			
		}
		s.close();
		
	}

}
