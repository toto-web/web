package com.noithat.object;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="info")
public class Infomation  implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8702850984449666299L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	@Type(type="text")
	private String title;
	
	@Column(name = "content")
	@Type(type="text")
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
