/** 
 *   @author Tri Dang
 *   Edoras: cssc0110
 *   The list is one-based--the first element is at position #1 and the last element is
 *   at position currentSize.  Although the vector is not in sorted order, the ordering
 *   does matter. Order must be preserved if insertion/deletion happens in other than the last 
 *   position.  All of th elements in the list must be contiguous. 
 */

package data_structures; //MAKE SURE TO INCLUDE THIS IN EDORAS

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E extends Comparable<E>> implements UnorderedListADT<E> {
    private Node<E> head,tail;
    private int currentSize;
    
    class Node<T> {
        T data;
        Node<T> next;
        public Node(T obj) {
            data = obj;
            next = null;
        }
   
        public void next(T obj) {
            next = new Node<>(obj);
        }
    } //end of inner Node class
    
    public LinkedList() {
        head = tail = null;
        currentSize = 0;
    }
    
    public LinkedList(E obj) {
        head = new Node<>(obj);
        tail = head;
        head.next(null);
        currentSize = 1;
        }
   
    public boolean isValidInputPosition(int position) {
        return !(position < 1 || position > currentSize);
    }
    
//  Adds the Object obj to the list in first position.
    public void addFirst(E obj) {
        Node<E> newNode = new Node<E>(obj);
        if (isEmpty()) {
            tail = newNode;
        }
        newNode.next = head;
        head = newNode;
        currentSize++;
    }
    
//  Adds the Object obj to the end of the list.
    public void addLast(E obj) {
        Node<E> newNode = new Node<E>(obj);
        if (isEmpty()) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        currentSize++;
    }
    
//  Adds the Object obj to the list in the position indicated.  The list is one based, and
//  the first element is at position #1 (not zero).  If the position is currently occupied
//  existing elements must be shifted over to make room for the insertion.
    public void add(E obj, int position) {
        if (position < 1 || position > currentSize + 1) {
            throw new RuntimeException("ERROR: Invalid Position Input");
        }
        Node<E> prev = null, current = head;
        Node<E> newNode = new Node<E>(obj);
        for (int i = 1; i < position; i++) {
            prev = current;
            current = current.next;
        }
        if (isEmpty()) {    //if the list is empty
            head = tail = newNode;            
        }
        else if (prev == null) {    //if the list has only 1 object, or adding at first position
            newNode.next = head;
            head = newNode;
        }
        else if (current == null) { //if add at the last position
            tail.next = newNode;
            tail = newNode;
        }
        else {
            newNode.next = current;
            prev.next = newNode;
        }
        currentSize++;
    }

//  Removes and returns the object located at the parameter position.
//  Throws a RuntimeException if the position does not map to a valid position within the list.
    public E remove(int position) {
        Node<E> prev = null, current = head;
        E temp;        
        if (!isValidInputPosition(position)) {
            throw new RuntimeException("ERROR: Invalid position!");
        }
        if (isEmpty()) { //if list is empty
            return null;
        } 
        
        for (int i = 1; i < position; i++) {
            prev = current;
            current = current.next;
        }
        //if the position is first or there is only one object in the list
        if (prev == null) {
            temp = current.data;
            head = head.next;
                if (head == null) { //if you removed the only object in the list
                    tail = null;
                }
        } 
        //if the position is last
        else if (current == tail) {
            temp = current.data;
            prev.next = null;
            tail = prev;
        }
        else {
            temp = current.data;
            prev.next = current.next;
        }
        currentSize--;
        return temp;
    }
        
//  Removes and returns the parameter object obj from the list if the list contains it, 
//  null otherwise.  If more than one element matches, the element is lowest position is removed
//  and returned.
    public E remove(E obj) {
        Node<E> prev = null, current = head;
        if (isEmpty()) {
            return null;
        }
        for (int i = 1; i <= currentSize; i++) {
            if (((Comparable<E>)obj).compareTo(current.data) == 0) {
                return remove(i);
            }
            prev = current;
            current = current.next;  
            }
        if (prev == null) { //if there is only one object, or if it matched at position 1
            if (((Comparable<E>)obj).compareTo(current.data) == 0) {    //if matched at pos 1
                return remove(1);
            }
        }
        return null;
    }  
    
//  Removes and returns the first element in the list and null if the it is empty.
    public E removeFirst() {
        //if list is empty return null
        if (isEmpty()) {
            return null;
        }
        E temp = head.data;
        if (currentSize == 1) { //if there is only one object in the list
            head = tail = null;
        }
        else { //if there are more than one object in the list
            head = head.next;
        }
        currentSize--;
        return temp;
    }
    
//  Removes and returns the last element in the list and null if the it is empty.
    public E removeLast() {
        //if list is empty return null        
        if (isEmpty()) {
            return null;
        }
        Node<E> prev = null, current = head;
        E temp;
        for (int i = 1; i < currentSize; i++) {
            prev = current;
            current = current.next;
        }
        if (prev == null) { //if list contains only 1 object
            temp = head.data;
            head = tail = null;
        }
        else { //if list does not contain only 1 object
            temp = current.data;
            prev.next = null;
            tail = prev;
        }
        currentSize--;
        return temp;
        }
        
//  Returns the object located at the parameter position.
//  Throws a RuntimeException if the position does not map to a valid position within 
//  the list.
    public E get(int position) {
        if (isEmpty()) {
            return null;
        }
        if (!isValidInputPosition(position)) {
            throw new RuntimeException("ERROR: Invalid input position!");
        }
        Node<E> current = head;
        for (int i = 1; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }
    
//  Returns the list object that matches the parameter, and null if the list is empty
//  or if the element is not in the list.  If obj matches more than one element, 
//  the element with the lowest position is returned.
    public E get(E obj) {
        Node<E> current = head;
        if (!isEmpty()) {
            for (int i = 1; i <= currentSize; i++) {
                if (((Comparable<E>)obj).compareTo(current.data) == 0) {
                    return current.data;
                }
                current = current.next;    
                }
        }
        return null;
    }
    
//  Returns the position of the first element that matches the parameter obj
//  and -1 if the item is not in the list.
    public int find(E obj) {
        Node<E> current = head;
        for (int i = 1; i <= currentSize; i++) {
            if (((Comparable<E>)obj).compareTo(current.data) == 0) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

//  Returns true if the parameter object obj is in the list, false otherwise.
    public boolean contains(E obj) {
        Node<E> current = head;
        for (int i = 1; i <= currentSize; i++) {
            if (((Comparable<E>)obj).compareTo(current.data) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

//  The list is returned to an empty state.
    public void clear() {
        head = tail = null;
        currentSize = 0;
    }

//  Returns true if the list is empty, otherwise false
    public boolean isEmpty() {
        return head==null;
    }
    
//  Returns true if the list is full, otherwise false.  
    public boolean isFull() {
        return false;
    }  

//  Returns the number of Objects currently in the list.
    public int size() {
        return currentSize;
    }
    
//  Returns an Iterator of the values in the list, presented in
//  the same order as the list.  The list iterator MUST be
//  fail-fast.
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }
    
    class IteratorHelper implements Iterator<E> {
        Node<E> current; long stateCheck, modCounter;
        
        public IteratorHelper() {
            current = head;
            stateCheck = modCounter;
        }
        
        public boolean hasNext() {
            if (stateCheck != modCounter) {
                throw new RuntimeException();
            }
            return current != null;
        }
        
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E temp = current.data;
            current = current.next;
            return temp;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    } //end of IteratorHelp inner class
} //end of LinkedList class
