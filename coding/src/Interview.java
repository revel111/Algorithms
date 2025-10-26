import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Interview {

    public static void main() {
//        System.out.println(Arrays.toString(checkRecipeValidity(new String[]{"sugar", "flour"}, new String[]{"sugarflour", "floursugar", "sugar"})));

//        Map<String, List<String>> relations = new HashMap<>();
//        relations.put("Inception", Arrays.asList("Interstellar", "The Prestige", "Tenet"));
//        relations.put("Interstellar", Arrays.asList("Inception", "Gravity", "The Martian"));
//        relations.put("The Prestige", Arrays.asList("Inception", "Memento"));
//        relations.put("Tenet", Arrays.asList("Inception", "Dunkirk"));
//        relations.put("Memento", Arrays.asList("The Prestige", "Insomnia"));
//        relations.put("Dunkirk", Arrays.asList("Tenet"));
//        relations.put("Gravity", Arrays.asList("Interstellar"));
//        relations.put("The Martian", Arrays.asList("Interstellar"));
//        relations.put("Insomnia", Arrays.asList("Memento"));
//
//        Map<String, Double> ratings = new HashMap<>();
//        ratings.put("Inception", 8.8);
//        ratings.put("Interstellar", 8.6);
//        ratings.put("The Prestige", 8.5);
//        ratings.put("Tenet", 7.5);
//        ratings.put("Memento", 8.4);
//        ratings.put("Dunkirk", 7.9);
//        ratings.put("Gravity", 7.7);
//        ratings.put("The Martian", 8.0);
//        ratings.put("Insomnia", 7.1);
//
//        String givenMovie = "Inception";
//        int k = 3;

//        System.out.println(findKBestMovies(relations, ratings, givenMovie, k));
//        System.out.println(computeBiggestSquare(new int[]{1, 3, 3, 3, 2}));

        String[][] matrix = {
                {"3", "2", "1", "4", "1", "2", "3", "4"},
                {"5", "6", "#", "8", "5", "#", "7", "8"},
                {"9", "10", "11", "12", "9", "10", "11", "12"},
                {"13", "14", "15", "16", "13", "14", "15", "16"}
        };

        System.out.println(Arrays.deepToString(substituteAndSortMatrices(matrix)));
    }

    // Find k movies with the biggest rating relative to a given movie;
    private static List<String> findKBestMovies(Map<String, List<String>> relations, Map<String, Double> ratings, String givenMovie, int k) {
        Set<String> visited = new HashSet<>();
        dfsForMovies(visited, relations, givenMovie);

        Queue<Movie> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (String movie : visited) {
            maxHeap.add(new Movie(movie, ratings.get(movie)));
        }

        List<String> answer = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (!maxHeap.isEmpty()) {
                answer.add(maxHeap.poll().name);
            } else {
                break;
            }
        }
        return answer;
    }

    record Movie(String name, Double rating) implements Comparable<Movie> {
        @Override
        public int compareTo(Movie o) {
            return Double.compare(rating, o.rating);
        }
    }

    private static void dfsForMovies(Set<String> visited, Map<String, List<String>> relations, String givenMovie) {
        List<String> current = relations.get(givenMovie);

        for (String movie : current) {
            if (!visited.contains(movie)) {
                visited.add(movie);
                dfsForMovies(visited, relations, movie);
            }
        }
    }

    // Calculate a digits in an integer. If an index is odd, subtract a digit, otherwise add.
    private static int calculateDigits(int number) {
        int res = 0;

        String numberString = String.valueOf(number);
        for (int i = 0; i < numberString.length(); i++) {
            int temp = Character.getNumericValue(numberString.charAt(i));
            if (i % 2 == 0) {
                res += temp;
            } else {
                res -= temp;
            }
        }

        return res;
    }

    // Check the validity of a recipe. The recipe should match ingredients' names and their order.
    // ingredients["sugar", "flour"], recipes["sugarflour"];
    public static boolean[] checkRecipeValidity(String[] ingredients, String[] recipes) {
        boolean[] res = new boolean[recipes.length];

        for (int rec = 0; rec < recipes.length; rec++) {
            StringBuilder builder = new StringBuilder();
            for (int ing = 0; ing < ingredients.length; ing++) {
                builder.append(ingredients[ing]);
                if (Objects.equals(builder.toString(), recipes[rec]) && builder.toString().length() == recipes[rec].length()) {
                    res[rec] = true;
                }
            }
        }

        return res;
    }

    // Find the biggest square formed by adjacent buildings
    public static int computeBiggestSquare(int[] buildings) {
        int left = 0, right = 0;
        int max = 0, prevHeight = 0;

        while (right < buildings.length) {
            int leftBuilding = buildings[left], rightBuilding = buildings[right];

            int minHeight = Math.min(leftBuilding, rightBuilding);
            int width = right - left + 1;

            if (prevHeight < rightBuilding || rightBuilding < leftBuilding) {
                left = right;
            }
            max = Math.max(max, Math.min(minHeight, width) * Math.min(minHeight, width));

            prevHeight = rightBuilding;
            right++;
        }

        return max;
    }

    // In each 4x4 submatrix in matrix there is one number from 1..16 that is missing(#).
    // Plug in missing numbers and sort submatrixes ascendingly depending on the missing element.
    private static String[][] substituteAndSortMatrices(String[][] matrix) {
        Queue<MatrixEntry> matrixEntries = new PriorityQueue<>();

        for (int col = 0; col < matrix[0].length; col += 4) {
            matrixEntries.add(new MatrixEntry(col, processSubmatrix(matrix, col)));
        }

        String[][] res = new String[matrix.length][matrix[0].length];
        int shift = 0;
        while (!matrixEntries.isEmpty()) {
            int currCol = matrixEntries.poll().col();

            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < 4; col++) {
                    res[row][col + shift] = matrix[row][currCol + shift];
                }
            }
            shift += 4;
        }

        return matrix;
    }

    private static int processSubmatrix(String[][] matrix, int column) {
        int missingRow = 0, missingCol = 0;
        int sum = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = column; col < column + 4; col++) {
                if (Objects.equals(matrix[row][col], "#")) {
                    missingRow = row;
                    missingCol = col;
                } else {
                    sum += Integer.parseInt(matrix[row][col]);
                }
            }
        }

        int missing = 136 - sum;
        matrix[missingRow][missingCol] = Integer.toString(missing);
        return missing;
    }

    record MatrixEntry(int col, int missing) implements Comparable<MatrixEntry> {
        @Override
        public int compareTo(MatrixEntry o) {
            return Integer.compare(missing, o.missing);
        }
    }
}