package ru.job4j.pools;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static void main(String[] args) throws InterruptedException {
        int[][] matrix = new int[1000][1300];
        for (int i = 0; i < matrix.length; i++) {
            for (int z = 0; z < matrix[i].length; z++) {
                matrix[i][z] = z;
            }
        }
        System.out.println(Sum.colSum(matrix));
        System.out.println(Sum.rowSum(matrix));


        Thread a = new Thread(()-> {
            try {
                System.out.println(Sum.getTaskRowSum(matrix).get());
                System.out.println(Sum.getTaskСolSum(matrix).get());
                System.out.println(Sum.getTask(matrix).get());// пока дает сумму массива а не поотдельности данные
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        a.start();
        a.join();
    }
}

class Sum {
    private static int rowSum1;
    private static int colSum1;

    public static int rowSum(int[][] matrix) {

        int arrRow[] = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int z = 0; z < matrix[i].length; z++) {
                arrRow[i] += matrix[i][z];
            }
        }

        for (int a = 0; a < arrRow.length; a++) {
            rowSum1 += arrRow[a];
        }
        return rowSum1;
    }


    public static int colSum(int[][] matrix) {
        int arrCol[] = new int[matrix[0].length];
        int s = 0;
        while (s < matrix.length) {
            for (int q = 0; q < matrix[s].length; q++) {
                arrCol[q] += matrix[s][q];
            }
            s++;
        }

        for (int a = 0; a < arrCol.length; a++) {
            colSum1 += arrCol[a];
        }
        return colSum1;
    }

//    public static int [] asyncSum(int[][] matrix) {
//        int[] arr = new int[2];
//        CompletableFuture<Void> all = CompletableFuture.any(() -> {
//            arr[0]=Sum.colSum(matrix);
//            arr[1] = Sum.rowSum(matrix);});
//        return arr;
//    }

    public static CompletableFuture<int[]> getTask (int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> { int[] arr = new int[2];
            arr[0]=Sum.colSum(matrix);
            arr[1] = Sum.rowSum(matrix);
        return arr;});
    }

    public static CompletableFuture<Integer> getTaskСolSum (int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> {
            return Sum.colSum(matrix);});
    }

//    public static CompletableFuture<Void> getTaskRowSum (int[][] matrix) {
//        return CompletableFuture.runAsync(() -> {
//           Sum.rowSum(matrix);});
//        List list;
//        CompletableFuture<Void> result = Sum.rowSum(matrix).thenCombine(Sum.colSum(matrix),(r1,r2)-> list :: add );
//
//
//    }
}




