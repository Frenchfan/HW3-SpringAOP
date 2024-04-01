package com.sumkin.hw3.aspect;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LoggingHandler {

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {
    }

    @Pointcut("execution(* com.sumkin.hw3.services.UserService.create*(..))")
    public void createService() {
    }

    @Before("within(@org.springframework.stereotype.Service *)")
    public void logPubicAPI(JoinPoint joinPoint) {
        log.info("**********************************");
        log.info("Entering in Method : {} ", joinPoint.getSignature().getName());
        log.info("Class Name : {}", joinPoint.getSignature().getDeclaringTypeName());
        log.info("Arguments : {}", Arrays.toString(joinPoint.getArgs()));
        log.info("Target class :{} ", joinPoint.getTarget().getClass().getName());
        log.info("**********************************");
    }

    @AfterThrowing(pointcut = "service()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.info("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        log.info("Cause : " + exception.getCause());
    }

    @AfterReturning(pointcut = "createService()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        log.info("Exiting in Method : " + joinPoint.getSignature().getName());
        log.info("New Object created: " + joinPoint.getSignature().getDeclaringTypeName() + " " + result);
        log.info("Method Return value : " + returnValue);
    }

    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}

