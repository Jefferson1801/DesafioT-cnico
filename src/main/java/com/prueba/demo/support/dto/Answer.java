package com.prueba.demo.support.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "codigo", "parametros" })
public class Answer<D> {

	private boolean success;
	private Map<String, Object> parametros;
	private String message;
	private D dato;

	
	public Answer() {
	}

	public Answer(boolean success) {
		this.success = success;
	}

	public Answer(boolean success, D dato) {
		this.success = success;
		this.dato = dato;
	}
 
	public Answer(boolean success, D dato, String message) {
		this.success = success;
		this.dato = dato;
		this.message = message;
	}
	
	public Answer(boolean success, Map<String, Object> parametros) {
		this.success = success;
		this.parametros = parametros;
	}

	public Answer(boolean success, Map<String, Object> parametros, D dato) {
		this.success = success;
		this.parametros = parametros;
		this.dato = dato;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

	public Answer(boolean success, Map<String, Object> parametros, String message) {
		super();
		this.success = success;
		this.parametros = parametros;
		this.message = message;
	}

	public D getDato() {
		return dato;
	}

	public void setDato(D dato) {
		this.dato = dato;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}