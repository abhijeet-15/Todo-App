package com.abhijeetsingh.todoapp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class Todo{
	private int id;
	private boolean completed;
	private String title;
	private int userId;

	public Todo(int i, boolean b, String s, int i1) {
		this.id = i;
		this.completed = b;
		this.title = s;
		this.userId = i1;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCompleted(boolean completed){
		this.completed = completed;
	}

	public boolean isCompleted(){
		return completed;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"Todo{" + 
			"id = '" + id + '\'' + 
			",completed = '" + completed + '\'' + 
			",title = '" + title + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
