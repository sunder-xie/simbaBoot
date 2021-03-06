package com.simba.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.simba.framework.session.page.PageParameter;
import com.simba.util.SessionUtil;

@Component
public class UserAccountPageParameter implements PageParameter {

	@Override
	public String getKey() {
		return SessionKey.userAccountKey;
	}

	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response) {
		return SessionUtil.getUser(request.getSession()).getAccount();
	}

}
