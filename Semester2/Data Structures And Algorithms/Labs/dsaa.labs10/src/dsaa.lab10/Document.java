package dsaa.lab10;

import java.util.Scanner;
import java.util.*;

public class Document implements IWithName{
	public String name;
	public SortedMap<String,Link> link;

	public Document(String name) {
		this.name=name.toLowerCase();
		link=new TreeMap<String,Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TreeMap<String,Link>();
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

                    Link l = createLink(candidate);

                    if(l!=null){
                        link.put(l.ref,l);
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
		String retStr="Document: "+name+"\n";
		//TODO?
		retStr+=link;
		return retStr;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String getName() {
		return name;
	}
}
