/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circlebuffer;

import sun.awt.X11.XConstants;

/**
 *
 * @author grigorios
 */
public class CircleBuffer {

    private Node  lastInsertedNode = null
                , readNode = null;
    
    private int  size = 5
               , availableSlots = 5;
    
    public CircleBuffer() {}
    
    public CircleBuffer(int size) {
        this();
        this.size = this.availableSlots = size;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CircleBuffer cb = new CircleBuffer();
        
        for (int i=2; i <= 100; i++) {
            cb.pushItem(i);    
        }
        
        /*
        cb.pushItem(1);
        cb.pushItem(2);
        cb.pushItem(3);
        cb.pushItem(4);
        cb.pushItem(5);
        cb.pushItem(6);
        cb.pushItem(7);
        */
        
        
        try {
            System.out.println(cb.pop().getItem());
            System.out.println(cb.pop().getItem());
            System.out.println(cb.pop().getItem());
            System.out.println(cb.pop().getItem());
            System.out.println(cb.pop().getItem());          
        } catch(NullPointerException ex) {
            System.err.println("Buffer is empty");
        }

    }
    
    private boolean isEmpty() {
        return this.size == this.availableSlots;
    }
    
    private boolean isFull() {
        return 0 == this.availableSlots;
    }
    
    public void pushItem(Object item) {
        if (isEmpty())
            pushFirst(item);
        else if (isFull())
            pushFull(item);
        else
            pushNormal(item);
    }
    
    private void pushFirst(Object item) {
        Node newNode = new Node(item);
        this.lastInsertedNode = this.readNode = newNode;
        this.availableSlots--;
    }
    
    private void pushFull(Object item) {
        this.readNode = this.readNode.getNext();
        Node newNode = new Node(item);
        this.lastInsertedNode.setNext(newNode);
        this.lastInsertedNode = newNode;        
    }
    
    private void pushNormal(Object item) {
        Node newNode = new Node(item);
        this.lastInsertedNode.setNext(newNode);
        this.lastInsertedNode = newNode;
        this.availableSlots--;
    }
    
    public Node pop() {
        Node returnNode = null;
        if ( ! isEmpty() ) {
            returnNode = this.readNode;
            this.readNode = this.readNode.getNext();
            this.availableSlots++;
        }
        return returnNode;
    }
}
