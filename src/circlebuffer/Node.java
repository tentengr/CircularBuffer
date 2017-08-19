/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circlebuffer;

/**
 *
 * @author grigorios
 */
public class Node {
    private Node nextNode = null;
    private Object item;
    
    public Node() {}
    
    public Node(Object item) {
        this.item = item;
    }
    
    public Object getItem() {
        return this.item;
    }
    
    public void setItem(Object item) {
        this.item = item;
    }
    
    public Node getNext() {
        return this.nextNode;
    }
    
    public void setNext(Node node) {
        this.nextNode = node;
    }
}
