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

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.fesod.sheet.annotation.format.DateTimeFormat;
import org.apache.fesod.sheet.metadata.data.WriteCellData;

/**
 *
 */
@Getter
@Setter
@EqualsAndHashCode
public class CellDataWriteData {
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private WriteCellData<Date> date;

    @DateTimeFormat("yyyy-MM-dd")
    private WriteCellData<Date> sqlDate;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss.SSS")
    private WriteCellData<Date> sqlTimestamp;

    @DateTimeFormat("HH:mm:ss")
    private WriteCellData<Date> sqlTime;

    // Write as plain Date to test DateNumberConverter
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss.SSS")
    private Date sqlTimestampAsDate;

    private WriteCellData<Integer> integer1;
    private Integer integer2;
    private WriteCellData<?> formulaValue;
}
