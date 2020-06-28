package com.jxau.yzm.mytravelproject.pojo;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="gallery")
public class Gallery implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;//
	private String img;//图片
	private String title;//标题
	private String comment;//内容

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Gallery{" +
				"id=" + id +
				", img='" + img + '\'' +
				", title='" + title + '\'' +
				", comment='" + comment + '\'' +
				'}';
	}
}
