package com.cwb.shiro.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cwb.shiro.dao.ShiDao;
import com.cwb.shiro.model.Power;
import com.cwb.shiro.model.Relo;
import com.cwb.shiro.model.User;
import com.cwb.shiro.service.ShiService;

@Service
public class ShiServiceImpl implements ShiService {

	@Resource
	private ShiDao shiDao;

	@Override
	public List queryZtree() {
		// TODO Auto-generated method stub
		return shiDao.queryZtree();
	}
	

	@Override
	public List<Power> findTree(int id) {
		// TODO Auto-generated method stub
		return shiDao.queryTrees(id);
	}



	@Override
	public List<User> queryUserById(String username, String password) {
		MessageDigest md5;
		String pwd = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes()); 
			pwd = new BigInteger(1, md5.digest()).toString(16); 
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//.toUpperCase()小写转大写
		return shiDao.queryUserById(username,pwd.toUpperCase());
	}


	@Override
	public List<Relo> queryRelos() {
		// TODO Auto-generated method stub
		return shiDao.queryRelos();
	}


	@Override
	public List<Relo> queryReloById(Integer id) {
		// TODO Auto-generated method stub
		return shiDao.queryReloById(id);
	}


	@Override
	public void deleRelo(Integer id) {
		// TODO Auto-generated method stub
		       shiDao.deleRelo(id);
	}


	@Override
	public void addRelo(Integer id, String[] str) {
		// TODO Auto-generated method stub
		shiDao.addRelo(id,str);
		
	}


	@Override
	public List<Relo> queryRelo(Integer id) {
		// TODO Auto-generated method stub
		return shiDao.queryRelo(id);
	}


	@Override
	public List<Power> queryPower(int i) {
		// TODO Auto-generated method stub
		return shiDao.queryPower(i);
	}


	@Override
	public List<Power> queryPowerById(Integer id) {
		// TODO Auto-generated method stub
		return shiDao.queryPowerById(id);
	}


	@Override
	public void deletePowerById(Integer id) {
		// TODO Auto-generated method stub
		      shiDao.deletePowerById(id);	
		
	}


	@Override
	public void addPowerById(Integer id, String[] str) {
		// TODO Auto-generated method stub
		    shiDao.addPowerById(id,str);	
	}
	
}
