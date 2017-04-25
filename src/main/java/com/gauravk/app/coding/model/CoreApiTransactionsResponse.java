/**
 * 
 */
package com.gauravk.app.coding.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Gaurav Kanojia
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreApiTransactionsResponse implements Serializable {

  private static final long serialVersionUID = 8502656030616503394L;

  @JsonProperty(value = "error", defaultValue = "no-error")
  private String error;

  @JsonProperty(value = "transactions", defaultValue = "")
  private List<Transaction> transactions;


  /**
   * @param error
   * @param transactions
   */
  public CoreApiTransactionsResponse() {
    this.error = "no-error";
    this.transactions = new ArrayList<Transaction>();
  }

  /**
   * @return the transactions
   */
  public List<Transaction> getTransactions() {

    if (null == transactions) {
      transactions = new ArrayList<Transaction>();
    }
    return transactions;
  }

  /**
   * @param transactions the transactions to set
   */
  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  @Override
  public String toString() {
    return "CoreApiTransactionsResponse [error=" + error + ", transactions=" + transactions + "]";
  }

}
