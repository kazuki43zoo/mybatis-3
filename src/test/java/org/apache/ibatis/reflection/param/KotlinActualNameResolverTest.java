/**
 *    Copyright 2009-2016 the original author or authors.
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
package org.apache.ibatis.reflection.param;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by shimizukazuki on 2016/12/29.
 */
public class KotlinActualNameResolverTest {

	@Test
	public void testKotlinMapper() throws NoSuchMethodException {
		KotlinActualParamNameResolver resolver = new KotlinActualParamNameResolver();
		Method method = KotlinTestMapper.class.getMethod("findOne", int.class, int.class);
		Assert.assertThat(resolver.resolve(method, 0), Is.is("id"));
		Assert.assertThat(resolver.resolve(method, 1), Is.is("subId"));
	}

	@Test
	public void testJavaMapperUsingParametersOption() throws NoSuchMethodException {
		Assume.assumeTrue(Float.valueOf(System.getProperty("java.specification.version")) >= 1.8F);
		KotlinActualParamNameResolver resolver = new KotlinActualParamNameResolver();
		Method method = JavaTestMapper.class.getMethod("findOne", int.class, int.class);
		Assert.assertThat(resolver.resolve(method, 0), Is.is("id"));
		Assert.assertThat(resolver.resolve(method, 1), Is.is("subId"));
	}

	@Test
	public void testJavaMapper() throws NoSuchMethodException {
		Assume.assumeTrue(Float.valueOf(System.getProperty("java.specification.version")) < 1.8F);
		KotlinActualParamNameResolver resolver = new KotlinActualParamNameResolver();
		Method method = JavaTestMapper.class.getMethod("findOne", int.class, int.class);
		Assert.assertThat(resolver.resolve(method, 0), IsNull.nullValue());
		Assert.assertThat(resolver.resolve(method, 1), IsNull.nullValue());
	}

}
