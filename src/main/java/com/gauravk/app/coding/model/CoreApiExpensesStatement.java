/**
 * 
 */
package com.gauravk.app.coding.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Gaurav Kanojia
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreApiExpensesStatement implements Serializable {

  private static final long serialVersionUID = 4879745013754030623L;

  public CoreApiExpensesStatement() {
    super();
  }

  public CoreApiExpensesStatement(long spent, long income) {
    super();
    this.spent = spent;
    this.income = income;
  }

  @JsonProperty(value = "spent", defaultValue = "0")
  private long spent;

  @JsonProperty(value = "income", defaultValue = "0")
  private long income;

  /**
   * @return the spent
   */
  public long getSpent() {
    return spent;
  }

  /**
   * @param spent the spent to set
   */
  public void setSpent(long spent) {
    this.spent = spent;
  }

  /**
   * @return the income
   */
  public long getIncome() {
    return income;
  }

  /**
   * @param income the income to set
   */
  public void setIncome(long income) {
    this.income = income;
  }

  @Override
  public String toString() {
    // Formatter to print Amounts with $ sign and decimal places
    DecimalFormat decimalFormatter = new DecimalFormat("$#,##0.00;-$#,##0.00");
    return " [spent=\"" + decimalFormatter.format(spent) + "\", income=\"" + decimalFormatter.format(income) + "\"]";
  }
}
