package com.noithat.object;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="group")
public class Group  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5884158584963795428L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "groupId")
	private int groupId;
	
	@Column(name = "groupName")
	@Type(type="text")
	private String groupName;
	
	@Column(name = "groupDescription")
	@Type(type="text")
	private String groupDescription;
	
	@Column(name = "seo")
	@Type(type="text")
	private String seo;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
