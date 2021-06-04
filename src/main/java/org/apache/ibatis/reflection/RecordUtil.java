/*
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.apache.ibatis.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Utility class for java.lang.Record.
 *
 * @since 3.5.8
 */
public class RecordUtil {

  private static final Method IS_RECORD_METHOD = findIsRecordMethod();

  /**
   * Whether record class or not.
   *
   * @param type a target type
   * @return If record class, return {@code true}
   */
  public static boolean isRecord(Class<?> type) {
    try {
      return IS_RECORD_METHOD != null && (boolean) IS_RECORD_METHOD.invoke(type);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }

  private static Method findIsRecordMethod() {
    try {
      return Class.class.getMethod("isRecord");
    } catch (NoSuchMethodException e) {
      return null;
    }
  }

  private RecordUtil() {
    // NOP
  }

}
