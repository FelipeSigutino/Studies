package dsaa.lab04;

import java.util.ListIterator;
import java.util.Scanner;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
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
}

/*
go 10
ld zero
text link=ggg and link=cc link=a link=e link=GGG(5) link=eeee(15)
eod
show
ch 1
ld one
link=a(10)
link=e(10)
link=ggg(10)
link=ggg(15)
eod
show


 */
/*
go 10
ld zero
text link=ggg and link=cc link=a link=e link=GGG(5) link=eeee(15)
link=gGg(4)
adsad link=abc link=cos(30) link=cos(1) link=wrong(-1) link=wrong(asdf)
link=wrong(1.23) link=ok(123) link=kkk
eod
show
reverse
ch 1
ld first
correct link=cos(15) link=zzz link=cos(12) link=eee(1) link=eeee(14)
this is link=wrong(12 o yeah.
eod
show
reverse
addl 0
show
reverse
ch 0
show
reverse
ch 2
ld 3ddfg
ld ABC
and LiNk=Abc(4)
eod
show
ch 1
show
remall cos
show

 */
/*
go 10
ld zero
link=a
link=a
link=a
link=b
link=b
link=b
link=c
link=c
link=c
eod
show

 */