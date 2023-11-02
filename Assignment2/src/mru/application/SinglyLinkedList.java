package mru.application;

import java.util.Comparator;

/**
 * a singly linked list with generic elements 
 *
 * @param <T> the type of elements in the last 
 */
public class SinglyLinkedList< T extends Comparable <T> >

{
    private Node<T> start;
    private Comparator<T> comparator;

    /**
     * creates an empty singly linked list 
     */
    public SinglyLinkedList() { 
    	this.start = null;
    }
    
    /**
     * creates an empty singly linked list with a custom comparator 
     * @param comparator the comparator sued to order elements in the list 
     */
    public SinglyLinkedList(Comparator<T> comparator) {
    	this();
    	this.comparator = comparator;
    }
    
    /**
     * checks if the list is empty 
     * @return true if the list is empty and false if not empty 
     */
    public boolean isEmpty(){ 
    	return (start == null); 
    }

    /**
     * gets the first node in the singly linked list
     * @return start the first node in the list 
     */
    public Node<T> getFirst(){
    	return start;
    }

    
    /**
     * shows the number of elements in the list 
     * @return length the number of elements in the list 
     */
    public int size(){
        Node<T> curr = start;
        int length = 0;

        while(curr != null)
        {
            length++;
            curr = curr.getNext();
        }

        return length;
    }
    
    /**
     * adds an element to the end of the list 
     * @param data the data to add to the end of the list 
     */
    public void addToEnd(T data) {
        //without the if and else you will get a null pointer 
        //expection when adding the first element.
        
        Node<T> nodeToAdd = new Node<>(data); 
        if(start != null){
            Node<T> curr = start;

            while(curr.getNext() != null){
               curr = curr.getNext();
            }
            curr.setNext(nodeToAdd);
        }
        else
            start = nodeToAdd;
    }
    
    /**
     * adds an element to the start of the list 
     * @param data the data to add to the start of the list 
     */
    public void addToStart(T data) 
    { 
        Node<T> nodeToAdd = new Node<>(data); 
        if(isEmpty())
            start = nodeToAdd;
        else
        {
          nodeToAdd.setNext(start);
          start = nodeToAdd;
        }
    }
    
    /**
     * adds an element list based on the comparator order 
     * @param data the data that needs to be added to the list 
     * @param comparator the comparator used to order elements on the list 
     */
    public void addInOrder(T data, Comparator<T> comparator) {
        Node<T> newNode = new Node<>(data);

        if (start == null || comparator.compare(data, start.getData()) < 0){
            addToStart(data);
        } else {
            Node<T> current = start;
            while (current.getNext() != null && comparator.compare(data, current.getNext().getData()) >= 0) {
                current = current.getNext();
            }
            if(current.getNext() == null){
                addToEnd(data);
            }else{
                newNode.setNext(current.getNext());
                current.setNext(newNode);
            }
      
        }
    }
    
}

