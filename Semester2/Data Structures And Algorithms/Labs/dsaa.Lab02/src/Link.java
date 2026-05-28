public class Link{
	public String ref;
	public Link(String ref) {
		this.ref=ref;
	}
    @Override
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
