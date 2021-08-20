package com.something.qlexpress;

import com.ql.util.express.IExpressContext;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/2
 */
public class QLExpressContext extends HashMap<String,Object> implements IExpressContext<String, Object> {

	private final transient ApplicationContext context;

	//构造函数，传入context和 ApplicationContext
	public QLExpressContext(Map<String, Object> map,
							ApplicationContext aContext) {
		super(map);
		this.context = aContext;
	}

	/**
	 * 抽象方法：根据名称从属性列表中提取属性值
	 */
	@Override
	public Object get(Object name) {
		Object result = null;
		result = super.get(name);
		try {
			if (result == null && this.context != null
					&& this.context.containsBean((String) name)) {
				// 如果在Spring容器中包含bean，则返回String的Bean
				result = this.context.getBean((String) name);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public Object put(String name, Object object) {
		return super.put(name, object);
	}

}