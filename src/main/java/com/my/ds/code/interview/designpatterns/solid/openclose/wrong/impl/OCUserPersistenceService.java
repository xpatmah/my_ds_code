package com.my.ds.code.interview.designpatterns.solid.openclose.wrong.impl;

import com.my.ds.code.interview.designpatterns.solid.openclose.wrong.domain.OCStore;
import com.my.ds.code.interview.designpatterns.solid.openclose.wrong.domain.OCUser;

//A separate class for handling persistence
public class OCUserPersistenceService {

	private OCStore ocStore = new OCStore();
	
	public void saveUser(OCUser ocUser) {
		ocStore.store(ocUser);
	}
}
