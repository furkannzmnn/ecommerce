package com.base.ecommerce.aspect;

import com.base.ecommerce.annotation.Modifiable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Aspect
@Component
public class ModifiedAspect {
    // create aspect for modified method


    @Around("@annotation(com.base.ecommerce.annotation.Modifiable)")
    public Object modifiedMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();

        int index = -1;

        for (Annotation[] matrix : annotations) {
            index++;
            for (Annotation annotation : matrix) {
                if (annotation instanceof Modifiable) {
                    Object object = joinPoint.getArgs()[index];
                    if (object instanceof com.base.ecommerce.model.base.Modifiable) {
                        com.base.ecommerce.model.base.Modifiable modifiable = (com.base.ecommerce.model.base.Modifiable) object;
                        Long userId = authentication.getPrincipal() instanceof Long ? (Long) authentication.getPrincipal() : null;
                        modifiable.setModifiedId(userId);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}