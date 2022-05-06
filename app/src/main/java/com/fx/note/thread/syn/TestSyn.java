package com.fx.note.thread.syn;

/**
 * @author Created by 冯鑫 on 2021/8/26 11:35.
 * @description
 */
public class TestSyn {
    public  void Test() {
        TestA  testA=new TestA();
        TestA  testAA=new TestA();
        TestB  textB=new  TestB();
        TestB  textBB=new  TestB();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testA.getA();
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                testAA.getA();
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                testA.getA1();
            }
        }).start();


        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                testAA.getA1();
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                testA.getA2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                textB.getB();
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                textBB.getB();
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                textB.getB1();
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                textB.getB2();
            }
        }).start();

    }
}
