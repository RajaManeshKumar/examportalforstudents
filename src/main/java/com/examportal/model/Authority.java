package com.examportal.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	
	public String authority;
	
	
	public  Authority(String authority) {
		this.authority = authority;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
