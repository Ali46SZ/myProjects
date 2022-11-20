package com.db.mimicdb.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLog {
    private static final Logger log = LogManager.getLogger(AspectLog.class);
    @Pointcut("execution(* com.db.mimicdb..*(..))")
    public void forAll() {

    }

    @Before(value = "forAll()" )
    public void beforeCall(JoinPoint joinPoint) {
            log.info( joinPoint.getSignature()  +" started");
         MethodSignature signature = (MethodSignature) joinPoint.getSignature();
         Object[] arr =joinPoint.getArgs();
         String[] parametersName = signature.getParameterNames();
        for (int i=0;i< arr.length;i++) {
            log.info( parametersName[i] +" = "+arr[i]);
        }
     }

    @After(value = "forAll()" )
    public void afterCall(JoinPoint joinPoint) {
        log.info( joinPoint.getSignature()  +"  finished");
    }

    @AfterThrowing(value = "forAll()", throwing="exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        log.info("Exception :"+exception);
    }
}


