package com.apodoba.shop.domain.bank;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity(name = "Card")
public class Card implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2651505569410268888L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private long number;
	
	@Column
	private int cvv;
	
	@Column(name="end_year")
	private int endYear;
	
	@Column
	private BigDecimal count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("number", number)
				.append("cvv", cvv).append("end year", endYear).append("count", count.doubleValue()).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Card card = (Card) obj;
		return new EqualsBuilder().append(id, card.id).append(number, card.number)
				.append(cvv, card.cvv).append(endYear, card.endYear)
				.append(count, card.count)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).append(number).append(cvv)
				.append(endYear).append(count).toHashCode();
	}
}
