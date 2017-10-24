package com.amc.web.models.extension;

import com.amc.model.models.Account;
import com.amc.web.models.AccountRegisterModel;

public class AccountRegisterModelExtension {
	public static Account toAccount(AccountRegisterModel registerModel){
		Account ret=new Account();
		ret.setName(registerModel.getName());
		ret.setEmail(registerModel.getEmail());
		ret.setUsername(registerModel.getUsername());
		ret.setPassword(registerModel.getPassword());
		
		return ret;
	}
}
