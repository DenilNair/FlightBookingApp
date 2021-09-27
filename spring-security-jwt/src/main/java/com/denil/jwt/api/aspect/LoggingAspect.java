package com.denil.jwt.api.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.*;

@Aspect
@Component
public class LoggingAspect {
/*
	Logger log= LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut(value=" execution(* com.denil.jwt.api.*.*.*(..) ) ")
	public void myPointCut() {
		
	}
	
	@Pointcut(value="execution(org.springframework.web.filter.GenericFilterBean) )")
	public void excludeIttoResolveAopError() {}

	@Around("myPointCut() && !excludeIttoResolveAopError()")
	public Object appplicationLogger(ProceedingJoinPoint pj) throws Throwable {
		ObjectMapper mapper=new ObjectMapper();
		String methodName=pj.getSignature().getName();
		String classname=pj.getTarget().getClass().toString();
		Object[] input=pj.getArgs();
		log.info("Method Invoked "+classname+" : "+ methodName+"() "+" Request Arguments "+mapper.writeValueAsString(input));
		
		Object response=pj.proceed();
		log.info("Method Invoked "+classname+" : "+ methodName+"() "+" Response "+mapper.writeValueAsString(response));
		return response;
		
	}
	*/
}
