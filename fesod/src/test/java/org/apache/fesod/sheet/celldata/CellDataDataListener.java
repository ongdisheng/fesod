/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.fesod.sheet.celldata;

import com.alibaba.fastjson2.JSON;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.fesod.sheet.context.AnalysisContext;
import org.apache.fesod.sheet.event.AnalysisEventListener;
import org.apache.fesod.sheet.support.ExcelTypeEnum;
import org.apache.fesod.sheet.util.DateUtils;
import org.junit.jupiter.api.Assertions;

/**
 *
 */
@Slf4j
public class CellDataDataListener extends AnalysisEventListener<CellDataReadData> {

    List<CellDataReadData> list = new ArrayList<>();

    @Override
    public void invoke(CellDataReadData data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        Assertions.assertEquals(1, list.size());
        CellDataReadData cellDataData = list.get(0);

        // Verify util.Date preserves seconds
        Assertions.assertEquals("2020-01-01 01:01:01", cellDataData.getDate().getData());

        // Verify sql.Date contains date only
        Assertions.assertEquals("2020-01-01", cellDataData.getSqlDate().getData());

        // Verify sql.Timestamp preserves milliseconds
        Assertions.assertEquals(
                "2020-01-01 01:01:01.789", cellDataData.getSqlTimestamp().getData());

        // Verify sql.Time contains time only
        Assertions.assertEquals("01:01:01", cellDataData.getSqlTime().getData());

        // Verify sql.Timestamp read as Date type preserves milliseconds
        Assertions.assertEquals(
                "2020-01-01 01:01:01.789",
                DateUtils.format(cellDataData.getSqlTimestampAsDate(), "yyyy-MM-dd HH:mm:ss.SSS"));

        Assertions.assertEquals(2L, (long) cellDataData.getInteger1().getData());
        Assertions.assertEquals(2L, (long) cellDataData.getInteger2());

        if (context.readWorkbookHolder().getExcelType() != ExcelTypeEnum.CSV) {
            Assertions.assertEquals(
                    "B2+C2", cellDataData.getFormulaValue().getFormulaData().getFormulaValue());
        } else {
            Assertions.assertNull(cellDataData.getFormulaValue().getData());
        }

        log.debug("First row:{}", JSON.toJSONString(list.get(0)));
    }
}
