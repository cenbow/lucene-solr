package org.apache.lucene.search.similarities;

/**
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

import org.apache.lucene.search.Explanation;

/**
 * Computes lambda as {@code docFreq / numberOfDocuments}.
 * @lucene.experimental
 */
public class LambdaTTF extends Lambda {
  @Override
  public final float lambda(EasyStats stats) {
    return (float)stats.getDocFreq() / stats.getNumberOfDocuments();
  }
  
  @Override
  public final Explanation explain(EasyStats stats) {
    Explanation result = new Explanation();
    result.setDescription(getClass().getSimpleName() + ", computed from: ");
    result.setValue(lambda(stats));
    result.addDetail(
        new Explanation(stats.getDocFreq(), "docFreq"));
    result.addDetail(
        new Explanation(stats.getNumberOfDocuments(), "numberOfDocuments"));
    return result;
  }
}
