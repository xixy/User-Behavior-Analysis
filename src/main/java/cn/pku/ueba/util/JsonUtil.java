/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月31日 下午4:16:21
 */
package cn.pku.ueba.util;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 实现对象实例和Json之间的转换
 */
public class JsonUtil {
	/**
	 * 从模型实例得到Json数据
	 * 
	 * @param obj
	 *            模型实例
	 * @return Json数据
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getJsonFromModelInstance(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> result = null;
		try {
			result = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 从Json中获取到模型对象
	 * 
	 * @param json
	 *            json格式的数据
	 * @param valueType
	 *            对应的模型对象
	 * @return
	 */
	public static Object getModelInstanceFromJson(Map<String, Object> json, Class<?> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		String s_json = null;
		try {
			s_json = mapper.writeValueAsString(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object obj = null;
		try {
			obj = valueType.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		try {
			obj = mapper.readValue(s_json, valueType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
