/**
 * 
 */
package com.gauravk.app.coding.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gauravk.app.coding.model.CoreApiRequestBody;
import com.gauravk.app.coding.model.CoreApiRequestCommonArguments;
import com.gauravk.app.coding.model.CoreApiTransactionsResponse;

/**
 * @author Gaurav Kanojia
 *
 */
@SpringBootApplication
public class AppCodingWebResource {

  private static final Logger logger = LoggerFactory.getLogger(AppCodingWebResource.class);

  private static Properties properties;
  
  public AppCodingWebResource() {
    loadPropertiesFromFile();
  }

  public static void main(String args[]) {
    SpringApplication.run(AppCodingWebResource.class);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
    return args -> {

      final String uri = "https://2016.api.levelmoney.com/api/v2/core/get-all-transactions";

      HttpEntity<CoreApiRequestBody> entity = new HttpEntity<CoreApiRequestBody>(getRequestBody(), getRequestHeaders());
      ResponseEntity<CoreApiTransactionsResponse> response = 
          restTemplate.exchange(uri, HttpMethod.POST, entity, CoreApiTransactionsResponse.class);

      logger.info(response.getBody().toString());
    };
  }

  /**
   * This method is used for setting the header values for consuming the Core API.
   * @return - HttpHeaders
   */
  private HttpHeaders getRequestHeaders() {

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);

    return headers;
  }

  /**
   * This method is used for setting the Request Body for consuming the Core API.
   * @return - CoreApiRequestBody
   */
  private CoreApiRequestBody getRequestBody() {

    CoreApiRequestBody requestBody = new CoreApiRequestBody();
    CoreApiRequestCommonArguments arguments = new CoreApiRequestCommonArguments();

    arguments.setUid(Integer.parseInt(properties.getProperty("core.appId")));
    arguments.setToken(properties.getProperty("core.appSecret"));
    arguments.setApiToken(properties.getProperty("core.appToken"));
    arguments.setJsonStrictMode(true);
    arguments.setJsonVerboseResponse(true);
    requestBody.setArgs(arguments);

    return requestBody;
  }

  /**
   * This method is used for loading properties from property file.
   * @return - Properties
   */
  private Properties loadPropertiesFromFile() {
    
    if (null == properties) {
      
      try (FileInputStream inputStream = new FileInputStream(
            getClass().getClassLoader().getResource("application.properties").getPath())) {
        
        properties = new Properties();
        properties.load(inputStream);
      
      } catch (IOException ioEx) {
        logger.error("Error while loading properties file: {}", ioEx.fillInStackTrace());
      }
    }

    return properties;
  }
}
