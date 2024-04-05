package com.prueba.demo.support.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiResponse {
	/**
	 * Respuesta de objeto
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> mapObjectOK(Object obj) {
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("data", obj);
		modelMap.put("success", true);
		return modelMap;
	}

	/**
	 * Respuesta de lista
	 * 
	 * @param contacts
	 * @return
	 */
	public static Map<String, Object> mapListOK(List<?> list) {
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", list.size());
		modelMap.put("data", list);
		modelMap.put("success", true);
		return modelMap;
	}

	/**
	 * Respuesta de lista con total
	 * 
	 * @param list
	 * @return
	 */
	public static Map<String, Object> mapListOK(List<?> list, int total) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", total);
		modelMap.put("data", list);
		modelMap.put("success", true);
		return modelMap;
	}

	/**
	 * Respuesta de error
	 * 
	 * @param msg message
	 * @return
	 */
	public static Map<String, Object> mapError(String code, String msg) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("success", false);
		modelMap.put("error", code);
		modelMap.put("message", msg);
		return modelMap;
	}

	public static Map<String, Object> mapError(String msg) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("success", false);
		modelMap.put("message", msg);
		return modelMap;
	}

	public Map<String, Object> mapSuccess(String msg) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("success", false);
		modelMap.put("message", msg);
		return modelMap;
	}
}
