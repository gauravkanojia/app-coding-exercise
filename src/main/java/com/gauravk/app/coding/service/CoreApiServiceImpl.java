/**
 * 
 */
package com.gauravk.app.coding.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gauravk.app.coding.controller.CoreApiController;
import com.gauravk.app.coding.model.CoreApiExpensesStatement;
import com.gauravk.app.coding.model.CoreApiTransactionsResponse;
import com.gauravk.app.coding.model.Transaction;
import com.gauravk.app.coding.util.ExpensesStatementSerializer;

/**
 * @author Gaurav Kanojia
 *
 */
public class CoreApiServiceImpl implements CoreApiService {

  private static final Logger logger = LoggerFactory.getLogger(CoreApiController.class);

  /**
   * This is implementation for calculating the averages for provided transactions.
   * 
   * @param transactions - Provided transactions for which average needs to be computed.
   * @return Map<String, List<CoreApiStatement>> - A map containing the averages for each month from
   *         provided transactions.
   */
  @Override
  public String getAverages(CoreApiTransactionsResponse transactions) {

    Map<String, List<CoreApiExpensesStatement>> monthlyStatementMap =
        new HashMap<String, List<CoreApiExpensesStatement>>(transactions.getTransactions().size());
    Map<String, List<CoreApiExpensesStatement>> averageMonthlyStatementMap =
        new HashMap<String, List<CoreApiExpensesStatement>>(monthlyStatementMap.size());

    for (Transaction transaction : transactions.getTransactions()) {

      // Get the transaction time to extract year and month from it.
      String transactionTime = transaction.getTransactionTime();
      String yearMonth = transactionTime.substring(0, 7);

      // Get the transaction amount and set it to monthly statement as expense or income.
      CoreApiExpensesStatement monthlyStatement = new CoreApiExpensesStatement();
      long transactionAmount = transaction.getAmount();

      // If transaction amount is greater than 0, then it's income, else expense.
      if (transactionAmount > 0) {
        monthlyStatement.setIncome(transactionAmount);
      } else
        monthlyStatement.setSpent(transactionAmount);

      // Make first pass in which the monthly statements are stored as per year+month key,
      // with expenses and income for a month in a single statement.
      if (monthlyStatementMap.containsKey(yearMonth)) {
        monthlyStatementMap.get(yearMonth).add(monthlyStatement);
      } else {
        List<CoreApiExpensesStatement> statements = new ArrayList<CoreApiExpensesStatement>();
        statements.add(monthlyStatement);
        monthlyStatementMap.put(yearMonth, statements);
      }

      // Make second pass in which we calculate the monthly sums for the expenses and income
      for (Map.Entry<String, List<CoreApiExpensesStatement>> keyEntry : monthlyStatementMap.entrySet()) {

        List<CoreApiExpensesStatement> monthlyStatments = calculateMonthlySum(keyEntry.getValue());
        averageMonthlyStatementMap.put(keyEntry.getKey(), monthlyStatments);
      }

      List<CoreApiExpensesStatement> averageOfAllStatements = new ArrayList<CoreApiExpensesStatement>();
      long totalSpent = 0L;
      long totalEarned = 0L;
      int numberOfStatements = 0;
      
      // We got the monthly sum from above step, now calculating averages for all the months
      for (Map.Entry<String, List<CoreApiExpensesStatement>> keyEntry : averageMonthlyStatementMap.entrySet()) {

        List<CoreApiExpensesStatement> monthlySumStatements = keyEntry.getValue();
        for (CoreApiExpensesStatement monthlySumStatement : monthlySumStatements) {
          totalEarned += monthlySumStatement.getIncome();
          totalSpent += monthlySumStatement.getSpent();
          numberOfStatements++;
        }
      }

      CoreApiExpensesStatement averageOfAllExpenseIncome = 
          new CoreApiExpensesStatement(totalSpent / numberOfStatements, totalEarned / numberOfStatements);
      averageOfAllStatements.add(averageOfAllExpenseIncome);
      averageMonthlyStatementMap.put("average", averageOfAllStatements);

    }

    // Setting up a new map for printing out the results.
    Map<String, CoreApiExpensesStatement> mapForPrint =
        new HashMap<String, CoreApiExpensesStatement>(averageMonthlyStatementMap.size());
    for (Map.Entry<String, List<CoreApiExpensesStatement>> keyEntry : averageMonthlyStatementMap.entrySet()) {

      List<CoreApiExpensesStatement> avgExpense = keyEntry.getValue();
      for (CoreApiExpensesStatement avgStatement : avgExpense) {
        mapForPrint.put(keyEntry.getKey(), avgStatement);
      }
    }

    // For printing the averages in sorted manner.
    Map<String, CoreApiExpensesStatement> monthlyAveragesTreeMap =
        new TreeMap<String, CoreApiExpensesStatement>(mapForPrint);
    logger.info("Monthly sums and average of total: {}", monthlyAveragesTreeMap);


    return hashMapToJson(monthlyAveragesTreeMap);
  }

  @Override
  public CoreApiTransactionsResponse ignoreDonuts(CoreApiTransactionsResponse coreApiTransactionsResponse) {
    
    CoreApiTransactionsResponse filteredResponse = new CoreApiTransactionsResponse();
    List<Transaction> transactionsList = new ArrayList<Transaction>();

    // Get merchant for all transactions and exclude the transaction if it's related to donuts in any way.
    for (Transaction transaction : coreApiTransactionsResponse.getTransactions()) {
      String merchant = transaction.getMerchant();
      if (!(StringUtils.contains(merchant, "Dunkin") || StringUtils.contains(merchant, "Donuts"))) {
        transactionsList.add(transaction);
      }
    }
    filteredResponse.setTransactions(transactionsList);
    logger.info("Filtered transactions: {}", filteredResponse);

    return filteredResponse;
  }

  /**
   * This method calculates average for provided monthly statements.
   * 
   * @param statements - Statements containing expenses and income for each month.
   * @return List<CoreApiStatement> - A list containing average amounts for each month.
   */
  private List<CoreApiExpensesStatement> calculateMonthlySum(List<CoreApiExpensesStatement> statements) {
    long spentSum = 0L;
    long incomeSum = 0L;
    List<CoreApiExpensesStatement> allMonthlySums = new ArrayList<CoreApiExpensesStatement>();

    for (CoreApiExpensesStatement statement : statements) {
      // Getting absolute value to be shown as expenses, without negative sign
      spentSum += Math.abs(statement.getSpent());
      incomeSum += statement.getIncome();
    }

    CoreApiExpensesStatement monthlyStatementSum = new CoreApiExpensesStatement();
    monthlyStatementSum.setIncome(centocentsToDollars(incomeSum));
    monthlyStatementSum.setSpent(centocentsToDollars(spentSum));
    allMonthlySums.add(monthlyStatementSum);

    return allMonthlySums;
  }

  /**
   * This method is used for converting centocents to dollars.
   * 
   * @param centocents - Value to be converted.
   * @return long - Converted amount in dollars without the currency declaration.
   */
  private long centocentsToDollars(long centocents) {
    return centocents / 10000L;
  }

  private String hashMapToJson(Map<String, CoreApiExpensesStatement> averages) {

    String json = StringUtils.EMPTY;
    ObjectMapper objMapper = new ObjectMapper();

    // Custom serializer for currencies
    SimpleModule module = new SimpleModule();
    module.addSerializer(CoreApiExpensesStatement.class, new ExpensesStatementSerializer());
    objMapper.registerModule(module);

    try {
      json = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(averages);
    } catch (JsonProcessingException jsonProcessEx) {
      logger.error("Error while processing Map to JSON: {}", jsonProcessEx.fillInStackTrace());
    }

    return json;
  }
}
