
/**
 * CleverSIDC automatically adapts to dynamic content that its operate on
 * It accept the size as a parameter and uses appropriate data structure
 * if an CleverSIDC contains only a small number of entries it users LinkedList
 * if the number of contained entries is large it uses HashMap.
 *
 * @author Harshil Patel 40163431
 * @author Parth Navsariwala 40178800
 */
public class CleverSIDC {


    /**
     * HashSet class to implement hash table, and it's method.
     */
    public static class HashSet {
        Integer[] set;

        /**
         * Default constructor of HashSet class which set array size to 100000000.
         */
        public HashSet() {
            set = new Integer[100000000];
        }

        /**
         * Method to check value already exist or not.
         *
         * @param val value
         * @return true if value exist or return false it not exists.
         */
        public boolean checkIfExists(int val) {
            if (set[val] != null)
                return true;
            return false;
        }

        /**
         * Method to add value into HashTable
         *
         * @param val Value
         */
        public void addToSet(int val) {
            set[val] = val;
        }


    }

    LinkedListADT linkedlistSIDC;
    AVLTreeADT avlTreeSIDC;
    boolean flag;
    HashSet hashSet;

    CleverSIDC() {
        linkedlistSIDC = new LinkedListADT();
        avlTreeSIDC = new AVLTreeADT();
        flag = true;
        hashSet = new HashSet();
    }

    /**
     * Set data structure from size
     *
     * @param size key size
     */
    public void SetSIDCThreshold(int size) {
        if (size > 1000) {
            flag = false;
        } else {
            flag = true;
        }
    }

    /**
     * randomly generates new non-existing key of 8 digits.
     */
    public void generate() {

        while (true) {

            int randomKey = (int) ((Math.random() * (100000000 - 10000000)) + 10000000);

            if (hashSet.checkIfExists(randomKey) == false) {
                hashSet.addToSet(randomKey);
                add(randomKey + "", DataGenerator.generateRandomString());
                System.out.println("Random key generated " + randomKey);
                return;
            }

        }


    }


    /**
     * add an entry for the given key and value.
     *
     * @param key string
     */
    public boolean add(String key, String value)
    {

        if(!hashSet.checkIfExists(Integer.parseInt(key)))
        {
            hashSet.addToSet(Integer.parseInt(key));
        }
        else
        {
            return false;
        }

        if (flag)
        {
            linkedlistSIDC.add(Integer.parseInt(key), value);
        }
        else
        {
            avlTreeSIDC.root = avlTreeSIDC.insert(avlTreeSIDC.root, Integer.parseInt(key), value);
        }
        return true;
    }

    /**
     * remove the entry for the given key.
     *
     * @param key key
     */
    public void remove(Integer key) {

        if (flag) {
            linkedlistSIDC.deleteNode(key);
        }
        else
        {
            avlTreeSIDC.root = avlTreeSIDC.remove(avlTreeSIDC.root, key);
        }

    }

    /**
     * @param key Key
     *            return the values of the given Key
     */
    public String getValues(Integer key) {
        String value = "";
        if (flag)
        {
            value = linkedlistSIDC.getValues(key);
        }
        else
        {
            value = avlTreeSIDC.getValue(key);
        }

        System.out.println("Key : " + key + " Value : " + value);
        return value;

    }

    /**
     * return the key for the successor of key.
     *
     * @param key Key
     */
    public void nextKey(int key) {

        if (flag) {
            int nextKey = linkedlistSIDC.nextKey(key);
        }
        else
        {
            AVLTreeADT.Node next = avlTreeSIDC.nextKey(avlTreeSIDC.root, null, key );

            if(next != null)
            {
                System.out.println("Next : " + next.key);
            }
            else
            {
                System.out.println("No Next Key present");
            }

        }

    }

    /**
     * return the key for the predecessor of key.
     *
     * @param key Key
     */
    public void prevKey(int key) {

        if (flag) {
            int prevKey = linkedlistSIDC.prevKey(key);
        }
        else
        {
            AVLTreeADT.Node prev = avlTreeSIDC.prevKey(avlTreeSIDC.root, null, key );

            if(prev != null)
            {
                System.out.println("Previous : " + prev.key);
            }
            else
            {
                System.out.println("No Previous Key present");
            }

        }

}

    /**
     * return all keys in CleverSIDC as a sorted sequence.
     */
    public void allKeys() {

        if (flag)
        {
            linkedlistSIDC.allKeys();
        }
        else
        {
            avlTreeSIDC.inOrder(avlTreeSIDC.root);
        }

    }

    public int[] rangeKey(int key1, int key2)
    {

        if(flag)
        {
            int[] result = linkedlistSIDC.rangeKey(key1, key2);

            return result;
        }
        else
        {
            int[] result = avlTreeSIDC.rangeKey(key1, key2);

            System.out.println();
            for (int i = 0; i < result[result.length - 1]; i++) {
                System.out.print(result[i] + " -> ");
            }
            System.out.println();

            return result;
        }
    }
}
