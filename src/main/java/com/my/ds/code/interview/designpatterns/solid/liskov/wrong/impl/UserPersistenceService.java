package com.my.ds.code.interview.designpatterns.solid.liskov.wrong.impl;

import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.QueueStore;
import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.DefaultStore;
import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.ListStore;
import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.User;

//A separate class for handling persistence
public class UserPersistenceService {

	private DefaultStore store = new DefaultStore();

	private ListStore listStore = new ListStore();

	private QueueStore queueStore = new QueueStore();

	public void saveUser(User user) {
		if(null!=user.getName()){
			store.store(user);
		}else if(null!=user.getEmail()){
			listStore.store(user);
		}else{
			queueStore.store(user);
		}

	}
}
