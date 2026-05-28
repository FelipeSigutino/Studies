package dsaa.lab08;

import java.util.Scanner;

public class Document implements IWithName{
	static final int MODVALUE=100000000;
	public String name;
	public BST<Link> link;
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
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
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringInOrder();		
		return retStr;
	}

	public String toStringPreOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPreOrder();
		return retStr;
	}

	public String toStringPostOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPostOrder();
		return retStr;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}
}
/*
show
ld first
link=h
eod
add g
show
add k
add c
add i
add m
add a
add d
add l
add p
add f
twochildren



show
preorder
postorder
rem q
rem g
show
preorder
postorder
size
ha

 */
