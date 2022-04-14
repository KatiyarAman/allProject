package com.ex.encrption.model;

public class Token {
	private Long userId;
	private String userRefer;
	private int expiryInMinutes;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserRefer() {
		return userRefer;
	}
	public void setUserRefer(String userRefer) {
		this.userRefer = userRefer;
	}
	public int getExpiryInMinutes() {
		return expiryInMinutes;
	}
	public void setExpiryInMinutes(int expiryInMinutes) {
		this.expiryInMinutes = expiryInMinutes;
	}
	
}
