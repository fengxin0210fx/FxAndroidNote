package com.fx.note.java.annotation;

import com.blankj.utilcode.util.LogUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

import androidx.annotation.ColorRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

/**
 * @author Created by 冯鑫 on 2021/12/28 10:59.
 * 注解
 */
public class TestAnn {
    private static final String TAG = "TestAnn";



    /**
     * @author Created by 冯鑫 on 2021/12/28 10:46.
     *
     * 注解的理解： 注解，标签。只是给类或者方法，变量打标签，对被标记的元素没有实际意义，起一个解释说明作用。
     * 注解可以有属性，还可以通过反射获取被注解的元素的注解的属性，
     *
     * 注解的定义: 注解通过 @interface 关键字进行定义,它的形式跟接口很类似，不过前面多了一个 @ 符号。
     * 如下面的 public @interface TestAnnotation
     * 然后在创建一个类 Test,然后在类定义的地方加上 @TestAnnotation 就可以用 TestAnnotation 注解这个类了。
     * Java 预置的注解 :
     * . @Deprecated 这个元素是用来标记过时的元素  @Override 提示子类要复写父类中被 @Override 修饰的方法
     * . @SuppressWarnings 阻止警告的意思。 @SafeVarargs 参数安全类型注解。
     * . @FunctionalInterface 函数式接口 (Functional Interface) 就是一个具有一个方法的普通接口。
     *
     *
     * 不过，要想注解能够正常工作，还需要介绍一下一个新的概念那就是元注解。
     * 元注解是可以注解到注解上的注解，或者说元注解是一种基本注解，但是它能够应用到其它的注解上面。
     * 如果难于理解的话，你可以这样理解。元注解也是一张标签，但是它是一张特殊的标签，它的作用和目的就是给其他普通的标签进行解释说明的。
     * 元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种。
     *
     *
     * 1：Documented 顾名思义，这个元注解肯定是和文档有关。它的作用是能够将注解中的元素包含到 Javadoc 中去。
     *
     * 2：Target 是目标的意思，@Target 指定了注解运用的地方,你可以这样理解，当一个注解被 @Target 注解时，这个注解就被限定了运用的场景。
     * 类比到标签，原本标签是你想张贴到哪个地方就到哪个地方，但是因为 @Target 的存在，它张贴的地方就非常具体了，比如只能张贴到方法上、类上、方法参数上等等。
     * 它的取值如下：
     * ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
     * ElementType.CONSTRUCTOR 可以给构造方法进行注解
     * ElementType.FIELD 可以给属性进行注解
     * ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
     * ElementType.METHOD 可以给方法进行注解
     * ElementType.PACKAGE 可以给一个包进行注解
     * ElementType.PARAMETER 可以给一个方法内的参数进行注解
     * ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
     *
     * 3:Inherited 是继承的意思，但是它并不是说注解本身可以继承，而是说如果一个超类被 @Inherited 注解过的注解进行注解的话，
     * 那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。说的比较抽象,比如下面的：
     * 注解 TestAnnotation被@Inherited修饰，之后类TestA被TestAnnotation注解,类TestB继承TestA,类TestB也拥有TestAnnotation这个注解
     * 可以这样理解：
     * 老子非常有钱，所以人们给他贴了一张标签叫做富豪。
     * 老子的儿子长大后，只要没有和老子断绝父子关系，虽然别人没有给他贴标签，但是他自然也是富豪。
     * 老子的孙子长大了，自然也是富豪。
     * 这就是人们口中戏称的富一代，富二代，富三代。虽然叫法不同，好像好多个标签，但其实事情的本质也就是他们有一张共同的标签，也就是老子身上的那张富豪的标签。
     *
     * 4:Repeatable 自然是可重复的意思。@Repeatable 是 Java 1.8 才加进来的，所以算是一个新的特性。看下面例子persons。
     *
     * 5：Retention 的英文意为保留期的意思。当 @Retention 应用到一个注解上的时候，它解释说明了这个注解的的存活时间。
     * 它的取值如下：
     * RetentionPolicy.SOURCE  注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视
     * RetentionPolicy.CLASS   注解只被保留到编译进行的时候，它并不会被加载到 JVM 中
     * RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们
     *
     *
     * 注解的属性: 注解的属性也叫做成员变量。注解只有成员变量，没有方法。注解的成员变量在注解的定义中以“无形参的方法”形式来声明，
     * 其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。
     *
     * 在注解中定义属性时它的类型必须是 8 种基本数据类型外加 类、接口、注解及它们的数组。
     * 注解中属性可以有默认值，默认值需要用 default 关键值指定
     *
     * 注解的提取：
     * 首先注解通过反射获取。首先可以通过 Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解
     * 然后通过 getAnnotation() 方法来获取 Annotation 对象，或者是 getAnnotations() 方法。
     * 前一种方法返回指定类型的注解，后一种方法返回注解到这个元素上的所有注解
     * 如果获取到的 Annotation 如果不为 null，则就可以调用它们的属性方法了
     */
    @Inherited
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnnotation {
        int id() default -1;      //需要注意的是，在注解中定义属性时它的类型必须是 8 种基本数据类型外加 类、接口、注解及它们的数组

        String msg() default "Hi";//注解中属性可以有默认值，默认值需要用 default 关键值指定

    }

    @TestAnnotation(id = 1, msg = "哈哈哈")
    public static class TestA {
        int id = 5;        //旨在说明对象的变量和注解值没关系
        String msg = "嘿嘿";//旨在说明对象的变量和注解值没关系


        @Override
        public boolean equals(Object o) { //重写equal 当他们的成员变量相等时候认为同一个对象
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestA testAnn = (TestA) o;
            return aa == testAnn.aa &&
                    a == testAnn.a &&
                    aaa == testAnn.aaa;
        }

        @Override
        public int hashCode() {
            return Objects.hash(aa, a, aaa);  //重写hash 一般这样写
        }


        @Override
        public String toString() {
            return "TestA{" +
                    "id=" + id +
                    ", msg='" + msg + '\'' +
                    ", a=" + a +
                    ", aa=" + aa +
                    ", aaa=" + aaa +
                    '}';
        }



        //只有一个参数注解
        public @interface Check {
            String value() default "null";
        }
        @Check(value = "我是  hi")
        int a = 5;
        @Check("我是  hi")//如果一个注解内仅仅只有一个名字为 value 的属性时，应用这个注解时可以直接接属性值填写到括号内
        int aa = 3;
        @Check() //如果注解有默认值，也可以直接省略 类似 java 预制注解 Deprecated
        int aaa = 3;


        //一个注解没有任何属性,那么在应用这个注解的时候，括号都可以省略。
        public @interface Perform {
        }

        @Perform @Check
        public void testMethod() {
        }


        // 什么是容器注解呢？就是用来存放其它注解的地方。它本身也是一个注解
        // 按照规定，它里面必须要有一个 value 的属性，属性类型是一个被 @Repeatable 注解过的注解数组，注意它是数组
        // 如果不好理解的话，可以这样理解。Persons 是一张总的标签，上面贴满了 Person 这种同类型但内容不一样的标签。
        // 把 Persons 给一个 SuperMan 贴上，相当于同时给他贴了程序员、产品经理、画家的标签。
        @interface Persons {
            Person[] value();
        }
        //@Repeatable 注解了 Person。而 @Repeatable 后面括号中的类相当于一个容器注解。
        @Repeatable(Persons.class)
        public @interface Person {
            String role() default "";
        }
        //举个例子，一个人他既是程序员又是产品经理,同时他还是个画家
        @Person(role = "artist")
        @Person(role = "coder")
        @Person(role = "PM")
        public static class SuperMan {

        }
    }
    /*
     * 注解 TestAnnotation 被 @Inherited 修饰，之后类 TestA 被 TestAnnotation 注解，类 TestB 继承 TestA,类TestB 也拥有 TestAnnotation 这个注解
     * */
    public static class TestB extends TestA {

    }

    //jdk和android 预制注解演示
    public class Hero {
        //jdk 预制注解
        @Deprecated //对过时方法说明，啥时候开始就不在用了
        public void say() {
            System.out.println("Noting has to say!");
        }

        public void speak() {
            System.out.println("I have a dream!");
        }

        //android预制注解  ret 必须输入 1..100;
        public void add(@IntRange(from = 1, to = 100) int ret) {

        }
        //android预制注解 color 必须是一个颜色资源;
        public void max(@ColorRes int color) {

        }

        @NonNull   //android 预制注解
        @Override //jdk 预制注解
        public String toString() {
            return super.toString();
        }
    }

    //获取注解
    public static void startTest() {
        //获取类上面的注解
        boolean annotationPresentTestA = TestA.class.isAnnotationPresent(TestAnnotation.class);
        if (annotationPresentTestA) {
            TestAnnotation testAnnotation = TestA.class.getAnnotation(TestAnnotation.class);
            LogUtils.dTag(TAG, "annotationPresentTestA  msg  " + testAnnotation.msg() + "  id  " + testAnnotation.id());
            //打印结果：annotationPresentTestA  msg  哈哈哈  id  1
        }
        //获取继承的注解
        boolean annotationPresentTestB = TestB.class.isAnnotationPresent(TestAnnotation.class);
        if (annotationPresentTestB) {
            TestAnnotation testAnnotation = TestB.class.getAnnotation(TestAnnotation.class);
            LogUtils.dTag(TAG, "annotationPresentTestB  msg  " + testAnnotation.msg() + "  id  " + testAnnotation.id());
            //打印结果： annotationPresentTestB  msg  哈哈哈  id  1
        }


        try {
            Field a = TestA.class.getDeclaredField("a");
            a.setAccessible(true);
            Field aaa = TestA.class.getDeclaredField("aaa");
            aaa.setAccessible(true);
            //获取一个成员变量上的注解
            TestA.Check checka = a.getAnnotation(TestA.Check.class);
            if ( checka != null ) {
                LogUtils.dTag(TAG,"check checka:"+checka.value());
            }

            TestA.Check checkaaa = a.getAnnotation(TestA.Check.class);
            if ( checkaaa != null ) {
                LogUtils.dTag(TAG,"checkaaa value:"+checkaaa.value());
            }


            Method testMethod = TestA.class.getDeclaredMethod("testMethod");
            if ( testMethod != null ) {
                // 获取方法中的注解
                Annotation[] ans = testMethod.getAnnotations();
                for( int i = 0;i < ans.length;i++) {
                    LogUtils.dTag(TAG,"method testMethod annotation: "+ans[i].annotationType().getSimpleName());
                }
            }

            NoBugTest();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.eTag(TAG, e.toString());
        }



    }


    /**
     * 使用场景  注解可以用于测试 如下面例子
     * 程序员 A : 我写了一个类，它的名字叫做 NoBug，因为它所有的方法都没有错误。
     * 我：自信是好事，不过为了防止意外，让我测试一下如何？
     *程序员 A: 怎么测试？
     *我：把你写的代码的方法都加上 @Jiecha 这个注解就好了。
     * 程序员 A: 好的。
     * */

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Jiecha {
    }
    public static class NoBug {
        @Jiecha
        public void suanShu(){
            System.out.println("1234567890");
        }
        @Jiecha
        public void jiafa(){
            System.out.println("1+1="+1+1);
        }
        @Jiecha
        public void jiefa(){
            System.out.println("1-1="+(1-1));
        }
        @Jiecha
        public void chengfa(){
            System.out.println("3 x 5="+ 3*5);
        }
        @Jiecha
        public void chufa(){
            System.out.println("6 / 0="+ 6 / 0);
        }
        public void ziwojieshao(){
            System.out.println("我写的程序没有 bug!");
        }
    }
    //注解可以用于测试
    private static void NoBugTest() {
        NoBug testobj = new NoBug();
        Class clazz = testobj.getClass();
        Method[] method = clazz.getDeclaredMethods();
        //用来记录测试产生的 log 信息
        StringBuilder log = new StringBuilder();
        // 记录异常的次数
        int errornum = 0;
        for ( Method m: method ) {
            // 只有被 @Jiecha 标注过的方法才进行测试
            if ( m.isAnnotationPresent( Jiecha.class )) {
                try {
                    m.setAccessible(true);
                    m.invoke(testobj, (Object) null);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    errornum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }
        log.append(clazz.getSimpleName());
        log.append(" has  ");
        log.append(errornum);
        log.append(" error.");
        // 生成测试报告
        LogUtils.eTag(TAG,log.toString());
        /**打印结果：
         * chufa has error:
         *caused by ArithmeticException
         *divide by zero
         *NoBug has  1 error.
         * */
    }


}
