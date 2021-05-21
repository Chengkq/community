package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {

    /**
     * 可以统一定义切点
     * 第一个 * 表示要拦截的目标方法返回值任意（也可以明确指定返回值类型
     * 第二个 * 表示包中的任意类（也可以明确指定类
     * 第三个 * 表示类中的任意方法
     * 最后面的两个点表示方法参数任意，个数任意，类型任意
     */
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut() {

    }

    /**
     * 前置通知
     * @Before 注解表示这是一个前置通知，即在目标方法执行之前执行，注解中，需要填入切点
     */
    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }
    /**
     * 后置通知
     * @After 表示这是一个后置通知，即在目标方法执行之后执行
     */
    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }

    /**
     * @AfterReturning 表示这是一个返回通知，即有目标方法有返回值的时候才会触发，该注解中的 returning 属性表示目标方法返回值的变量名，这个需要和参数一一对应吗，注意：目标方法的返回值类型要和这里方法返回值参数的类型一致，否则拦截不到，如果想拦截所有（包括返回值为 void），则方法返回值参数可以为 Object
     */
    @AfterReturning("pointcut()")
    public void afterRetuning() {
        System.out.println("afterRetuning");
    }
    /**
     * 异常通知
     */
    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }
    /**
     * 环绕通知
     * 环绕通知是集大成者，可以用环绕通知实现上面的四个通知，这个方法的核心有点类似于在这里通过反射执行方法
     * @return 注意这里的返回值类型最好是 Object ，和拦截到的方法相匹配
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        Object obj = joinPoint.proceed();
        System.out.println("around after");
        return obj;
    }

}
