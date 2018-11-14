package com.cwb.shiro.service;

import java.util.List;

import com.cwb.shiro.model.Power;
import com.cwb.shiro.model.Relo;
import com.cwb.shiro.model.User;

public interface ShiService {

	List queryZtree();

	List<Power> findTree(int id);


	List<User> queryUserById(String username, String password);

	List<Relo> queryRelos();

	List<Relo> queryReloById(Integer id);

	void deleRelo(Integer id);

	void addRelo(Integer id, String[] str);

	List<Relo> queryRelo(Integer id);

	List<Power> queryPower(int i);

	List<Power> queryPowerById(Integer id);

	void deletePowerById(Integer id);

	void addPowerById(Integer id, String[] str);

}
