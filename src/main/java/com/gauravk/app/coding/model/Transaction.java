/**
 * 
 */
package com.gauravk.app.coding.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Gaurav Kanojia
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Serializable {

  private static final long serialVersionUID = 267029653687795319L;

  @JsonProperty(value = "transaction-id", defaultValue = "")
  @ApiModelProperty(value = "Opaque.", required = true, dataType = "String")
  private String transactionId;

  @JsonProperty(value = "account-id", defaultValue = "")
  @ApiModelProperty(value = "The bank account the transaction is associated with.", required = true,
      dataType = "String")
  private String accountId;

  @JsonProperty(value = "raw-merchant", defaultValue = "")
  @ApiModelProperty(value = "Un-prettified merchant string. Should never be displayed to the user.",
      required = true, dataType = "String")
  private String rawMerchant;

  @JsonProperty(value = "merchant", defaultValue = "")
  @ApiModelProperty(
      value = "Prettified merchant string. Should always be displayed to the user, even if they aren't using the app or their phone is off.",
      required = true, dataType = "String")
  private String merchant;

  @JsonProperty(value = "is-pending", defaultValue = "false")
  @ApiModelProperty(
      value = "Man don't even get me started. Basically, transactions show up as pending shortly after you swipe your card, and days later they are replaced by similar cleared (i.e. non-pending) transactions with different transaction IDs.",
      required = true, dataType = "boolean")
  private boolean isPending;

  @JsonProperty(value = "transaction-time", defaultValue = "1970-01-01T00:00:00.000Z")
  @ApiModelProperty(value = "Our best estimate of the time that you swiped your card.",
      required = true, dataType = "String")
  private String transactionTime;

  @JsonProperty(value = "amount", defaultValue = "0")
  @ApiModelProperty(
      value = "Negative amount = debit, positive amount = credit. Centocents. 20000 centocents = $2.",
      required = true, dataType = "String")
  private long amount;

  @JsonProperty(value = "previous-transaction-id", defaultValue = "")
  @ApiModelProperty(
      value = "When transactions clear, their IDs change for bad reasons. This is the ID the transaction had when it was pending, if any (not all transactions pend, and not all pending transactions are successfully matched with their cleared versions). Read-only",
      required = true, dataType = "String")
  private String previousTransactionId;

  @JsonProperty(value = "categorization", defaultValue = "")
  @ApiModelProperty(
      value = "A vaguely human-readable description of the category of transaction this is (from the aggregator, generally). If you're using one of our example accounts, this'll be \"Unknown\". Sorry.",
      required = true, dataType = "String")
  private String categorization;

  @JsonProperty(value = "memo-only-for-testing", defaultValue = "")
  @ApiModelProperty(value = "ONLY FOR TESTING", required = false, dataType = "String")
  private String memoOnlyForTesting;

  @JsonProperty(value = "payee-name-only-for-testing", defaultValue = "")
  @ApiModelProperty(value = "ONLY FOR TESTING", required = false, dataType = "String")
  private String payeeNameOnlyForTesting;

  @JsonProperty(value = "clear-date", defaultValue = "0")
  @ApiModelProperty(
      value = "Millisecond timestamp of when we think the transaction cleared. May be in the future. If the transaction is pending, has undefined behavior.",
      required = true, dataType = "String")
  private long clearDate;

  /**
   * @return the transactionId
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * @param transactionId the transactionId to set
   */
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * @return the accountId
   */
  public String getAccountId() {
    return accountId;
  }

  /**
   * @param accountId the accountId to set
   */
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  /**
   * @return the rawMerchant
   */
  public String getRawMerchant() {
    return rawMerchant;
  }

  /**
   * @param rawMerchant the rawMerchant to set
   */
  public void setRawMerchant(String rawMerchant) {
    this.rawMerchant = rawMerchant;
  }

  /**
   * @return the merchant
   */
  public String getMerchant() {
    return merchant;
  }

  /**
   * @param merchant the merchant to set
   */
  public void setMerchant(String merchant) {
    this.merchant = merchant;
  }

  /**
   * @return the isPending
   */
  public boolean isPending() {
    return isPending;
  }

  /**
   * @param isPending the isPending to set
   */
  public void setPending(boolean isPending) {
    this.isPending = isPending;
  }

  /**
   * @return the transactionTime
   */
  public String getTransactionTime() {
    return transactionTime;
  }

  /**
   * @param transactionTime the transactionTime to set
   */
  public void setTransactionTime(String transactionTime) {
    this.transactionTime = transactionTime;
  }

  /**
   * @return the amount
   */
  public long getAmount() {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(long amount) {
    this.amount = amount;
  }

  /**
   * @return the previousTransactionId
   */
  public String getPreviousTransactionId() {
    return previousTransactionId;
  }

  /**
   * @param previousTransactionId the previousTransactionId to set
   */
  public void setPreviousTransactionId(String previousTransactionId) {
    this.previousTransactionId = previousTransactionId;
  }

  /**
   * @return the categorization
   */
  public String getCategorization() {
    return categorization;
  }

  /**
   * @param categorization the categorization to set
   */
  public void setCategorization(String categorization) {
    this.categorization = categorization;
  }

  /**
   * @return the memoOnlyForTesting
   */
  public String getMemoOnlyForTesting() {
    return memoOnlyForTesting;
  }

  /**
   * @param memoOnlyForTesting the memoOnlyForTesting to set
   */
  public void setMemoOnlyForTesting(String memoOnlyForTesting) {
    this.memoOnlyForTesting = memoOnlyForTesting;
  }

  /**
   * @return the payeeNameOnlyForTesting
   */
  public String getPayeeNameOnlyForTesting() {
    return payeeNameOnlyForTesting;
  }

  /**
   * @param payeeNameOnlyForTesting the payeeNameOnlyForTesting to set
   */
  public void setPayeeNameOnlyForTesting(String payeeNameOnlyForTesting) {
    this.payeeNameOnlyForTesting = payeeNameOnlyForTesting;
  }

  /**
   * @return the clearDate
   */
  public long getClearDate() {
    return clearDate;
  }

  /**
   * @param clearDate the clearDate to set
   */
  public void setClearDate(long clearDate) {
    this.clearDate = clearDate;
  }

  @Override
  public String toString() {
    return "Transaction [transactionId=" + transactionId + ", accountId=" + accountId
        + ", rawMerchant=" + rawMerchant + ", merchant=" + merchant + ", isPending=" + isPending
        + ", transactionTime=" + transactionTime + ", amount=" + amount + ", previousTransactionId="
        + previousTransactionId + ", categorization=" + categorization + ", memoOnlyForTesting="
        + memoOnlyForTesting + ", payeeNameOnlyForTesting=" + payeeNameOnlyForTesting
        + ", clearDate=" + clearDate + "]";
  }
}
