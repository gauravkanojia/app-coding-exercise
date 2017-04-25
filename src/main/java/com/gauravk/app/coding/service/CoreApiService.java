package com.gauravk.app.coding.service;

import java.util.List;
import java.util.Map;

import com.gauravk.app.coding.model.CoreApiStatement;
import com.gauravk.app.coding.model.CoreApiTransactionsResponse;


/**
 * @author Gaurav Kanojia
 *
 */
public interface CoreApiService {

  /**
   * This method will be implemented for calculating the averages for provided transactions.
   * 
   * @param transactions - Provided transactions for which average needs to be computed.
   * @return Map<String, List<CoreApiStatement>> - A map containing the averages for each month from
   *         provided transactions.
   */
  Map<String, List<CoreApiStatement>> getAverages(CoreApiTransactionsResponse transactions);

  /**
   * This method will be implemented for filtering out the transactions that are related to donuts.
   * 
   * @param coreApiTransactionsResponse - All the transactions from CoreAPI.
   * @return CoreApiTransactionsResponse - This response will be filtered response which contains no
   *         donut transactions.
   */
  CoreApiTransactionsResponse ignoreDonuts(CoreApiTransactionsResponse coreApiTransactionsResponse);

}
