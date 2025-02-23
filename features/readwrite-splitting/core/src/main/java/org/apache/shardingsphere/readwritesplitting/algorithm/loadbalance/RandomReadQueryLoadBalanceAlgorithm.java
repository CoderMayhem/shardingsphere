/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.readwritesplitting.algorithm.loadbalance;

import org.apache.shardingsphere.infra.context.transaction.TransactionConnectionContext;
import org.apache.shardingsphere.readwritesplitting.spi.ReadQueryLoadBalanceAlgorithm;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Random read query load-balance algorithm.
 */
public final class RandomReadQueryLoadBalanceAlgorithm implements ReadQueryLoadBalanceAlgorithm {
    
    @Override
    public String getDataSource(final String name, final String writeDataSourceName, final List<String> readDataSourceNames, final TransactionConnectionContext context) {
        if (context.isInTransaction()) {
            return writeDataSourceName;
        }
        return readDataSourceNames.get(ThreadLocalRandom.current().nextInt(readDataSourceNames.size()));
    }
    
    @Override
    public String getType() {
        return "RANDOM";
    }
}
