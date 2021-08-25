package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindValueInArray extends RecursiveTask<Integer> {

    private final int arr[];
    private final int value;
    private final int to;
    private final int from;

    public FindValueInArray(int[] arr, int value, int from, int to) {
        this.arr = arr;
        this.value = value;
        this.to = to;
        this.from = from;
    }

    public int findMethod(int from, int to) {
        var res = -1;
        if (from == to) {
            return arr[0];
        }
        for (var i = from; i <= to; i++) {
            if (arr[i] == value) {
                res = i;
                break;
            }
        }
        return res;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return findMethod(from, to);
        }
        int mid = ((from + to) - 1) / 2;
        FindValueInArray findValueInArray1 = new FindValueInArray(arr, value, from, mid);
        FindValueInArray findValueInArray2 = new FindValueInArray(arr, value, mid + 1, to);
        findValueInArray1.fork();
        findValueInArray2.fork();
        return Math.max(findValueInArray1.join(), findValueInArray2.join());
    }

    public static Integer find(int[] array, int value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new FindValueInArray(array, value, 0, array.length - 1));
    }
}


