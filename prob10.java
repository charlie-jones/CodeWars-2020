import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;



public class prob10 {

	public static void main (String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		String sentence = br.readLine();

		String sentence2 = sentence;

		String line = br.readLine();

		ArrayList<String> nouns = new ArrayList<String>();

		ArrayList<String> adverbs = new ArrayList<String>();

		ArrayList<String> verbs = new ArrayList<String>();

		ArrayList<String> adjectives = new ArrayList<String>();

		line = br.readLine();

		while (line.equals("ADVERBS") == false) {

			nouns.add(line);

			line = br.readLine();

		}

		line = br.readLine();

		while (line.equals("VERBS") == false) {

			adverbs.add(line);

			line = br.readLine();

		}

		line = br.readLine();

		while (line.equals("ADJECTIVES") == false) {

			verbs.add(line);

			line = br.readLine();

		}

		line = br.readLine();

		while (line.equals("END") == false) {

			adjectives.add(line);

			line = br.readLine();

		}

		int count = 0;
		
		sentence = sentence.replaceAll("\\[", "8").replaceAll("\\]","8");
		sentence2 = sentence2.replaceAll("\\[", "8").replaceAll("\\]","8");


		while (sentence.contains("8AJ8")) {

			String v = adjectives.get(count);

			sentence = sentence.replaceFirst("8AJ8", v);

			

			adjectives.remove(v);

		}



		while (sentence.contains("8AV8")) {

			String v = adverbs.get(count);

			sentence = sentence.replaceFirst("8AV8", v);

		

			adverbs.remove(v);

		}

		count = 0;

		while (sentence.contains("8N8")) {

			String v = nouns.get(count);

			

			sentence = sentence.replaceFirst("8N8", v);

	

			nouns.remove(v);

		}



		while (sentence.contains("8V8")) {

			String v = verbs.get(count);

			sentence = sentence.replaceFirst("8V8", v);

	

			verbs.remove(v);

		}

		System.out.println(sentence);
		

		while (sentence2.contains("8AJ8")) {

			String v = adjectives.get(count);

			sentence2 = sentence2.replaceFirst("8AJ8", v);

			

			adjectives.remove(v);

		}



		while (sentence2.contains("8AV8")) {

			String v = adverbs.get(count);

			sentence2 = sentence2.replaceFirst("8AV8", v);

		

			adverbs.remove(v);

		}

		count = 0;

		while (sentence2.contains("8N8")) {

			String v = nouns.get(count);

			

			sentence2 = sentence2.replaceFirst("8N8", v);

	

			nouns.remove(v);

		}



		while (sentence2.contains("8V8")) {

			String v = verbs.get(count);

			sentence2 = sentence2.replaceFirst("8V8", v);

	

			verbs.remove(v);

		}

		System.out.println(sentence2);

	}

	                         

}

