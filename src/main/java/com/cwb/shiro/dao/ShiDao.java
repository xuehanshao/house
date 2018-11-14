package com.cwb.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cwb.shiro.model.Power;
import com.cwb.shiro.model.Relo;
import com.cwb.shiro.model.User;

public interface ShiDao {

	List queryZtree();

	List<Power> queryTrees(Integer pid);


	List<User> queryUserById(@Param("username")String username,@Param("pwd")String pwd);

	List<Relo> queryRelos();

	List<Relo> queryReloById(Integer id);

	void deleRelo(Integer id);

	void addRelo(Integer id,@Param("rid") String[] str);

	List<Relo> queryRelo(Integer id);

	List<Power> queryPower(int i);

	List<Power> queryPowerById(Integer id);

	void deletePowerById(Integer id);

	void addPowerById(Integer id,@Param("pids") String[] str);

}
