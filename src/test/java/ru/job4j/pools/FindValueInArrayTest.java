package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindValueInArrayTest {

    @Test
    public void whenLengthLessTen() {
        int[] arr = {1, 2, 3};
        int val = 3;

        assertThat(FindValueInArray.find(arr, val), is(2));
    }

    @Test
    public void whenLengthMoreTen() {
        int[] arr = new int[12];
        for (int i = 0; i < 12; i++) {
            arr[i] = i;
        }
        int val = 3;
        assertThat(FindValueInArray.find(arr, val), is(3));
    }

    @Test
    public void whenOne() {
        int[] arr = new int[1];
        for (int i = 0; i < 1; i++) {
            arr[i] = i;
        }
        int val = 1;
        assertThat(FindValueInArray.find(arr, val), is(0));
    }
}