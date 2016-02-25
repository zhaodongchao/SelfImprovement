package com.newtouch.entitys;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable {
	private static final long serialVersionUID = 3904841057857611639L;
	private String id;
	private String menu_name;
	private int level;
	private boolean isleaf;
	private String menu_url;
	private Date created_time ;
	private String parentId;

	public Menu() {
	}

	
   
	public Menu(String id, String menu_name, int level, boolean isleaf, String menu_url, Date created_time,
			String parentId) {
		super();
		this.id = id;
		this.menu_name = menu_name;
		this.level = level;
		this.isleaf = isleaf;
		this.menu_url = menu_url;
		this.created_time = created_time;
		this.parentId = parentId;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isIsleaf() {
		return isleaf;
	}

	public void setIsleaf(boolean isleaf) {
		this.isleaf = isleaf;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public Date getCreated_time() {
		return created_time;
	}



	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}



	@Override
	public String toString() {
		return "Menu [id=" + id + ", menu_name=" + menu_name + ", level=" + level + ", isleaf=" + isleaf + ", menu_url="
				+ menu_url + ", created_time=" + created_time + ", parentId=" + parentId + "]";
	}

}
