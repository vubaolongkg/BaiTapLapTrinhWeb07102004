package vbl.entity;

import java.io.Serializable;

import jakarta.persistence.*;
@Entity
@Table(name="videos")
@NamedQuery (name="Video.findAll", query="select v from Video v")
public class Video implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="VideoId")
	private String videoId;
	
	@Column(name="Active")
	private boolean active;
	
	@Column(name="Description", columnDefinition = "nvarchar(max)")
	private String description;
	
	@Column(name="Poster")
	private String poster;
	
	@Column(name="Title", columnDefinition = "nvarchar(max)")
	private String title;
	
	@Column(name="Views")
	private String views;
	
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;

	public Video() {
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
