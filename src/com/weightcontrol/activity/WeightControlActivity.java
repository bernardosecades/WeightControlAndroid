package com.weightcontrol.activity;


import java.util.ArrayList;
import java.util.Date;

import com.weightcontrol.R;
import com.weightcontrol.util.Calculos;
import com.weightcontrol.model.HistorialPeso;
import com.weightcontrol.database.DBHelper;
import com.weightcontrol.util.Util;

import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class WeightControlActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_control_a);        
        cargarItemsOnSpinnerTipoActividad();             
    }
      
    public void onClickObtenerResultado(View v)
    {    	
      Double result_calorias = 0.0;  
      Double imc = 0.0;
      Double peso = 0.0;
      Integer estatura = 0;
      Integer edad = 0;
      String tipo_actividad = "";    
      String resultado = "";
      Double peso_ideal = 0.0;
             
      tipo_actividad = this.getTipoActividadForm();      
      peso = this.getPesoForm();
      edad = this.getEdadForm();
      estatura = this.getEstaturaForm();
      
      result_calorias = Util.redondear(Calculos.consumoCalorico(peso, estatura, edad, tipo_actividad));      
      TextView resultCaloriasTextView = (TextView) findViewById(R.id.result_calorias);
	  resultCaloriasTextView.setText(result_calorias.toString());   
	  
	  imc = Calculos.calculoIMC(peso, estatura);
	  resultado = Calculos.txtCalculoIMC(imc);	  
      TextView resultTextView = (TextView) findViewById(R.id.result_txt);
	  resultTextView.setText(resultado); 	  
	  
	  peso_ideal = Calculos.calculoPI(estatura);
	  TextView resultPesoIdeal = (TextView) findViewById(R.id.result_pi);
	  resultPesoIdeal.setText(peso_ideal.toString());
	  
    } 
    
    public void onClickGuardarResultado(View v)
    {  
    	Double peso = this.getPesoForm();   	    	
    	HistorialPeso peso1 = new HistorialPeso(peso, "Nota", "2012-02-03");
    	
    	DBHelper db = new DBHelper(this);
    	db.open();

    	db.insertHistorial(peso1);
    	
    	db.close();  	    	
    }
    
    public void cargarItemsOnSpinnerTipoActividad()
    {
        Spinner tipoActividadSpinner = (Spinner) findViewById(R.id.tipoActividadSpinner);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.tipo_actividad , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoActividadSpinner.setAdapter(spinner_adapter);      	
    }
        
    public String getTipoActividadForm()
    {
        Spinner tipoActividadSpinner = (Spinner) findViewById(R.id.tipoActividadSpinner);   
        String tipo_actividad = tipoActividadSpinner.getSelectedItem().toString();
        return tipo_actividad;
    }
    
    public Double getPesoForm()
    {
        Double peso = 0.0;
        try {
        	
    		EditText pesoEditText = (EditText) findViewById(R.id.pesoEditText);
    		peso = Double.parseDouble(pesoEditText.getText().toString());
    		
          } catch(NumberFormatException e) {
        	  
          	new AlertDialog.Builder(this).setTitle("Error").setMessage("Error de formato al introducir el peso").setPositiveButton("OK", null).show();
          	return (Double)0.0;
          	
          }   
        
          return peso;
    }
    
    public Integer getEstaturaForm()
    {
        Integer estatura = 0;    
        
        try {
        	
    		EditText estaturaEditText = (EditText) findViewById(R.id.estaturaEditText);
            estatura = Integer.parseInt(estaturaEditText.getText().toString());
            
          } catch (NumberFormatException e) {
        	  
    		new AlertDialog.Builder(this).setTitle("Error").setMessage("Error de formato al introducir la estatura").setPositiveButton("OK", null).show();
    		return -1;    		
          }       
        
          return estatura;
    }
    
    public Integer getEdadForm()
    {
        Integer edad = 0;    
        try {
    		EditText edadEditText = (EditText) findViewById(R.id.edadEditText);
            edad = Integer.parseInt(edadEditText.getText().toString());
        } catch (NumberFormatException e) {
        	
    		new AlertDialog.Builder(this).setTitle("Error").setMessage("Error de formato al introducir la edad").setPositiveButton("OK", null).show();
    		return -1;
    		
        }  
        
        return edad;
    }    
    
}