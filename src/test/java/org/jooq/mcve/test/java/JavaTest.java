package org.jooq.mcve.test.java;

import org.jooq.mcve.java.tables.records.TestRecord;
import org.junit.Test;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;

import static org.jooq.mcve.java.Tables.TEST;
import static org.junit.Assert.assertEquals;

public class JavaTest extends AbstractTest {

   @Test
   public void mcveTest() {
      Field<Integer> field_test = DSL.greatest(
         TEST.ID,
         TEST.VALUE
      );
      String sql = ctx.select(field_test)
                        .from(TEST)
                        .getSQL(ParamType.NAMED_OR_INLINED);
      System.out.println(sql);
      Result<Record1<Integer>> res = ctx.resultQuery(sql).coerce(field_test).fetch();

      System.out.println(res);

      for( Record currRec : res )
      {
         // this does not work
         System.out.println(currRec.get(field_test));
      }
   }
}
