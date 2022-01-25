/**
 * LinkedListADT class to implement LinkedList and it's method
 *
 * @author Harshil Patel 40163431
 * @author Parth Navsariwala 40178800
 */
public class LinkedListADT {
    /**
     * Inner class for node of Linkedlist
     */
    public class Node {
        int key;
        String value;
        Node next;
    }


    Node head;
    int size;

    /**
     * add an entry for the given key and value.
     *
     * @param key   key
     * @param value value for the key
     */
    void add(int key, String value) {

        Node t = new Node();
        t.key = key;
        t.value = value;
        t.next = head;
        head = t;
        size++;
    }

    /**
     * remove the entry for the given key.
     *
     * @param key
     */
    void deleteNode(int key) {
        Node temp = head, prev = null;


        if (temp != null && temp.key == key)
        {
            head = temp.next;
            size--;
            System.out.println("Key found and removed");
            return;
        }


        while (temp != null && temp.key != key)
        {
            prev = temp;
            temp = temp.next;
        }


        if (temp == null)
        {
            System.out.println("Key not present");
            return;
        }

        System.out.println("Key found and removed");
        prev.next = temp.next;
        size--;
    }

    /**
     * TO get value of given key
     *
     * @param key Key
     *            return the values of the given key
     */
    public String getValues(int key)
    {
        Node current = head;
        while (current != null)
        {
            if (current.key == key)
            {
                return current.value;
            }
            current = current.next;
        }
        System.out.println("Key Not Present");
        return "";
    }

    /**
     * @param key key
     *            return the key for the successor of key
     */
    public int nextKey(int key) {
        Node temp = head;


        int nextKey = Integer.MAX_VALUE;

        while (temp.next != null) {
            temp = temp.next;

            if (temp.key > key && temp.key < nextKey)
                nextKey = temp.key;

        }

        if (nextKey == Integer.MAX_VALUE)
        {
            System.out.println("No Next Key");
            return -1;
        }
        else
        {
            System.out.println("Next Key of " + key + " : " + nextKey);
            return nextKey;
        }


    }

    /**
     * @param key Key
     *            return the key for the predecessor of key.
     */
    public int prevKey(int key)
    {
        Node temp = head;

        int prevKey = Integer.MIN_VALUE;

        while (temp.next != null) {
            temp = temp.next;

            if (temp.key < key && temp.key > prevKey)
                prevKey = temp.key;

        }

        if (prevKey == Integer.MIN_VALUE)
        {
            System.out.println("No Previous Key");
            return -1;
        }
        else
        {
            System.out.println("Previous Key of " + key + " : " + prevKey);
            return prevKey;
        }


    }

    /**
     * @param key1 first key
     * @param key2 Second Key
     *             return the number of keys that are within the specified range of the two keys key1 and key2
     */

    public int[] rangeKey(int key1, int key2) {
        if (key1 == key2) return new int[0];

        Node temp = head;
        Node start = head;
        int count = 0;

        while (temp != null && temp.key != key1)
            temp = temp.next;

        if (temp != null)
        {
            temp = temp.next;
            start = temp;
        }
        else
        {
            System.out.println("Key1 does not exists");
            int[] f = new int[1];
            f[0] = -1;
            return f;
        }

        while (temp != null && temp.key != key2)
        {
            count++;
            temp = temp.next;
        }

        if (temp == null)

        {
            System.out.println("Key2 does not exists");
            int[] f = new int[1];
            f[0] = -1;
            return f;
        }

        int[] arr = new int[count];
        int i = 0;

        temp = start;
        while (temp != null && temp.key != key2) {
            arr[i] = temp.key;
            i++;
            temp = temp.next;
        }

        for (int a : arr)
            System.out.print(a + "  ");

        return arr;


    }

    /**
     * Method to print content of the list
     */
    public void printList() {
        System.out.print("Linked List: ");
        Node temp = head;
        while (temp != null) {
            System.out.print(String.format("%08d", temp.key) + "---->");
            temp = temp.next;
        }
        System.out.println("X");
    }

    /**
     * To sort all the keys
     */
    public void allKeys() {
        head = mergeSort(head);
        printList();
    }


    /*
    * Merge sort caller
    */
    public Node sortedMerge(Node a, Node b) {
        Node result = null;

        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.key <= b.key) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    /**
     * TO sort the linkedList
     *
     * @param h list
     * @return sorted list
     */
    public Node mergeSort(Node h) {

        if (h == null || h.next == null) {
            return h;
        }

        Node middle = getMiddle(h);
        Node nextofmiddle = middle.next;

        middle.next = null;

        Node left = mergeSort(h);

        Node right = mergeSort(nextofmiddle);

        Node sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    /**
     * To find middle element of the linked list
     *
     * @param head linkedlist
     * @return middle element of the list
     */
    public static Node getMiddle(Node head)
    {
        if (head == null)
            return head;

        Node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}