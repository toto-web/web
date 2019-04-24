package com.noithat.object;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="project")
public class Project implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1792789602727863668L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	@Type(type="text")
	private String title;
	
	@Column(name = "content")
	@Type(type="text")
	private String content;
	
	@Column(name = "image")
	@Type(type="text")
	private String image;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
