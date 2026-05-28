public class Page {
    private final int id;
    private boolean dirty;
    private boolean referenceBit;

    public Page(int id) {
        this.id = id;
        this.dirty = false;
        this.referenceBit = false;
    }

    public int getId() {
        return id;
    }
    public boolean isDirty(){
        return dirty;
    }

    public void setDirty(boolean dirty){
        this.dirty = dirty;
    }

    public boolean isReferenceBit(){
        return referenceBit;
    }
    public void setReferenceBit(boolean referenceBit){
        this.referenceBit = referenceBit;
    }
}
