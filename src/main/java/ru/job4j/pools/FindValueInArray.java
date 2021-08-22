package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindValueInArray extends RecursiveTask<Integer> {

    private final int arr[];
    private final int value;
    private final int to;
    private final int from;

    public FindValueInArray(int[] arr, int value, int to, int from) {
        this.arr = arr;
        this.value = value;
        this.to = to;
        this.from = from;
    }

    static int findMethod(int[] arr, int value) {
//      AtomicInteger res = new AtomicInteger();
        var res = -1;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                res = i;
            }
        }
        return res;
    }

    @Override
    protected Integer compute() {
        if(arr.length <= 10) {
            return findMethod(arr, value);
        }
        int mid = (arr.length-1)/2;
        FindValueInArray findValueInArray1 = new FindValueInArray(arr, value, to, mid);
        FindValueInArray findValueInArray2 = new FindValueInArray(arr, value, mid +1, from);
        findValueInArray1.fork();
        findValueInArray2.fork();
        return Math.max(findValueInArray1.join(), findValueInArray2.join());
    }

    public static Integer find(int[] array, int value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new FindValueInArray(array, value, 0, array.length - 1));
    }
}


