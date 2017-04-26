/**
 * 
 */
package com.gauravk.app.coding.controller;

import java.util.Arrays;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gauravk.app.coding.model.CoreApiRequestBody;
import com.gauravk.app.coding.model.CoreApiRequestCommonArguments;
import com.gauravk.app.coding.model.CoreApiTransactionsResponse;
import com.gauravk.app.coding.service.CoreApiService;
import com.gauravk.app.coding.service.CoreApiServiceImpl;

/**
 * @author Gaurav Kanojia
 *
 */
@RestController
@RequestMapping(value = "/coreapi")
public class CoreApiController {

  private static final Logger logger = LoggerFactory.getLogger(CoreApiController.class);

  private final String uri = "https://2016.api.levelmoney.com/api/v2/core/get-all-transactions";

  private RestTemplate restTemplate;

  private CoreApiService service;

  /**
   * This method is used for testing if the application is up or not.
   * 
   * @return A string with success message.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/test")
  public String getTestApplication() {
    logger.info("Inside Test Application");
    return String.format("Your Spring Boot Application is up! Try running other methods. Check README for more details.");
  }

  /**
   * This method is used for consuming the CoreAPI in the background and then display all the
   * transactions when invoked.
   * 
   * @return All the transactions from CoreAPI.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/getAllTransactions")
  public CoreApiTransactionsResponse getAllTransactions() {

    logger.info("Inside getAllTransactions");
    HttpEntity<CoreApiRequestBody> entity = new HttpEntity<CoreApiRequestBody>(getRequestBody(), getRequestHeaders());
    restTemplate = new RestTemplate();
    ResponseEntity<CoreApiTransactionsResponse> response = null;

    try {
      response = restTemplate.exchange(uri, HttpMethod.POST, entity, CoreApiTransactionsResponse.class);
    } catch (RestClientException restClientEx) {
      logger.error("Rest Client Exception occured while getting all transactions: {}", restClientEx.fillInStackTrace());
    }
    logger.info("Leaving getAllTransactions");

    return response.getBody();
  }

  /**
   * This method is used for get the monthly averages from the transactions.
   * 
   * @return A string denoting expenses and income averages for each month provided in the
   *         transactions.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/getAverages")
  public String getAverages() {

    logger.info("Inside getAverages");
    HttpEntity<CoreApiRequestBody> entity = new HttpEntity<CoreApiRequestBody>(getRequestBody(), getRequestHeaders());
    restTemplate = new RestTemplate();
    ResponseEntity<CoreApiTransactionsResponse> response = null;

    try {
      response = restTemplate.exchange(uri, HttpMethod.POST, entity, CoreApiTransactionsResponse.class);
    } catch (RestClientException restClientEx) {
      logger.error("Rest Client Exception occured while getting averages: {}", restClientEx.fillInStackTrace());
    }

    CoreApiTransactionsResponse allTransactions = response.getBody();
    service = new CoreApiServiceImpl();
    logger.info("Leaving getAverages");

    return service.getAverages(allTransactions);

  }

  @RequestMapping(method = RequestMethod.GET, value = "/ignoreDonuts")
  public CoreApiTransactionsResponse getIgnoreDonuts() {

    logger.info("Inside ignoreDonuts");
    HttpEntity<CoreApiRequestBody> entity = new HttpEntity<CoreApiRequestBody>(getRequestBody(), getRequestHeaders());
    restTemplate = new RestTemplate();
    ResponseEntity<CoreApiTransactionsResponse> response = null;

    try {
      response = restTemplate.exchange(uri, HttpMethod.POST, entity, CoreApiTransactionsResponse.class);
    } catch (RestClientException restClientEx) {
      logger.error("Rest Client Exception occured while ignoring donuts: {}", restClientEx.fillInStackTrace());
    }

    CoreApiTransactionsResponse allTransactions = response.getBody();
    service = new CoreApiServiceImpl();
    logger.info("Leaving ignoreDonuts");

    return service.ignoreDonuts(allTransactions);
  }

  /**
   * This method returns a {@link RestTemplate} when called.
   * 
   * @param restTemplateBuilder
   * @return RestTemplate
   */
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

  /**
   * This method is ran when Spring-Boot application is brought up and prints all the transactions
   * onto the console which are consumed from CoreAPI. Kept this for testing purposes.
   * 
   * @param restTemplate
   * @return CommandLineRunner
   * @throws Exception
   */
  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
    return args -> {

      HttpEntity<CoreApiRequestBody> entity = new HttpEntity<CoreApiRequestBody>(getRequestBody(), getRequestHeaders());
      ResponseEntity<CoreApiTransactionsResponse> response = null;

      try {
        response = restTemplate.exchange(uri, HttpMethod.POST, entity, CoreApiTransactionsResponse.class);
      } catch (RestClientException restClientEx) {
        logger.error("Rest Client Exception occured while ignoring donuts: {}", restClientEx.fillInStackTrace());
      }

      logger.info(response.getBody().toString());
    };
  }

  /**
   * This method is used for setting the header values for consuming the Core API.
   * 
   * @return HttpHeaders - created HttpHeaders
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
}
