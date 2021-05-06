//package com.redbird.restaurant.aspects;
//
//import com.redbird.lesson24.notifiers.NotifyService;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Slf4j
//@Component
//@org.aspectj.lang.annotation.Aspect
//public class Aspect {
//
//    private final NotifyService notifyService;
//
//    public Aspect(NotifyService notifyService) {
//        this.notifyService = notifyService;
//    }
//
//    @Before("allServiceMethods()")
//    public void argslogger(JoinPoint joinPoint) {
//
//        log.info("Parameters: " + Arrays.toString(joinPoint.getArgs()));
//    }
//    @Around("allServiceMethods()")
//    public Object timeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object res = proceedingJoinPoint.proceed();
//        long finishTime = System.currentTimeMillis();
//
//        log.info("Method " +
//                proceedingJoinPoint.getSignature().getName() +
//                " worked for " +
//                (finishTime-startTime) +
//                " ms in " +
//                proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName());
//        return res;
//    }
//
//    @AfterReturning(
//            pointcut = "saveOrUpdate()",
//            returning = "retVal"
//    )
//    public void mailSender(Object retVal) {
//        notifyService.send(retVal.toString());
//    }
//
//    @Pointcut("within(com.redbird.lesson24.services.*)")
//    public void allServiceMethods() { }
//    @Pointcut("allServiceMethods() && execution(* *.saveOrUpdate(..))")
//    public void saveOrUpdate() { }
//}
