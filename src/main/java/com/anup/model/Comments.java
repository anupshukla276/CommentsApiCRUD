package com.anup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
@Table(name="Comments")
public class Comments {
//	 Id INT AUTO_INCREMENT PRIMARY KEY,
//	    Date DATE,
//	    CommentedBy VARCHAR(255),
//	    Text TEXT
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name="Date")
	private Date Date;
	@Column(name="commented_by")
	private String commented_by;
	@Column(name= "Text")
	private String Text;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getcommented_by() {
		return commented_by;
	}
	public void setcommented_by(String commented_By) {
		commented_by = commented_By;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public Comments(Long id, Date date, String commented_by, String text) {
	
		this.Id = id;
		this.Date = date;
		this.commented_by = commented_by;
		this.Text = text;
	}
	
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Comments [Id=" + Id + ", Date=" + Date + ", commented_by=" + commented_by + ", Text=" + Text + "]";
	}
	
	

}
