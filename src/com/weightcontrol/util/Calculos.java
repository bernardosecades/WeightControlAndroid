package com.weightcontrol.util;

import android.util.Log;

public class Calculos 
{
	
	/**
	 * @param peso
	 * @param altura
	 * @param edad
	 * @return
	 */
	public static Double calcularRMB(Double peso, Integer altura, Integer edad)
	{
		
		Double param1 = 66.473; // +
		Double param2 = 13.752; // x KG
		Double param3 = 5.0033; // x Cm
		Double param4 = 6.755; // x edad
		
		Double rmb = 0.0;
				
		rmb = param1 + (param2 * peso) + (param3 * altura) - (param4 * edad);
		
		return (Double) rmb;
	}
	
	/**
	 * 
	 * @param peso
	 * @param altura
	 * @param edad
	 * @param tipo_actividad
	 * @return
	 */
	public static Double consumoCalorico(Double peso, Integer altura, Integer edad,String tipo_actividad)
	{
		
		Double actividad_baja = 1.3;
		Double actividad_media = 1.5;
		Double actividad_alta = 1.7;
		Double actividad_muy_alta = 1.8;		
		
		Double indice_actividad = 0.0;
		
		if (tipo_actividad.equals("Baja")) {
			indice_actividad = actividad_baja;
		} else if (tipo_actividad.equals("Media")) {
			indice_actividad = actividad_media;			
		} else if (tipo_actividad.equals("Alta")) {
			indice_actividad = actividad_alta;			
		} else if (tipo_actividad.equals("Muy alta")) {
			indice_actividad = actividad_muy_alta;
		} else {
			// Error			
		}
		
		Log.d("TIPO_ACTIVIDAD", tipo_actividad);
		
		Log.d("INDICE", Double.toString(indice_actividad));
		
		return indice_actividad * calcularRMB(peso, altura, edad);
	}
	
	/**
	 * @param peso
	 * @param altura
	 * @return
	 */
	public static Double calculoIMC(Double peso, Integer altura)
	{		
		Double indice = 0.0;		
		
		indice = peso/(Math.pow(altura/100.0,2));
		Log.d("IMC",Double.toString(indice));	
		
		return indice;			
	}
	
	/**
	 * @param indice
	 * @return
	 */
	public static String txtCalculoIMC(Double indice)
	{		
		Log.d("IMC",Double.toString(indice));		
		String result = "";		
		
		if (indice >= 16.5 && indice < 18.5) result = "Infrapeso";
		else if (indice >= 18.5 && indice <20.5) result = "Bajo peso";
		else if (indice >= 20.5 && indice < 25.5) result = "Peso normal";
		else if (indice >= 25.5 && indice < 30) result = "Sobrepeso";
		else if (indice >= 30 && indice < 40) result = "Obesidad grado 1";
		else if (indice >= 40) result = "Obesidad grado 2";
		return result;
	}
	
	/**
	 * @param altura
	 * @return
	 */
	public static Double calculoPI(Integer altura)
	{
		return 0.75 * (altura - 150) + 50;
	}

}
