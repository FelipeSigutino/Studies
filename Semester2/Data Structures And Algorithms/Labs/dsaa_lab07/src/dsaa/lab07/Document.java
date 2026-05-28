package dsaa.lab07;

import java.util.ListIterator;
import java.util.Scanner;

public class Document implements IWithName{
	public static final int MODVALUE=100000000;
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name) {
		this.name = name;
	}

    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
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

                    if (isCorrectId(candidate)) {
                        if(candidate.contains("(")){
                            int start  = candidate.indexOf("(");
                            int end   = candidate.indexOf(")");

                            String ref = candidate.substring(0, start);
                            int weight = Integer.parseInt(candidate.substring(start + 1, end));
                            link.add(new Link(ref, weight));
                        } else {
                            link.add(new Link(candidate));
                        }
                    }
                }
            }
        }
    }

    public static boolean isCorrectId(String id) {
        return id.matches("^[A-Za-z][A-Za-z0-9_]*(\\(\\d+\\))?$");
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    static Link createLink(String link) {
        if(isCorrectId(link)){
            if(link.contains("(")){
                int start  = link.indexOf("(");
                int end   =  link.indexOf(")");

                String ref = link.substring(0, start);
                int weight = Integer.parseInt(link.substring(start + 1, end));
                return new Link(ref, weight);
            } else {
                return new Link(link);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String retStr="Document: "+name;
        if (link.size == 0){
            return retStr;
        }
        for (Link l:link) {
            retStr += "\n";
            retStr += l.toString();
        }
        return retStr;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        if (link.size == 0){
            return retStr;
        }
        ListIterator<Link> iter=link.listIterator();
        retStr += "\n";
        while(iter.hasNext())
            iter.next();

        iter.previous();
        Link li=iter.next();
        retStr += li.toString();
        while(iter.hasPrevious()){
            retStr += "\n";
            Link l=iter.previous();
            retStr+=l.toString();

        }
        return retStr;
    }

	@Override
	public String getName() {
		return name;
	}

    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Document)) return false;

        Document other = (Document) o;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

