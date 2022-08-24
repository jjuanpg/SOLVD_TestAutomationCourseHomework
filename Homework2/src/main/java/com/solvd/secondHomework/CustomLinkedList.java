package com.solvd.secondHomework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLinkedList<T> {

    private static Logger logger = LogManager.getLogger(CustomLinkedList.class);
    private Node head;
    int size = 0;

    class Node{
        private T value;
        private Node next;

        private Node(T value){
            this.value = value;
        }
    }

    public void insert(T value) {
        Node newNode = new Node(value);
        if(head == null) {
            head = newNode;
        }else {
            Node currentNode = head;
            while(currentNode.next != null) {
                currentNode= currentNode.next;
            }
            currentNode.next = newNode;
            size++;
        }
    }

    public void insertAtStart(T value) {
        Node newNode = new Node(value);

        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAt(int index, T value) {
        if(index == 0){
            insertAtStart(value); //We already have a function to do this.
        }else{
            Node newNode = new Node(value);

            Node currentNode = head;
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            size++;
        }
    }

    public void deleteAt(int index) {
        if(index == 0) {
            head = head.next;
        }else{
            Node currentNode = head;
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
            size--;
        }
    }

    public void show() {
        Node currentNode = head;
        if(currentNode == null){
            logger.fatal("Linked list is empty");
        }
        else {
            while(currentNode != null) {
                logger.info(currentNode.value + " ");
                currentNode = currentNode.next;
            }
        }
    }

    public T showAt(int index){
        Node currentNode = head;
        if(index == 0) {
            return (currentNode.value);
        }else{
            for(int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        }
        return (currentNode.value);
    }

    public int size() {
        return size;
    }

}