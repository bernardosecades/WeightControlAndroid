package com.weightcontrol.model;

public class HistorialPeso 
{

	private long id = 0;
	private Double peso = null;
	private String nota = "";
	private String created_at = "";
	
	public HistorialPeso(Double peso, String nota, String created_at)
	{	
		this.peso = peso;
		this.nota = nota;
		this.created_at = created_at;
	}
	
	public HistorialPeso(long id, Double peso, String nota, String created_at)
	{
		this(peso,nota,created_at);
		this.id = id;
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public Double getPeso()
	{
		return this.peso;
	}
	
	public String getNota()
	{
		return this.nota;
	}
	
	public String getCreatedAt()
	{
		return this.created_at;
	}
	
	public void setPeso(Double peso)
	{ 
		this.peso = peso;
	}
	
	public void setNota(String nota)
	{ 
		this.nota = nota;
	}
	
	public void setCreatedAt(String created_at)
	{ 
		this.created_at = created_at;
	}	
	
	
}
