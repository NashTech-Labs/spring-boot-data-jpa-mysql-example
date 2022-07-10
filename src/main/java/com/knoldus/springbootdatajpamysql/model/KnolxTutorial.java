package com.knoldus.springbootdatajpamysql.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import org.springframework.data.annotation.Id;

/**
 * KnolxTutorial database where
 * all entity applied like: id,title,description.
 */
@Entity
@Table(name = "tutorials")
public class KnolxTutorial {
	@javax.persistence.Id
	@Column(name = "t_id", nullable = false)
	private Long t_id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "published")
	private boolean published;

	public Long getT_id() {
		return t_id;
	}

	public void setT_id(Long t_id) {
		this.t_id = t_id;
	}

	public KnolxTutorial() {

	}

	/**
	 * Used getter setter method for collecting data.
	 * @param title  describes Knolx Title
	 * @param description  decription of knolx.
	 * @param published which day it'll be delieverd.
	 */
	public KnolxTutorial(String title, String description, boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}

}
