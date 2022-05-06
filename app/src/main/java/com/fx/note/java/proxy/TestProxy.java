package com.fx.note.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Created by 冯鑫 on 2021/12/29 15:22.
 * java 代理熟悉，代码基本全是抄袭的
 */
public class TestProxy {


    /**
     * 代理模式（Proxy）是通过代理对象访问目标对象，这样可以在目标对象基础上增强额外的功能，如添加权限，访问控制和审计等功能
     *
     *
     * 静态代理： 先写一个业务接口类，然后被代理类和代理类都实现这个接口，然后被代理类通过构造传给代理类。
     *           最后new 一个代理类就可以代理 被代理的类了。
     * 动态代理： 先写一个业务接口类，然后被代理类实现这个接口, 然后通过jdk的
     *           Proxy.newProxyInstance（ClassLoader loader,Class<?>[] interfaces,InvocationHandler h）
     *           获取到代理类 xxClass（这个时候这个类就实现了这个业务接口）。
     *           其中 new InvocationHandler 需要实现invoke 方法 ，然后该方法 return method.invoke(target)；
     *            target 就是代理类。
     *           最后通过 xxClass 就可以操作 被代理的类了。
     * Java代理分为静态代理和动态代理和Cglib代理，下面进行逐个说明。
     */


    //一般代理写法 通常先写通用接口
    public interface AdminService {
        void update();

        Object find();
    }

    //写接口 AdminService 的实现类，这个就是要被代理的对象。
    public static class AdminServiceImpl implements AdminService {
        public void update() {
            System.out.println("修改管理系统数据");
        }

        public Object find() {
            System.out.println("查看管理系统数据");
            return new Object();
        }
    }

    /**
     * 静态代理方式的代理实现对象
     * 去代理上面被代理的类
     * 也去实现共同接口 AdminService
     */
    public static class AdminServiceProxy implements AdminService {

        private AdminService adminService;

        //把要被代理的对象传进去。
        public AdminServiceProxy(AdminService adminService) {
            this.adminService = adminService;
        }

        public void update() {
            System.out.println("判断用户是否有权限进行update操作");
            adminService.update();
            System.out.println("记录用户执行update操作的用户信息、更改内容和时间等");
        }

        public Object find() {
            System.out.println("判断用户是否有权限进行find操作");
            System.out.println("记录用户执行find操作的用户信息、查看内容和时间等");
            return adminService.find();
        }

    }

    //动态代理的 InvocationHandler 主要用于实现invoke 方法
    public static class AdminServiceInvocation  implements InvocationHandler {

        private Object target;

        public AdminServiceInvocation(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("判断用户是否有权限进行操作");
            Object obj = method.invoke(target);
            System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
            return obj;
        }
    }
    //动态代理实现类 主要是实现 Proxy.newProxyInstance（）；
    public static class AdminServiceDynamicProxy {
        private Object target;
        private InvocationHandler invocationHandler;
        public AdminServiceDynamicProxy(Object target,InvocationHandler invocationHandler){
            this.target = target;
            this.invocationHandler = invocationHandler;
        }

        public Object getPersonProxy() {
            //核心方法
            Object obj = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
            return obj;
        }
    }

    public static void startTest() {
        //静态代理
        AdminService adminService=new AdminServiceImpl();
        AdminServiceProxy adminServiceProxy=new AdminServiceProxy(adminService);
        adminServiceProxy.update();
        System.out.println("=============================");
        adminServiceProxy.find();
        System.out.println("++++++++++++++++++");
        /**
         *  输出结果：
         *         判断用户是否有权限进行update操作
         *                 修改管理系统数据
         *         记录用户执行update操作的用户信息、更改内容和时间等
         *                 =============================
         *         判断用户是否有权限进行find操作
         *         记录用户执行find操作的用户信息、查看内容和时间等
         *                 查看管理系统数据
         *
         * 总结：
         * 静态代理模式在不改变目标对象的前提下，实现了对目标对象的功能扩展。
         * 不足：静态代理实现了目标对象的所有方法，一旦目标接口增加方法，代理对象和目标对象都要进行相应的修改，增加维护成本。
         * 为解决静态代理对象必须实现接口的所有方法的问题，Java给出了动态代理
         */

        //动态代理方法1
        AdminServiceInvocation adminServiceInvocation=new AdminServiceInvocation(adminService);//实现 InvocationHandler 重写 invoke
        AdminServiceDynamicProxy adminServiceDynamicProxy=new AdminServiceDynamicProxy(adminService,adminServiceInvocation);
        AdminService personProxy1 = (AdminService) adminServiceDynamicProxy.getPersonProxy();
        personProxy1.update();
        System.out.println("=============================");
        personProxy1.find();
        System.out.println("++++++++++++++++++");
        //动态代理方法2
        AdminService personProxy2= (AdminService) Proxy.newProxyInstance(adminService.getClass().getClassLoader()
                                                        ,adminService.getClass().getInterfaces()
                                                        ,adminServiceInvocation);
        personProxy2.update();
        System.out.println("=============================");
        personProxy2.find();
        System.out.println("++++++++++++++++++");


        //动态代理方法3
        AdminService personProxy3= (AdminService) Proxy.newProxyInstance(adminService.getClass().getClassLoader()
                , adminService.getClass().getInterfaces()
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("判断用户是否有权限进行操作");
                        Object obj = method.invoke(adminService);
                        System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
                        return obj;
                    }
                });
        personProxy3.update();
        System.out.println("=============================");
        personProxy3.find();
        /**
         *  输出结果：
         *         判断用户是否有权限进行操作
         *         修改管理系统数据
         *         记录用户执行update操作的用户信息、更改内容和时间等
         *          =============================
         *         判断用户是否有权限进行操作
         *         查看管理系统数据
         *         记录用户执行操作的用户信息、更改内容和时间等
         *
         * 总结：
         * 动态代理解决了静态代理的不足问题。
         * 不足：JDK动态代理要求target对象是一个接口的实现对象，假如target对象只是一个单独的对象，并没有实现任何接口，这个时候就无法满足。
         *  这个时候就要Cglib代理  需要 Cglib依赖的jar包 ，这个暂时不讲
         *
         */
    }
}
