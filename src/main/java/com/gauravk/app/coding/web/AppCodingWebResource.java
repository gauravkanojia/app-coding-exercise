/**
 * 
 */
package com.gauravk.app.coding.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.gauravk.app.coding.controller.CoreApiController;

/**
 * @author Gaurav Kanojia
 *
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = CoreApiController.class)
public class AppCodingWebResource {

  public static void main(String args[]) {
    SpringApplication.run(AppCodingWebResource.class);
  }
}
