
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker("Hell9", 3, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if(word2.length() == 0) {
			return word1.length();
		}
		if (word1.length() == 0) {
			return word2.length();
		}
		if(word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		} 
		int firstMinimum = Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2)));
		return 1 + Math.min(firstMinimum, levenshtein(tail(word1), tail(word2)));
		}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		int i = 0;
		In in = new In(fileName);

		while(!in.isEmpty()) {
			dictionary[i] = in.readLine();
			i++;
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int stepsCount = threshold + 1;
		String newWord = "";
		for(int i = 0; i < dictionary.length; i++) {
			if(stepsCount > levenshtein(word, dictionary[i])) {
                stepsCount = levenshtein(word, dictionary[i]);
				newWord = dictionary[i];
			}
		}
		if(stepsCount > threshold) {
			return word;
		}
		return newWord;
	}
}
