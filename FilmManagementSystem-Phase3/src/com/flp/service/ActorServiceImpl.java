package com.flp.service;

import java.util.Set;

import com.flp.fms.dao.ActorDaoImplForList;
import com.flp.fms.domain.Actor;
import com.flp.fms.dao.IActorDao;

public class ActorServiceImpl implements IActorService{

	private IActorDao actorDao=new ActorDaoImplForList();
	@Override
	public Set<Actor> getActors() {
		// TODO Auto-generated method stub
		return actorDao.getActors();
	}

	

}
