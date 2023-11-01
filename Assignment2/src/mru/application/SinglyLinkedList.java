package mru.application;

import java.util.Comparator;

public class SinglyLinkedList<T>

{
    private Node<T> start;
    private Comparator<T> comparator;

    public SinglyLinkedList() { 
    	this.start = null;
    }
    
    public SinglyLinkedList(Comparator<T> comparator) {
    	this();
    	this.comparator = comparator;
    }
    
    public boolean isEmpty(){ return (start == null); }

    
    public Node<T> getFirst(){
    	return start;
    }

    public T removeFromStart()
    {
        T data = null;

        if(start != null)
        {
            if(start.getNext() == null)
            {
                data = start.getData();
                start = null;
            }
            else
            {
                Node<T> tmp = start;
                data = start.getData();
                start = start.getNext();

                tmp.setNext(null);
            }

        }
        return data;
    }
    
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
    

}

