package com.mcfly.springtemp.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListMiddleOnePass extends BaseAlgorithm<LinkedListMiddleOnePass.LinkedList> {

    @Override
    LinkedList[] getArguments() {
        return new LinkedList[] {
                new LinkedList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
                new LinkedList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")
        };
    }

    @Override
    Object calculate(LinkedList linkedList) {
        int length = 0;
        LinkedList.Node current = linkedList.head;
        LinkedList.Node middle = linkedList.head;
        while (current.next != null) {
            length++;
            current = current.next;
            if (length % 2 == 0) {
                middle = middle.next;
            }
        }
        if (length % 2 == 1) {
            middle = middle.next;
        }
        return middle;
    }

    static class LinkedList {

        private final Node head;
        private Node tail;

        public LinkedList() {
            this(new String[0]);
        }

        public LinkedList(String... values) {
            this.head = new Node("head");
            tail = head;
            Arrays.stream(values)
                    .forEach(value -> add(new LinkedList.Node(value)));
        }

        public Node head() {
            return head;
        }

        public void add(Node node) {
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

        public static class Node {

            private Node next;
            private String data;

            public Node(String data) {
                this.data = data;
            }

            public String data() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public Node next() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            @Override
            public String toString() {
                return this.data;
            }
        }
    }
}
