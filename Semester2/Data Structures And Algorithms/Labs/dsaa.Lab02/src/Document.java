import java.util.Scanner;


public class Document{
	public String name;
	public OneWayLinkedList<Link> links;

	public Document(String name, Scanner scan) {
			this.name = name;
            this.links = new OneWayLinkedList<>();
			load(scan);
		}

	public void load(Scanner scan) {
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
                        links.add(new Link(candidate));
                    }
                }
            }
        }
	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	private static boolean correctLink(String link) {
        return link.matches("^[A-Za-z][A-Za-z0-9_]*");
	}

	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document: ").append(name);

        for(Link link : links) {
            sb.append("\n").append(link);
        }
		return sb.toString();
	}
}
