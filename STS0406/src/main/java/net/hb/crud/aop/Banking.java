package net.hb.crud.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Component 객체작업 한번더 진행 비권장  	
@Aspect
public class Banking {
	
	 //@Around("execution(public * net.hb.crud..*(..))")
	 @Around("execution(public * net.hb.crud.BoardDAO.dbDetail(..))")
	  public Object  timeLog(ProceedingJoinPoint pjp) throws Throwable{
	    long startTime = System.currentTimeMillis();   
	    long endTime=System.currentTimeMillis();
	    System.out.println(pjp.getSignature().getName()+"메소드 접속시간 : "+(endTime-startTime)+"초");
	    Object result=pjp.proceed();
	    return result;
	 }//end	

	 //@Before("execution(public * net.hb.crud..*(..))")
	 @Before("execution(public * net.hb.crud.BoardDAO.dbDetail(..))")
	 public void open( ){
	   System.out.println("open메소드 자동호출");
	   System.out.println("로그파일,보안인증,트랜잭션");
	 }//end
	  
	 //@After("execution(public * net.hb.crud..*(..))")
	 @After("execution(public * net.hb.crud.BoardDAO.dbDetail(..))")
	  public void close( ){
		System.out.println("close메소드 자동호출");
		System.out.println("메일보내기,폰메세지");
		System.out.println("==========================");
	  }//end
}//class END
