package com.noithat.object;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="categories")
public class Categories implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -611550803934107863L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "categoriesId")
	private int categoriesId;
	
	@Column(name = "categoriesName")
	@Type(type="text")
	private String categoriesName;
	
	@Column(name = "categoriesDescription")
	@Type(type="text")
	private String categoriesDescription;
	
	@Column(name = "seo")
	@Type(type="text")
	private String seo;
	
	@Column(name = "groupId")
	@Type(type="text")
	private String groupId;

	public int getCategoriesId() {
		return categoriesId;
	}

	public void setCategoriesId(int categoriesId) {
		this.categoriesId = categoriesId;
	}

	public String getCategoriesName() {
		return categoriesName;
	}

	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}

	public String getCategoriesDescription() {
		return categoriesDescription;
	}

	public void setCategoriesDescription(String categoriesDescription) {
		this.categoriesDescription = categoriesDescription;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
