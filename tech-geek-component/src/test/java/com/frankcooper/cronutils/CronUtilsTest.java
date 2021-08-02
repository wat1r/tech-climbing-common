package com.frankcooper.cronutils;

import com.cronutils.model.SingleCron;
import com.cronutils.model.field.expression.Every;
import com.cronutils.model.field.value.IntegerFieldValue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/30 14:56
 * @description:
 */
public class CronUtilsTest {



    @Test
    public void testDescribeEveryXTimeUnits() {
//        final int time = 3;
//        final Every expression = new Every(new IntegerFieldValue(time));
//        assertEquals(String.format("every %s seconds", time), descriptor.describe(
//                new SingleCron(mockDefinition, Collections.singletonList(new CronField(CronFieldName.SECOND, expression, nullFieldConstraints)))
//                )
//        );
//        assertEquals(String.format("every %s minutes", time), descriptor.describe(
//                new SingleCron(mockDefinition, Collections.singletonList(new CronField(CronFieldName.MINUTE, expression, nullFieldConstraints)))
//                )
//        );
//        final List<CronField> params = new ArrayList<>();
//        params.add(new CronField(CronFieldName.HOUR, expression, nullFieldConstraints));
//        params.add(new CronField(CronFieldName.MINUTE, new On(new IntegerFieldValue(time)), nullFieldConstraints));
//        assertEquals(String.format("every %s hours at minute %s", time, time), descriptor.describe(new SingleCron(mockDefinition, params)));
    }



}
