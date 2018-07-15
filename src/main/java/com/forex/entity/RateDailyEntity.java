package com.forex.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "rate_daily")
public class RateDailyEntity {
	/**
	 ** Jankasri Silalahi
	 **/

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_rate")
	@SequenceGenerator(name = "id_seq_rate", sequenceName = "rate_id_seq", allocationSize = 1)
	private long id;

	@Column(name = "f_rate")
	private BigDecimal rate;

	@Column(name = "f_date")
	private Date date;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@ManyToOne
	@JoinColumn(name = "forex_id")
	private ForexEntity forex;

	public RateDailyEntity() {

	}

	public RateDailyEntity(BigDecimal rate, Date date, ForexEntity forex) {
		super();
		this.rate = rate;
		this.date = date;
		this.forex = forex;
	}

	@JsonProperty("rate")
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@JsonProperty("date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("forex")
	public ForexEntity getForex() {
		return forex;
	}

	public void setForex(ForexEntity forex) {
		this.forex = forex;
	}

}
