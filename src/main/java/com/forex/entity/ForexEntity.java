package com.forex.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "forex")
public class ForexEntity {
	/**
	 ** Jankasri Silalahi
	 **/

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
	@SequenceGenerator(name = "id_seq", sequenceName = "forex_id_seq", allocationSize = 1)
	private long id;

	@Column(name = "f_from")
	private String from;

	@Column(name = "f_to")
	private String to;

	private String createdBy;
	private Date createdDate;

	public ForexEntity() {

	}

	public ForexEntity(String from, String to, String createdBy, Date createdDate) {
		super();
		this.from = from;
		this.to = to;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("from")
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@JsonProperty("to")
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
