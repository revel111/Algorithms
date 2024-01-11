import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> binaryList = Arrays.asList(1, 5, 6, 8, 10, 15, 40);
        List<Integer> notSorted = Arrays.asList(45, 2, 10, -100, 34, 500, 40);
//        System.out.println(binarySearch(binaryList, 6));

//        System.out.println(factorialRecursion(3));

//        System.out.println(recursiveSum(binaryList, 0));

//        System.out.println(recursiveBinarySearch(binaryList, 0, binaryList.size() - 1, 9));

//        System.out.println(greedyAlgorithm());

//        System.out.println(wideSearch());
    }

    public static int binarySearch(List<Integer> list, int key) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int middle = (right + left) / 2;

            if (list.get(middle) == key)
                return middle;

            if (list.get(middle) > key)
                right = middle - 1;
            else
                left = middle + 1;
        }

        return -1;
    }

    public static int factorialRecursion(int x) {
        if (x == 1)
            return 1;

        return x * factorialRecursion(x - 1);
    }

    public static int recursiveSum(List<Integer> list, int i) {
        if (i == list.size() - 1)
            return list.get(list.size() - 1);

        return list.get(i) + recursiveSum(list, i + 1);
    }

    public static int recursiveBinarySearch(List<Integer> list, int left, int right, int key) {
        if (left <= right) {
            int middle = (right + left) / 2;

            if (key == list.get(middle))
                return middle;

            if (list.get(middle) > key)
                return recursiveBinarySearch(list, left, middle - 1, key);

            return recursiveBinarySearch(list, middle + 1, right, key);
        }

        return -1;
    }

    private static boolean wideSearch() {
        Map<String, Deque<String>> graph = new HashMap<>() {
            {
                put("You", new LinkedList<>(Arrays.asList("Alice", "Bob", "Claire")));
                put("Bob", new LinkedList<>(Arrays.asList("Anuj", "Peggy")));
                put("Alice", new LinkedList<>(Arrays.asList("Peggy")));
                put("Claire", new LinkedList<>(Arrays.asList("Thom", "Jonny")));
                put("Anuj", new LinkedList<>());
                put("Peggy", new LinkedList<>());
                put("Thom", new LinkedList<>());
                put("Jonny", new LinkedList<>());
            }
        };

        Queue<String> searchQueue = new LinkedList<>(graph.get("You"));
        Set<String> searched = new HashSet<>();

        while (!searchQueue.isEmpty()) {
            String person = searchQueue.poll();

            if (!searched.contains(person)) {
                if (person.charAt(0) == 'J') {
                    System.out.println(person + " is a seller");
                    return true;
                } else {
                    searchQueue.addAll(graph.get(person));
                    searched.add(person);
                }
            }
        }

        return false;
    }

    private static Set<String> greedyAlgorithm() {
        Set<String> states_needed = new HashSet<>() {
            {
                add("mt");
                add("wa");
                add("or");
                add("id");
                add("nv");
                add("ut");
                add("ca");
                add("az");
            }
        };
        Map<String, Set<String>> stations = new HashMap<>() {
            {
                put("kone", new HashSet<>(Set.of("id", "nv", "ut")));
                put("ktwo", new HashSet<>(Set.of("wa", "id", "mt")));
                put("kthree", new HashSet<>(Set.of("or", "nv", "са")));
                put("kfour", new HashSet<>(Set.of("nv", "ut")));
                put("kfive", new HashSet<>(Set.of("ca", "az")));
            }
        };
        Set<String> final_stations = new HashSet<>();

        while (!states_needed.isEmpty()) {
            String best_station = null;
            Set<String> states_covered = new HashSet<>();

            for (Map.Entry<String, Set<String>> entry : stations.entrySet()) {
                Set<String> covered = new HashSet<>(entry.getValue());
                covered.retainAll(states_needed);//убрать все, кроме тех, что в states_needed

                if (covered.size() > states_covered.size()) {
                    best_station = entry.getKey();
                    states_covered = covered;
                }
            }

            states_needed.removeAll(states_covered);
            final_stations.add(best_station);
        }

        return final_stations;
    }
}