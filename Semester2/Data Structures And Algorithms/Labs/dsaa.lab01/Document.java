package dsaa.lab01;

import java.util.Scanner;

public class Document {

	public static void loadDocument(String name, Scanner scan) {
        String pattern = "link=";
        int charsInPattern = pattern.length();
        while (true) {
            String line = scan.nextLine();

            if (line.equals("eod")) {
                break;
            }

            String[] words = line.split(" ");
            for (String word : words) {

                word = word.toLowerCase();

                if (word.startsWith(pattern)) {

                    String candidate = word.substring(charsInPattern);

                    if (correctLink(candidate)) {
                        System.out.println(candidate);
                    }
                }
            }
        }
    }
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String link) {
		return link.matches("^[A-Za-z][A-Za-z0-9_]*"); //Note for future bracket here means firts char and bracket with plus means anychar following word
	}
}
