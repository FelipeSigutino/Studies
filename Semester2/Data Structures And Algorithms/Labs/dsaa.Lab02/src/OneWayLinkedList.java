import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {
			this.object=e;
		}
		E object;
		Element next=null;

	}
	Element sentinel;

	private class InnerIterator implements Iterator<E>{
		private Element current;

		public InnerIterator() {
			current=sentinel.next;
		}
		@Override
		public boolean hasNext() {
			if (current!=null) {
                return true;
            }
			return false;
		}
		
		@Override
		public E next() {
            if (current==null) {
                throw new NoSuchElementException();
            }
            E e=current.object;
            current=current.next;
			return e;
		}
	}
	
	public OneWayLinkedList() {
		sentinel = new Element(null);
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(E e) {
        Element current = sentinel;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new Element(e);
        return true;


    }

	@Override
	public void add(int index, E element) throws NoSuchElementException {

        Element current = sentinel;
        for (int i=0;i<index;i++){
            if(current.next==null){
                throw new NoSuchElementException();
            }
            current = current.next;
        }
        Element newElement = new Element(element);

        newElement.next = current.next;

        current.next = newElement;
	}

	@Override
	public void clear() {
		sentinel.next = null;
		
	}

	@Override
	public boolean contains(E element) {
        if (sentinel.next==null) {
            return false;
        }
        Element current = sentinel.next;
		while (current!=null){
            if (current.object.equals(element)) {
                return true;
            }
            current = current.next;
        }
		return false;
	}

	@Override
	public E get(int index) throws NoSuchElementException {
        if (sentinel.next==null) {
            throw new NoSuchElementException();
        }
        Element current = sentinel.next;
        for (int i=0;i<index;i++){
            if(current.next==null){
                throw new NoSuchElementException();
            }
            current = current.next;
        }
		return current.object;
	}

	@Override
	public E set(int index, E element) throws NoSuchElementException {
        if  (sentinel.next==null) {
            throw new NoSuchElementException();
        }
        Element current = sentinel.next;
        for (int i=0;i<index;i++){
            if(current.next==null){
                throw new NoSuchElementException();
            }
            current = current.next;
        }
        E out = current.object;
        current.object=element;
		return out;
	}

	@Override
	public int indexOf(E element) {
        if (sentinel==null) {
            return -1;
        }
		Element current = sentinel.next;

        int index=0;
        while (current !=null){
            if (current.object.equals(element)) {
                return index;
            }
            current = current.next;
            index++;
        }

		return -1;
	}

	@Override
	public boolean isEmpty() {
		return sentinel.next == null;
	}

	@Override
	public E remove(int index) throws NoSuchElementException {
        Element current = sentinel;

        if (current.next == null || index < 0) {
            throw new NoSuchElementException();
        }
        for (int i=0;i<index;i++){
            if(current.next==null){
                throw new NoSuchElementException();
            }
            current = current.next;
        }
        if (current.next ==null){
            throw new NoSuchElementException();
        }
        E out=current.next.object;
        current.next = current.next.next;
		return out;
	}

	@Override
	public boolean remove(E e) {
		Element current = sentinel;
        while(current.next!=null){
            if(current.next.object.equals(e)){
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
		return false;
	}

	@Override
	public int size() {
		Element current = sentinel;
        int size=0;
        while (current.next!=null){
            size++;
            current = current.next;
        }
		return size;
	}
	public void ODD(){
        if (sentinel.next==null) {
            return;
        }
        Element current = sentinel.next;

        if (current == null || current.next == null) {
            return;
        }
        while (current.next!=null){
            current.next = current.next.next;
            current = current.next;
            if (current == null){
                return;
            }
        }
    }
}

