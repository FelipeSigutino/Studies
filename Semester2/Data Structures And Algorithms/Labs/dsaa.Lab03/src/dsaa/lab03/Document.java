package dsaa.lab03;

import java.util.Scanner;

public class Document{
	public String name;
	public TwoWayUnorderedListWithHeadAndTail<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name;
		this.link=new TwoWayUnorderedListWithHeadAndTail<Link>();
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
                        link.add(new Link(candidate));
                    }
                }
            }
        }
	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String link) {
        return link.matches("^[A-Za-z][A-Za-z0-9_]*");
	}
	
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document: ").append(name);

        for(Link link : link) {
            sb.append("\n").append(link);
        }
        return sb.toString();
    }
	
	public String toStringReverse() {
		String retStr="Document: "+name;
		return retStr+link.toStringReverse();
	}

}

/*
go 10
ld zero
text link=a and link=a and link=b and link=a and link=a and link=c and link=a link=a
eod

  */