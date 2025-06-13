package com.internship.sms.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "notice")
public class Notice extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;
	
	@Column(name="notice_status")
	private boolean notice_status;

	@Column(name = "notice_picture")
	private String noticePicture;

}
