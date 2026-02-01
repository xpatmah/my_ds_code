package com.my.ds.code.interview.designpatterns.solid.srp.correct.impl;

import com.my.ds.code.interview.designpatterns.solid.srp.correct.domain.SrpStore;
import com.my.ds.code.interview.designpatterns.solid.srp.correct.domain.SrpUser;

//A separate class for handling persistence
public class SrpUserPersistenceService {

	private SrpStore srpStore = new SrpStore();
	
	public void saveUser(SrpUser srpUser) {
		srpStore.store(srpUser);
	}
}
