package dsaa.lab03;

public class Link{
	public String ref;
	// in the future there will be more fields
	public Link(String ref) {
		this.ref=ref;
	}
    public String toString() {
        return ref;
    }
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link other = (Link) o;
        return ref.equals(other.ref);
    }

}