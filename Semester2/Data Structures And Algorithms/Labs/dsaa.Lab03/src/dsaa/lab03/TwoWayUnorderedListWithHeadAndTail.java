package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {
			this.object=e;
		}
		public Element(E e, Element next, Element prev) {
            this.object=e;
            this.next=next;
            this.prev=prev;

		}
		E object;
		Element next = null;
		Element prev = null;
	}
	
	Element head;
	Element tail;
	// can be realization with the field size or without
	int size = 0;
	
	private class InnerIterator implements Iterator<E>{
		Element pos;

		
		public InnerIterator() {
            pos = head;
		}
		@Override
		public boolean hasNext() {
			return pos != null;
		}
		
		@Override
		public E next() {
			if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = pos.object;
            pos = pos.next;
			return value;
		}
	}
	
	private class InnerListIterator implements ListIterator<E>{
		Element p;
		// TODO maybe more fields....

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public TwoWayUnorderedListWithHeadAndTail() {
		// make a head and a tail	
		head=null;
		tail=null;
	}
	
	@Override
	public boolean add(E e) {
		Element element = new Element(e);
        if(head==null){
            head=element;
        } else {
            tail.next=element;
            element.prev=tail;
        }
        tail=element;
        size++;
        return true;
	}

	@Override
	public void add(int index, E element) {
        Element Element = new Element(element);
        if (element == null) {
            throw new NoSuchElementException();
        }
        if(index<0 || index>size){
            throw new NoSuchElementException();
        }else if(index==size){
            add(element);
            return;
        }else if(index==0){
            Element afterhead = head;
            head = Element;
            afterhead.prev=Element;
            Element.next=afterhead;
            Element.prev=head;
            size++;
            return;
        }else if(index==size-1){
            Element oldTail = tail;
            Element beforetail = oldTail.prev;
            beforetail.next=Element;
            Element.prev=beforetail;
            Element.next=oldTail;
            oldTail.prev=Element;
            size++;
            return;
        }
        Element current=head;
        for (int i = 0; i < index; i++) {
            current=current.next;
        }

        Element before = current.prev;
        Element after = current;

        before.next=Element;
        after.prev=Element;
        Element.prev=before;
        Element.next=after;
        size++;
	}

	@Override
	public void clear() {
		head = null;
        tail = null;
        size = 0;
	}

	@Override
	public boolean contains(E element) {
        if (head == null) {
            return false;
        }
        Element current = head;
		for(int i = 0;i<size;i++){
            if (current.object.equals(element)) {
                return true;
            }
            current = current.next;
        }
		return false;
	}

	@Override
	public E get(int index) {
        Element current = head;
		if (index<0 || index>=size) {
            throw new NoSuchElementException();
        }
        for(int i = 0;i<size;i++){
            if (index==i){
                return current.object;
            }
            current = current.next;
        }
		return null;
	}

	@Override
	public E set(int index, E element) {
		Element current = head;
        if (index<0 || index>=size) {
            throw new NoSuchElementException();
        }
        for(int i = 0;i<size;i++){
            if (index==i){
                E temp = current.object;
                current.object=element;
                return temp;
            }
            current = current.next;
        }
		return null;
	}

	@Override
	public int indexOf(E element) {
		Element current = head;
        for(int i = 0;i<size;i++){
            if (current.object.equals(element)) {
                return i;
            }
            current = current.next;
        }
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
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
	public E remove(int index) {
        Element temp;
        Element current = head;
        if (index<0 || index>=size){
            throw new NoSuchElementException();
        }
        if (size == 1){
            temp = head;
            clear();
            return temp.object;
        }
        if(index==0){
            temp = current;
            current.next.prev = current.prev;
            head = current.next;
            size--;
            return temp.object;
        }
        for(int i = 0;i<index;i++){
            current = current.next;
        }
        if(index==size-1){
            temp = current;
            current.prev.next = current.next;
            tail = current.prev;
            size--;
            return temp.object;
        }
        temp = current;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return temp.object;
	}

	@Override
	public boolean remove(E e) {
        Element current = head;
        if (size == 1 && current.object.equals(e)) {
            clear();
            return true;
        }
		for (int i = 0; i < size; i++) {
            if (current.object.equals(e)) {

                if (i == size - 1) {
                    current.prev.next = current.next;
                    tail = current.prev;
                    size--;
                    return true;
                }
                if (i == 0){
                    current.next.prev = current.prev;
                    head = current.next;
                    size--;
                    return true;
                }
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                return true;
            }
            current = current.next;
        }
		return false;
	}

    public void remdup(){
        if(head == null){
            return;
        }
        Element current = head;
        int iterations = size;
        for(int i = 0;i<iterations;i++){
            if(current == tail){
                return;
            }
            if(current.object.equals(current.next.object)){
                if (i == 0){
                    current.next.prev = current.prev;
                    head = current.next;
                    size--;
                } else {

                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
            }
            current = current.next;
        }

    }

	@Override
	public int size() {
		return size;
	}
	public String toStringReverse() {
		ListIterator<E> iter=new InnerListIterator();
		while(iter.hasNext())
			iter.next();
		String retStr="";
        StringBuilder sb = new StringBuilder();


        for(int i = size -1; i>=0 ; i--) {
            sb.append("\n").append(get(i));
        }
        return sb.toString();
//		return retStr;
	}

	public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
		if (other.head == null) {
            return;
        }
        if (other == this) {
            return;
        }
        if (this.head == null) {
            this.head = other.head;
            this.tail = other.tail;
            this.size = other.size;
        } else{
            this.tail.next = other.head;
            other.head.prev = this.tail;

            this.tail = other.tail;
            this.size += other.size;
        }
        other.clear();
	}


}

