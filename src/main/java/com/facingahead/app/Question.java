package com.facingahead.app;

import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Question {
	private static final String YES = "YES";
	private static final String NO = "NO";
	private static final String MAYBE = "MAYBE";

	@Id
	private String id;
	
	private String text;
	private String category;
	private String order;
	private String why;

	public Question() {
		// blank constructor
	}

	public Question(Question question) {
		this.text = question.getText();
		this.category = question.getCategory();
		this.order = question.getOrder();
		this.why = question.getWhy();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}
	
	@Override
	public String toString(){
		ToStringCreator tsc = new ToStringCreator(this)
				.append("id", this.id)
				.append("text", this.text)
				.append("order", this.order)
				.append("why", this.why);
		return tsc.toString();
	}

	@Override
	public boolean equals(Object comp){
		if(comp instanceof Question){
			Question q = (Question) comp;
			return q.text.equalsIgnoreCase(this.text) &&
					q.category.equalsIgnoreCase(this.category) &&
					q.why.equalsIgnoreCase(this.why) &&
					q.order.equalsIgnoreCase(this.order);
		}
		return false;
	}

	@Override
	public int hashCode(){
		return Objects.hash(this.text, this.category, this.why, this.order);
	}

}
