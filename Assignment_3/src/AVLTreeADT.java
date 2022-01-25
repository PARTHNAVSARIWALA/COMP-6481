/**
 * AVLTreADT class to create AVL tree and perform its operations
 *
 * @author Harshil Patel 40163431
 * @author Parth Navsariwala 40178800
 */
public class AVLTreeADT
{

    class Node
    {
        int key, height;
        String value;
        Node left, right;

        Node(int key, String value)
        {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    Node root;
    static int index = 0;

    /*
     *   Get height of tree
     */
    int height(Node N)
    {
        if (N == null)
            return 0;
        return N.height;
    }

    /*
     *  Get max of two integers
     */
    int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

    /*
     *   Perform right rotate to AVL tree
     */
    Node rightRotate(Node z)
    {
        Node y = z.left;
        Node x = y.right;

        y.right = z;
        z.left = x;

        z.height = max(height(z.left), height(z.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }


    /*
    *   Perform left rotate to AVL tree
    */
    Node leftRotate(Node z)
    {
        Node y = z.right;
        Node x = y.left;

        y.left = z;
        z.right = x;

        z.height = max(height(z.left), height(z.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N)
    {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node insert(Node node, int key, String value)
    {
        if (node == null)
        {
            return (new Node(key, value));
        }
        if (key < node.key)
            node.left = insert(node.left, key, value);
        else if (key > node.key)
            node.right = insert(node.right, key, value);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key)
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /*
    *  Find node with minimum key value found in that tree.
    */
    Node minValueNode(Node node)
    {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    Node remove(Node root, int key)
    {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = remove(root.left, key);

        else if (key > root.key)
            root.right = remove(root.right, key);

        else
        {

            // node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;
            }
            else
            {
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = remove(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }


    void inOrder(Node node)
    {
        if (node != null)
        {
            inOrder(node.left);
            System.out.print(node.key + " -> ");
            inOrder(node.right);
        }
    }

    public String getValue(int key)
    {
        Node current = root;

        while (current != null && key != current.key)
        {
            if (key < current.key) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        if (current == null) {
            return "";
        }
        return current.value;
    }


    public Node nextKey(Node root, Node suc, int key)
    {
        if(root == null)
            return suc;

        if(root.key == key)
        {
            if (root.right != null)
            {
                Node tmp = root.right;

                while (tmp.left != null)
                    tmp = tmp.left;

                return tmp;
            }
        }

        else if(root.key > key)
        {
            suc = root;
            return nextKey(root.left, suc, key);
        }
        else
        {
            return nextKey(root.right, suc, key);
        }

        return suc;
    }

    public Node prevKey(Node root, Node pre, int key)
    {
        if(root == null)
            return pre;

        if(root.key == key)
        {
            if (root.left != null)
            {
                Node tmp = root.left;

                while (tmp.right != null)
                    tmp = tmp.right;

                return tmp;
            }
        }

        else if(root.key > key)
        {
            return prevKey(root.left, pre, key);
        }
        else
        {
            pre = root;
            return prevKey(root.right, pre, key);
        }

        return pre;
    }


    public int[] rangeKey(int key1, int key2)
    {
            int[] result = new int[500000];
            index = 0;
            rangeKeyRecursive(result, key1, key2, root);

            System.out.println(result[result.length-1]);
            return  result;
    }

    public void rangeKeyRecursive(int[] result, int key1, int key2, Node node)
    {
        if (node != null)
        {
            if(node.key > key1 && node.key < key2)
            {
                System.out.println(node.key);
                result[index++] = node.key;
                result[result.length-1] = index;
            }
            rangeKeyRecursive(result, key1, key2, node.left);
            rangeKeyRecursive(result, key1, key2, node.right);
        }
    }


}

