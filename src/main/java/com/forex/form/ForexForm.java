package com.forex.form;

import java.math.BigDecimal;
import java.util.Date;

public class ForexForm {
	/**
	 ** Jankasri Silalahi
	 **/

	private String from;
	private String to;

	private BigDecimal rate;

	private Date date;

	public ForexForm() {

	}

	public ForexForm(String from, String to, BigDecimal rate) {
		super();
		this.from = from;
		this.to = to;
		this.rate = rate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
