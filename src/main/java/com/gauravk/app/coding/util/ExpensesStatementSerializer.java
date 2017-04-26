/**
 * 
 */
package com.gauravk.app.coding.util;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gauravk.app.coding.model.CoreApiExpensesStatement;

/**
 * @author Gaurav Kanojia
 *
 */
public class ExpensesStatementSerializer extends StdSerializer<CoreApiExpensesStatement> {

  private static final long serialVersionUID = -3103541801707994857L;

  public ExpensesStatementSerializer() {
    this(null);
  }

  public ExpensesStatementSerializer(Class<CoreApiExpensesStatement> t) {
    super(t);
  }

  @Override
  public void serialize(CoreApiExpensesStatement expensesStmt, JsonGenerator jGen,
      SerializerProvider provider) throws IOException, JsonProcessingException {

    DecimalFormat decimalFormatter = new DecimalFormat("$#,##0.00;-$#,##0.00");
    jGen.writeStartObject();
    jGen.writeStringField("spent", decimalFormatter.format(expensesStmt.getSpent()));
    jGen.writeStringField("income", decimalFormatter.format(expensesStmt.getIncome()));
    jGen.writeEndObject();

  }
}
