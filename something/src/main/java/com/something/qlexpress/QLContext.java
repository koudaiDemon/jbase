package com.something.qlexpress;

import com.ql.util.express.IExpressContext;
import org.redisson.client.RedisException;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/2
 */
public class QLContext extends HashMap<String,Object> implements IExpressContext<String, Object> {



	/**
	 * 抽象方法：根据名称从属性列表中提取属性值
	 */
	@Override
	public Object get(Object name) {
		final Object o = super.get(name);
		if (Objects.isNull(o)) {
			throw new RedisException("aadadsdadasdadasdada");
		}
		return o;
	}

	@Override
	public Object put(String name, Object object) {
		return super.put(name, object);
	}

}