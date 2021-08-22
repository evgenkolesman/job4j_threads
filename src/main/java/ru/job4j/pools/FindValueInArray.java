package ru.job4j.pools;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class FindValueInArray extends RecursiveTask<int[]> {
    private final int[] arr;
    private final int value;

    public FindValueInArray(int[] arr, int value) {
        this.arr = arr;
        this.value = value;
    }



    @Override
    protected int[] compute() {
        int[] res = new int[1];
        FindValueInArray valueInArray = new FindValueInArray(arr, value);

        return res;
    }

    public int findMethod(int value) {
        AtomicInteger res = new AtomicInteger();
        IntStream.range(0, arr.length).filter(i -> arr[i] == value).forEach(i -> res.set(i));
        return res.get();
    }


}
