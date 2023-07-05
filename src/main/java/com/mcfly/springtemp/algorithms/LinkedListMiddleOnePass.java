package com.mcfly.springtemp.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListMiddleOnePass extends BaseAlgorithm<LinkedListMiddleOnePass.LinkedList> {

    @Override
    LinkedList[] getArguments() {
        return new LinkedList[]{
                new LinkedList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
                new LinkedList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")
        };
    }

    @Override
    Object calculate(LinkedList linkedList) {
        int length = 0;
        LinkedList.Node current = linkedList.getHead();
        LinkedList.Node middle = linkedList.getHead();
        while (current.getNext() != null) {
            length++;
            current = current.getNext();
            if (length % 2 == 0) {
                middle = middle.getNext();
            }
        }
        if (length % 2 == 1) {
            middle = middle.getNext();
        }
        return middle;
    }

    static final class LinkedList {

        private final Node head;
        private Node tail;

        LinkedList(String... values) {
            this.head = new Node("head");
            tail = head;
            Arrays.stream(values)
                    .forEach(value -> add(new LinkedList.Node(value)));
        }

        Node getHead() {
            return head;
        }

        void add(Node node) {
            tail.next = node;
            tail = node;
        }

        @Override
        public String toString() {
            final List<Node> all = new ArrayList<>();
            Node current = head;
            all.add(head);
            while ((current = current.next) != null) {
                all.add(current);
            }
            return all.toString();
        }

        static final class Node {

            private final String data;
            private Node next;

            Node(String data) {
                this.data = data;
            }

            Node getNext() {
                return next;
            }

            @Override
            public String toString() {
                return this.data;
            }
        }
    }
}
