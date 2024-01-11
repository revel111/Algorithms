import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {
    public static void main(String[] args) {
        List<Integer> notSorted = Arrays.asList(45, 2, 10, -100, 34, 500, 40);
        int[] notSortedArray = new int[]{4, 5, 4, 3, 5, 4, 3, 1, 9, 5, 1, 3};

        //System.out.println(quickSortFirst(notSorted));

        //selectionSort(notSorted);

        //insertionSort(notSorted);

        //System.out.println(mergeSort(notSorted));

        //System.out.println(Arrays.toString(countSort(new int[]{4, 5, 4, 3, 5, 4, 3, 1, 9, 5, 1, 3})));

        //quickSortSecond(notSortedArray, 0, notSortedArray.length - 1);
		
        //System.out.println(Arrays.toString(notSortedArray));
    }

    private static List<Integer> quickSortFirst(List<Integer> list) {
        if (list.size() < 2)
            return list;

        int pivot = list.get(0);
        List<Integer> less = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();

        for (int i = 1; i < list.size(); i++)
            if (list.get(i) <= pivot)
                less.add(list.get(i));
            else if (list.get(i) > pivot)
                greater.add(list.get(i));

        List<Integer> returner = new ArrayList<>(quickSortFirst(less));
        returner.add(pivot);
        returner.addAll(quickSortFirst(greater));

        return returner;
    }

    private static void selectionSort(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int mini = i;

            for (int j = i; j < list.size(); j++)
                if (list.get(j) < list.get(mini))
                    mini = j;

            int temp = list.get(i);
            list.set(i, list.get(mini));
            list.set(mini, temp);
        }

        System.out.println(list);
    }

    private static void insertionSort(List<Integer> list) {
        for (int next = 1; next < list.size(); next++) {
            int curr = next;
            int temp = list.get(next);

            while ((curr > 0) && (temp < list.get(curr - 1)))
                list.set(curr, list.get(--curr));

            list.set(curr, temp);
        }

        System.out.println(list);
    }

    private static List<Integer> mergeSort(List<Integer> list) {
        if (list.size() <= 1)
            return list;

        int m = list.size() / 2;

        return mergeFunc(mergeSort(list.subList(0, m)), mergeSort(list.subList(m, list.size())));
    }

    private static List<Integer> mergeFunc(List<Integer> list1, List<Integer> list2) {
        int i = 0, j = 0;
        List<Integer> result = new ArrayList<>();

        while ((i < list1.size()) && (j < list2.size()))
            if (list1.get(i) < list2.get(j))
                result.add(list1.get(i++));
            else
                result.add(list2.get(j++));

        while (i < list1.size())
            result.add(list1.get(i++));

        while (j < list2.size())
            result.add(list2.get(j++));

        return result;
    }

    private static int[] countSort(int[] array) {
        int maxValue = array[0];

        for (Integer i : array)
            if (i > maxValue)
                maxValue = i;

        int size = maxValue + 1;
        int[] count = new int[size];
        int[] result = new int[array.length];

        for (int j : array) count[j]++;

        for (int i = 1; i < size; i++) count[i] += count[i - 1];

        for (int i = array.length - 1; i >= 0; i--)
            result[--count[array[i]]] = array[i];

        return result;
    }

    private static int partition(int[] array, int left, int right) {
        int i = left + 1, j = right, temp = 0, m = array[left];

        do {
            while ((i < right) && (array[i] <= m))
                i++;
            while ((j > i) && (array[j] >= m))
                j--;

            if (i < j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        } while (i < j);

        if (array[i] > m) {
            array[left] = array[i - 1];
            array[i - 1] = m;
            return i - 1;
        }

        array[left] = array[i];
        array[i] = m;
        return i;
    }

    private static void quickSortSecond(int[] array, int left, int right) {
        if (left >= right)
            return;

        int k = partition(array, left, right);
        quickSortSecond(array, left, k - 1);
        quickSortSecond(array, left + 1, right);
    }
}