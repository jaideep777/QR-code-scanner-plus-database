package iisc.jaideep.sccsapp;

public class Event {
	// variables
	public String _id;
	public String _day;
	public String _meal;
	public String _n;
	public String _next;

	// constructors
	public Event(){}
	public Event(String id, String day, String meal, String n){
		this._id = id;
		this._day = day;
		this._meal = meal;
		this._n = n;
		this._next = "0";
	}
	public Event(String day, String meal, String n){
		this._day = day;
		this._meal = meal;
		this._n = n;
		this._next = "0";
	}
	
	// access variables
	public String getID(){
		return this._id;
	}
	public String getDay(){
		return this._day;
	}
	public String getMeal(){
		return this._meal;
	}
	public String getN(){
		return this._n;
	}
	
	// set variables
	public void setID(String id){
		this._id = id;
	}
	public void setDay(String day){
		this._day = day;
	}
	public void setMeal(String meal){
		this._meal = meal;
	}
	public void setN(String n){
		this._n = n;
	}
}
