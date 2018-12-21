package org.father.API;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * Hello world!
 *
 */
//@EnableJpaRepositories
@SpringBootApplication()
public class App
{
    public static void main( String[] args )
    {
    	
    	//SpringApplication.run(App.class, args);方法一：最普通的方法，用来启动springboot应用
    	SpringApplication sal=new SpringApplication(App.class);//方法二：设置Banner的方法
    	sal.setBannerMode(Banner.Mode.CONSOLE);
    	sal.run(args);
    }
}
