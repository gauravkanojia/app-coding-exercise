/**
 * 
 */
package com.gauravk.app.coding.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gauravk.app.coding.model.CoreApiRequestBody;
import com.gauravk.app.coding.model.CoreApiRequestCommonArguments;
import com.gauravk.app.coding.model.CoreApiTransactionsResponse;
import com.gauravk.app.coding.model.Transaction;

/**
 * @author Gaurav Kanojia
 *
 */
@RestController
@RequestMapping(value = "/coreapi")
public class CoreApiController {

  private static final Logger logger = LoggerFactory.getLogger(CoreApiController.class);

  private final String uri = "https://2016.api.levelmoney.com/api/v2/core/get-all-transactions";

  private RestTemplate restTemplate = new RestTemplate();

  @RequestMapping(method = RequestMethod.GET, value = "/greeting")
  public String getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    logger.info("Inside Greeting");
    return String.format("Greetings from Spring Boot! This is %s.", name);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/getAllTransactions")
  public CoreApiTransactionsResponse getAllTransactions() {

    logger.info("Inside getAllTransactions");

    HttpEntity<CoreApiRequestBody> entity =
        new HttpEntity<CoreApiRequestBody>(getRequestBody(), getRequestHeaders());
    ResponseEntity<CoreApiTransactionsResponse> response =
        restTemplate.exchange(uri, HttpMethod.POST, entity, CoreApiTransactionsResponse.class);
    logger.info("Leaving getAllTransactions");

    return response.getBody();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/getAverages")
  public CoreApiTransactionsResponse getAverages(
      @RequestParam(value = "name", defaultValue = "World") String name) {
    logger.info("Inside getAverages");

    HttpEntity<CoreApiRequestBody> entity =
        new HttpEntity<CoreApiRequestBody>(getRequestBody(), getRequestHeaders());
    ResponseEntity<CoreApiTransactionsResponse> response =
        restTemplate.exchange(uri, HttpMethod.POST, entity, CoreApiTransactionsResponse.class);
    CoreApiTransactionsResponse allTransactions = response.getBody();
    logger.info("Leaving getAverages");

    return calculateAverages(allTransactions);
  }


  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

  /**
   * This method is ran when Spring-Boot application is brought up and print all the transactions
   * onto the console.
   * 
   * @param restTemplate
   * @return
   * @throws Exception
   */
  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
    return args -> {

      HttpEntity<CoreApiRequestBody> entity =
          new HttpEntity<CoreApiRequestBody>(getRequestBody(), getRequestHeaders());
      ResponseEntity<CoreApiTransactionsResponse> response =
          restTemplate.exchange(uri, HttpMethod.POST, entity, CoreApiTransactionsResponse.class);

      logger.info(response.getBody().toString());
    };
  }

  /**
   * This method is used for setting the header values for consuming the Core API.
   * 
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
   * 
   * @return - CoreApiRequestBody
   */
  private CoreApiRequestBody getRequestBody() {

    CoreApiRequestBody requestBody = new CoreApiRequestBody();
    CoreApiRequestCommonArguments arguments = new CoreApiRequestCommonArguments();

    arguments.setUid(1110590645);
    arguments.setToken("42B2447FC75392EF9B91F70FF4DD359C");
    arguments.setApiToken("AppTokenForInterview");
    arguments.setJsonStrictMode(true);
    arguments.setJsonVerboseResponse(true);
    requestBody.setArgs(arguments);

    return requestBody;
  }

  private CoreApiTransactionsResponse calculateAverages(CoreApiTransactionsResponse transactions) {
    CoreApiTransactionsResponse averages = new CoreApiTransactionsResponse();

    for (Transaction transaction : transactions.getTransactions()) {

    }

    return averages;
  }
}
