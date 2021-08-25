package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Method of using CompletableFuture
 * assencronic methods
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */
public class RolColSum {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[][] matrix = new int[10][13];
        for (int i = 0; i < matrix.length; i++) {
            for (int z = 0; z < matrix[i].length; z++) {
                matrix[i][z] = z;
            }
        }

        RolColSum r = new RolColSum();

        System.out.println(r.sumArr(colSum(matrix)));
        System.out.println(r.sumArr(rowSum(matrix)));

        System.out.println(r.getTaskColSum(matrix).get());
        System.out.println(r.getTaskRowSum(matrix).get());
    }

    public static Sum[] rowSum(int[][] matrix) {
        int rowSum = 0;
        Sum[] arrRow = new Sum[matrix.length];
        int s = 0;
        while (s < matrix.length) {
            for (int i = 0; i < matrix[s].length; i++) {
                rowSum += matrix[s][i];
            }
            arrRow[s] = new Sum(rowSum);
            rowSum = 0;
            s++;
        }
        return arrRow;
    }


    public static Sum[] colSum(int[][] matrix) {
        var colSum = 0;
        Sum[] arrCol = new Sum[matrix[0].length];
        int s = 0;

        while (s < matrix[0].length) {
            for (int q = 0; q < matrix.length; q++) {
                colSum += matrix[q][s];
            }
            arrCol[s] = new Sum(colSum);
            colSum = 0;
            s++;
        }

        return arrCol;
    }

    public int sumArr(Sum[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i].value;
        }
        return res;
    }
    public CompletableFuture<Integer> getTaskColSum(int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> sumArr(colSum(matrix)));
    }

    public CompletableFuture<Integer> getTaskRowSum(int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> sumArr(rowSum(matrix)));
    }

    private static class Sum {
        int value;

        public Sum(int value) {
            this.value = value;
        }
    }


}




