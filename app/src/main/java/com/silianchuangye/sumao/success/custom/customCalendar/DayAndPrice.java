package com.silianchuangye.sumao.success.custom.customCalendar;

public class DayAndPrice {
	public String price;
	public String skuId;
	public int day;
	public int year;
	public int month;
	public DayAndPrice(String price, int year, int month,int day,String skuId) {
		super();
		this.price = price;
		this.day = day;
		this.year = year;
		this.month = month;
		this.skuId = skuId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
}
