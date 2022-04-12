package ru.job4j.multihreadingcourse;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionsUsing {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int account = 0;

    public static void main(String[] args) {
        AccountPlus accountPlus = new AccountPlus();
        AccountMinus accountMinus = new AccountMinus();

        accountMinus.start();
        accountPlus.start();

    }

    static class AccountPlus extends Thread {
        @Override
        public void run() {
            lock.lock();
            account +=10;
            condition.signal();
            lock.unlock();
        }
    }

    static class AccountMinus extends Thread {
        @Override
        public void run() {
            if ( account < 10) {
                try {
                    lock.lock();
                    System.out.println(account);
                    condition.await(); // внимание wait приводит к ошибки state
                    System.out.println(account);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
          account -= 10;
        }
    }

}
