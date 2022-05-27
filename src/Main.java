import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static HashMap<Item[], Integer> allPosibleBags = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Item[]> datasets = new ArrayList<>();

        Scanner scanner = new Scanner(new File("plecak.txt"));
        String in = scanner.nextLine();

        int numOfItems = Integer.parseInt(in.split(" ")[2].replace(",",""));
        int capacity = Integer.parseInt(in.split(" ")[4]);

        while (scanner.hasNextLine()){
            in = scanner.nextLine();
            if(in.split(" ")[0].equals("dataset")){
                int[] sizes = new int[numOfItems];
                in = scanner.nextLine().replaceAll("[a-z]", "").replaceAll("\s", "").replace("{", "").replace("}", "").replace("=", "");
                for (int i = 0; i < numOfItems; i++) {
                    sizes[i] = Integer.parseInt(in.split(",")[i]);
                }

                int[] values = new int[numOfItems];
                in = scanner.nextLine().replaceAll("[a-z]", "").replaceAll("\s", "").replace("{", "").replace("}", "").replace("=", "");
                for (int i = 0; i < numOfItems; i++) {
                    values[i] = Integer.parseInt(in.split(",")[i]);
                }

                Item[] bag = new Item[numOfItems];
                for (int i = 0; i < numOfItems; i++) {
                    bag[i] = new Item(i+1, sizes[i], values[i]);
                }
                datasets.add(bag);
            }
        }
        scanner.close();
        int datasetNum = (int) (Math.random()*datasets.size());
        System.out.println("Analyzing dataset "+datasetNum);


        for (int i = 0; i < datasets.get(datasetNum).length; i++) {
            getBestBagWithSetLength(datasets.get(datasetNum), datasets.get(datasetNum).length, i);
        }

        Item[] maxBag = new Item[0];
        int maxBagValue = 0;

        for (Map.Entry e : allPosibleBags.entrySet()) {
            if(maxBagValue < (int) e.getValue() && (int)e.getValue() < capacity){
                maxBag = (Item[]) e.getKey();
                maxBagValue = (int)e.getValue();
            }
        }

        System.out.println("Max value is "+maxBagValue+" from capacity "+capacity);
        System.out.println(Arrays.toString(Arrays.stream(maxBag).toArray()));
    }

        static void combinationUtil(Item[] arr, Item[] data, int start, int end, int index, int r)
        {
            if (index == r)
            {
//                for (int j=0; j<r; j++)
//                    System.out.print(data[j]+" ");
//                System.out.println("");
                int value = 0;
                for (int j=0; j<r; j++){
                    value += data[j].getValue();
                }
                Main.allPosibleBags.put(data, value);
                return ;
            }
            for (int i=start; i<=end && end-i+1 >= r-index; i++)
            {
                data[index] = arr[i];
                combinationUtil(arr, data, i+1, end, index+1, r);
            }
        }
        static void getBestBagWithSetLength(Item[] arr, int n, int r)
        {
            Item[] data = new Item[r];
            combinationUtil(arr, data, 0, n-1, 0, r);
        }
}
