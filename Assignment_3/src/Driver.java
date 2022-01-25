import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The type Driver to control flow of program
 *
 * @author Harshil Patel 40163431
 * @author Parth Navsariwala 40178800
 */
public class Driver {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CleverSIDC cleverSIDC = new CleverSIDC();

        int a=30;
        int b=0;
        System.out.println(a/b);

       /** System.out.println("Enter A File Number Between 1 and 5 To Load Data");
        String fileNumber = scan.next();**/


        try {


            scan = new Scanner(new FileInputStream(System.getProperty("user.dir") + "//src//NASTA_test_file" + (fileNumber) + ".txt"));

            System.out.println(System.getProperty("user.dir") + "/src/NASTA_test_file" + (fileNumber) + ".txt");

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter number of elements in the file. This will decide what underline data structure is used. If count is below 1000" +
                    "a linked list is used, otherwise AVL Tree is used.");
            int size = sc.nextInt();


            if (size < 1000)
            {
                cleverSIDC.SetSIDCThreshold(1000);
            }
            else
            {
                cleverSIDC.SetSIDCThreshold(500000);
            }

            while (scan.hasNextLine())
            {
                String str = scan.nextLine();
                cleverSIDC.add(str, DataGenerator.generateRandomString());
            }
            System.out.println("\nDuplicate keys have been discarded\n");

            scan.close();

            System.out.println("---DataSet Loaded into CleverSIDC---" +
                            "\nEnter your choice : \n");
            System.out.println("1) Generate Random Key");
            System.out.println("2) Get All Key");
            System.out.println("3) Add New Key");
            System.out.println("4) Remove Key");
            System.out.println("5) Get Values");
            System.out.println("6) Next Key");
            System.out.println("7) Prev Key");
            System.out.println("8) Range between two Keys");
            System.out.println("9) Exit");

            Scanner input = new Scanner(System.in);

            String str = input.next();
            while (Integer.parseInt(str) != 0) {
                int choice = Integer.parseInt(str);
                switch (choice) {
                    case 1:
                        cleverSIDC.generate();
                        System.out.println("Key Generated and Added");
                        break;

                    case 2:
                        cleverSIDC.allKeys();
                        break;

                    case 3:
                        System.out.println("Enter new 8 digit key");
                        String key = input.next();
                        System.out.println("Enter new value to insert");
                        String value = input.next();
                        boolean res = cleverSIDC.add(key, value);
                        if(res)
                            System.out.println("Key Generated and Added");
                        else
                            System.out.println("Key Already Present, not added");
                        break;

                    case 4:
                        System.out.println("Enter key to remove ");
                        key = input.next();
                        cleverSIDC.remove(Integer.parseInt(key));
                        break;

                    case 5:
                        System.out.println("Enter key to get Value ");
                        key = input.next();
                        cleverSIDC.getValues(Integer.parseInt(key));
                        break;

                    case 6:
                        System.out.println("Enter key to get Next Key ");
                        key = input.next();
                        cleverSIDC.nextKey(Integer.parseInt(key));
                        break;

                    case 7:
                        System.out.println("Enter key to get Prev Value ");
                        key = input.next();
                        cleverSIDC.prevKey(Integer.parseInt(key));
                        break;

                    case 8:
                        System.out.println("Enter key1 ");
                        key = input.next();
                        System.out.println("Enter key1 ");
                        String key2 = input.next();
                        int[] result = cleverSIDC.rangeKey(Integer.parseInt(key), Integer.parseInt(key2));
                        break;

                }
                System.out.println("\n1) Generate Random Key");
                System.out.println("2) Get All Key");
                System.out.println("3) Add New Key");
                System.out.println("4) Remove Key");
                System.out.println("5) Get Values");
                System.out.println("6) Next Key");
                System.out.println("7) Prev Key");
                System.out.println("8) Range between two Keys");
                System.out.println("9) Exit");

                str = input.next();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
