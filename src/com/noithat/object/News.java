package com.noithat.object;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="news")
public class News implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2035547573629066127L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "newsId")
	private int newsId;
	
	@Column(name = "newsTitle")
	@Type(type="text")
	private String newsTitle;
	
	@Column(name = "newsContent")
	@Type(type="text")
	private String newsContent;
	
	@Column(name = "timeCreate")
	private Timestamp timeCreate;
	
	@Column(name = "timeModify")
	private Timestamp timeModify;
	
	@Column(name = "newsSeo")
	@Type(type="text")
	private String newsSeo;

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Timestamp getTimeModify() {
		return timeModify;
	}

	public void setTimeModify(Timestamp timeModify) {
		this.timeModify = timeModify;
	}

	public String getNewsSeo() {
		return newsSeo;
	}

	public void setNewsSeo(String newsSeo) {
		this.newsSeo = newsSeo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
