package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ApplicationUtil implements ApplicationContextAware {

  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context =applicationContext;
  }

  public static  <T>T getBeanObject(String className) throws Exception{
    log.info("通过类名 获取 ioc容器对象 className:{}",className);
    T bean=null;
    try {
      Class clazz= Class.forName(className);
      bean = (T)context.getBean(clazz);
    }catch (Exception ex){
      log.error("获取IOC容器对象失败");
      throw new Exception("获取IOC容器对象失败");
    }
    return bean;
  }

  //获取SpringBean
  public static Object getSpringBean(Class clazz){
    return context.getBean(clazz);
  }


}
