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
   * @param transactions
   * @return
   */
  Map<String, List<CoreApiStatement>> calculateAverages(CoreApiTransactionsResponse transactions);

}
