package com.itext.Objects;

import java.awt.image.BufferedImage;

public class User {
	private String _name;
	private String _address;
	private String _phone;
	private BufferedImage _imageUrl;
	private String _logoUrl;
	private String _stampUrl;
	public User(String name, String address, String phone, BufferedImage imageUrl, String logoUrl, String stampUrl) {
		_name = name;
		_address = address;
		_phone = phone;
		_imageUrl = imageUrl;
		_logoUrl = logoUrl;
		_stampUrl = stampUrl;
	}
	public String getName() {
		return _name;
	}
	public String getAddress() {
		return _address;
	}
	public String getPhone() {
		return _phone;
	}
	public BufferedImage getImageUrl() {
		return _imageUrl;
	}
	public String getLogoUrl() {
		return _logoUrl;
	}
	public String getStampUrl() {
		return _stampUrl;
	}
}
