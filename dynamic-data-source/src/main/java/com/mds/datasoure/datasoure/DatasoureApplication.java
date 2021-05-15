package com.mds.datasoure.datasoure;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 13557
 */
@SpringBootApplication
public class DatasoureApplication {

    public static void main(String[] args) {
        //BeanFactory
        DefaultListableBeanFactory
        SpringApplication.run(DatasoureApplication.class, args);
    }

}
