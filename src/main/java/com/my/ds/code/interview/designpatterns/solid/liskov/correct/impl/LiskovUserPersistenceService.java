package com.my.ds.code.interview.designpatterns.solid.liskov.correct.impl;

import com.my.ds.code.interview.designpatterns.solid.liskov.correct.domain.LiskovDefaultOCStore;
import com.my.ds.code.interview.designpatterns.solid.liskov.correct.domain.LiskovListOCStore;
import com.my.ds.code.interview.designpatterns.solid.liskov.correct.domain.LiskovUser;
import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCDefaultOCStore;
import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCListOCStore;
import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCUser;

//A separate class for handling persistence
public class LiskovUserPersistenceService {

	private LiskovDefaultOCStore store = new LiskovDefaultOCStore();

	private LiskovListOCStore liskovListOCStore = new LiskovListOCStore();

	public void saveUser(LiskovUser likovUser) {
		if(null!= likovUser.getName()){
			store.store(likovUser);
		}else{
			liskovListOCStore.store(likovUser);
		}

	}
}
