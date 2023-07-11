package com.mcfly.springtemp.algorithms.items.structure;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Найти средний элемент в связном списке за один проход.
 * Подсказка: 2 указателя, один получает значение на каждую 2-е присвоение значение первому.
 * Если список может быть зациклен - проверяем окончание списка не на null, а на равенство head.
 * Вариация: найти i-й элемент списка с конца. Подсказка: 2-й указатель устанавливается только на i-е присвоение 1-му и
 * движется синхронно с 1-м до null (с отставанием на i).
 */
public class LinkedListMiddleOnePass extends BaseAlgorithm<LinkedListMiddleOnePass.LinkedList> {

    @Override
    public LinkedList[] getArguments() {
        return new LinkedList[]{
                new LinkedList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
                new LinkedList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")
        };
    }

    @Override
    public Object calculate(LinkedList linkedList) {
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
