package dsaa.lab05;

public class Link implements Comparable<Link>{
	public String ref;
	public int weight;
	public Link(String ref) {
		this.ref=ref;
		weight=1;
	}
	public Link(String ref, int weight) {
		this.ref=ref;
		this.weight=weight;
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Link)) return false;
        Link other = (Link) obj;
        return this.ref.equals(other.ref);
    }
    public int getSingularWeight(){
        return this.weight;
    }
    @Override
    public String toString() {
        return ref+"("+weight+")";
    }
    @Override
    public int compareTo(Link another) {
        return this.ref.compareTo(another.ref);
    }
}

/*
go 10
ld doc1
link=c(10) and link=b(7)
write also link=z end finish link=a(20)
eod
add f(8)
add g(4)
add e(3)
show
bubblesort

 */
/*
link=g(20)
link=o(30)
link=a(10)

 */