package com.my.ds.code.interview.designpatterns.solid.openclose.correct.impl;

import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCDefaultOCStore;
import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCListOCStore;
import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCUser;

//A separate class for handling persistence
public class OCUserPersistenceService {

	private OCDefaultOCStore store = new OCDefaultOCStore();

	private OCListOCStore OCListStore = new OCListOCStore();

	public void saveUser(OCUser OCUser) {
		if(null!= OCUser.getName()){
			store.store(OCUser);
		}else{
			OCListStore.store(OCUser);
		}

	}
}
