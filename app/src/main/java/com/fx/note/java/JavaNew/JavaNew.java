package com.fx.note.java.JavaNew;

/**
 * @author Created by 冯鑫 on 2022/1/1 16:38.
 * @description
 */
public class JavaNew {
    /*1、startTest，首先要加载Dog.class文件，加载过程中发现有父类Animal，
     *所以也要加载Animal.class文件，直至找到根基类，这里就是Animal*/
    public static void startTest() {
        /*4、前面步骤完成后执行main方法，输出语句*/
        System.out.println("startTest start");
        /*5、遇到new Dog()，调用Dog对象的构造函数*/
        Dog dog = new Dog();
        /*12、运行startTest函数余下的部分程序*/
        System.out.println("startTest end");
          /**
             * 输出结果
             * startTest start
             * static Animal.x1 initialized
             * static Dog.x2 initialized
             * i = 9, j = 0
             * Dog.k initialized
             * k = 47
             * j = 39
             * startTest end
           */
    }
    //父类Animal
    public static class Animal {
        /*2、初始化根基类的静态对象和静态方法*/
        private static int x1 = print("static Animal.x1 initialized");
        protected int j;
        /*7、执行初始化*/
        private int i = 9;
        /*6、调用构造方法，创建默认属性和方法，完成后发现自己没有父类*/
        public Animal() {
            /*8、执行构造方法剩下的内容，结束后回到子类构造函数中*/
            System.out.println("i = " + i + ", j = " + j);
            j = 39;
        }

        static int print(String s) {
            System.out.println(s);
            return 47;
        }
    }
    //子类 Dog
    public static class Dog extends Animal {
        /*3、初始化子类的静态对象静态方法，当然mian函数也是静态方法*/
        private static int x2 = print("static Dog.x2 initialized");
        /*4、初始化默认的属性和方法*/
        private int k = print("Dog.k initialized");
        /*5、开始创建对象，即分配存储空间->创建默认的属性和方法。
         * 遇到隐式或者显式写出的super()跳转到父类Animal的构造函数。
         * super()要写在构造函数第一行 */
        public Dog() {
            /*10、初始化结束执行剩下的语句*/
            System.out.println("k = " + k);
            System.out.println("j = " + j);
        }
    }
}
