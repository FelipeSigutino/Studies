package dsaa.lab05;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E extends Comparable<E>> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object = e;
            this.next = null;
            this.prev = null;
        }
        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }
        // add element e after this
        public void addAfter(Element elem) {
            Element element = elem;
            elem.next = this.next;
            elem.prev = this;

            this.next.prev = elem;
            this.next = elem;
        }
        // assert it is NOT a sentinel
        public void remove() {
            //TODO
        }
        E object;
        Element next=null;
        Element prev=null;
    }


    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E>{
        Element pos;
        public InnerIterator() {
            pos = sentinel;
        }
        @Override
        public boolean hasNext() {
            return pos.next != sentinel;
        }

        @Override
        public E next() {
            pos = pos.next;
            return pos.object;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        Element pos;
        public InnerListIterator() {
            pos =  sentinel;
        }
        @Override
        public boolean hasNext() {
            return pos.next != sentinel;
        }

        @Override
        public E next() {
            pos = pos.next;
            return pos.object;
        }
        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasPrevious() {
            return pos.prev != sentinel;
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public E previous() {
            pos = pos.prev;
            return pos.object;
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
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }
    public TwoWayCycledOrderedListWithSentinel() {
        sentinel = new Element(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        Element element = new Element(e);

        if( size == 0 ) {
            element.next = sentinel;
            element.prev = sentinel;

            sentinel.next = element;
            sentinel.prev = element;

            size++;
            return true;
        }

        Element current = sentinel.next;

        while(current != sentinel && e.compareTo(current.object) >= 0){
            current = current.next;
        }

        element.next = current;
        element.prev = current.prev;

        current.prev.next = element;
        current.prev = element;

        size++;

        return true;
    }

    private Element getElement(int index) {
        if (size == 0 || index > size -  1|| index < 0) return null;
        Element current = sentinel.next;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    private Element getElement(E obj) {
        if(size == 0 || obj == null) return null;
        Element current = sentinel.next;
        while(current != sentinel){
            if(obj.compareTo(current.object) == 0) return current;
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        sentinel.next = null;
        sentinel.prev = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        Element current = sentinel.next;
        if (size == 0) return false;
        for(int i = size-1; i >= 0; i--){
            if(element.equals(current.object)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        Element current = sentinel.next;
        if(size == 0) throw new NoSuchElementException();
        if(index < 0 || index >= size) throw new NoSuchElementException();

        for(int i = 0; i < index; i++){
            current = current.next;
        }

        return current.object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        Element current = sentinel.next;
        if(size == 0) return -1;
        for(int i = 0; i < size; i++){
            if(element.equals(current.object)) return i;
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == null &&  sentinel.prev == null) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        if (size == 0 || index > size -  1|| index < 0) throw new NoSuchElementException();
        Element current = sentinel.next;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        E returnObject = current.object;
        Element next = current.next;
        Element prev = current.prev;
        next.prev = prev;
        prev.next = next;
        size --;

        return returnObject;
    }

    @Override
    public boolean remove(E e) {
        if (size == 0) return false;
        Element current = sentinel.next;
        for(int i = 0; i < size; i++){
            if(current.object.equals(e)){
                Element next = current.next;
                Element prev = current.prev;
                next.prev = prev;
                prev.next = next;
                size --;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
    private void addElementAfter(E e){

    }

    //@SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        if(other == this) return;
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));
        }
        other.sentinel.next = null;
        other.sentinel.prev = null;

        other.size = 0;
    }

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        if (size == 0) return;
        Element current = sentinel.next;
        int iterations = size;
        for(int i = 0; i < iterations; i++){
            if(current.object.equals(e)){
                Element next = current.next;
                Element prev = current.prev;
                next.prev = prev;
                prev.next = next;
                size --;
            }
            current = current.next;
        }

        return;
    }

    public void remDup() {
        if (size == 0) return;
        Element current = sentinel.next;
        int iterations = size;

        for(int  i = 0; i < iterations - 1; i++){
            if (current.object.equals(current.next.object)) {
                Element next = current.next;
                Element prev = current.prev;
                next.prev = prev;
                prev.next = next;
                size --;
            }
            current = current.next;
        }
        return;
    }

}
