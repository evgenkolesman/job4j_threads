package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSumCompletableFuture {
    public static Sums[] sum(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            rsl[row] = getSums(matrix, row);
        }
        return rsl;
    }

    private static Sums getSums(int[][] matrix, int row) {
        int rowRsl = 0;
        int colRsl = 0;
        for (int cell = 0; cell < matrix.length; cell++) {
            rowRsl += matrix[row][cell];
            colRsl += matrix[cell][row];
        }
        return new Sums(rowRsl, colRsl);
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] rsl = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int row = 0; row < matrix.length; row++) {
            futures.put(row, getTask(matrix, row));
        }
        for (int i = 0; i < rsl.length; i++) {
            rsl[i] = futures.get(i).get();
        }
        return rsl;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int row) {
        return CompletableFuture.supplyAsync(() -> getSums(matrix, row));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] matrix = new int[10000][10000];
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        long start = System.currentTimeMillis();
        Sums[] sum = RolColSumCompletableFuture.sum(matrix);
        System.out.println(sum[10].getColSum());
        System.out.println(sum[86].getRowSum());
        System.out.println(System.currentTimeMillis() - start);

        long start2 = System.currentTimeMillis();
        Sums[] sumAsync = RolColSumCompletableFuture.asyncSum(matrix);
        System.out.println(sumAsync[10].getColSum());
        System.out.println(sumAsync[86].getRowSum());
        System.out.println(System.currentTimeMillis() - start2);
    }
}

class Sums {
    private int rowSum;
    private int colSum;

    public Sums(int rowSum, int colSum) {
        this.rowSum = rowSum;
        this.colSum = colSum;
    }

    public int getRowSum() {
        return rowSum;
    }

    public void setRowSum(int rowSum) {
        this.rowSum = rowSum;
    }

    public int getColSum() {
        return colSum;
    }

    public void setColSum(int colSum) {
        this.colSum = colSum;
    }
}
