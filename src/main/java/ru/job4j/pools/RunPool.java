package ru.job4j.pools;

import java.util.Arrays;

public class RunPool {
    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 4, 5, 6, 7, 9, 8};
        int[] arr1 = {1, 3, 2, 4, 5, 6, 7, 8, 9, 10, 12, 11};

        Arrays.stream(choseArrayAndSort(arr)).forEach(System.out::print);
        System.out.print(System.lineSeparator());
        Arrays.stream(choseArrayAndSort(arr1)).forEach(System.out::print);
    }

    public static int[] choseArrayAndSort(int[] arr) {
        int[] res;
        if (arr.length <= 10) {
            res = MergeSort.sort(arr);
        } else {
            res = ParallelMergeSort.sort(arr);
        }
        return res;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
