package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.MoveDao;
import com.wxpt.site.entity.Move;
import com.wxpt.site.service.MoveService;

public class MoveServiceImpl implements MoveService{
@Autowired
MoveDao moveDao;
	@Transactional
	public List<Move> findByMoveName(String moveName,int enterId) {
		// TODO Auto-generated method stub
		return moveDao.findByMoveName(moveName,enterId);
	}
	@Transactional
	public void save(Move move,int enterId) {
		moveDao.save(move,enterId);
		
	}
	@Transactional
	public List<Move> getMoveList(int page, int rows, String sql,int enterId) {
		// TODO Auto-generated method stub
		return moveDao.getMoveList(page,rows,sql,enterId);
	}
	@Transactional
	public int getMoveById(String hql) {
		// TODO Auto-generated method stub
		return moveDao.getMoveById(hql);
	}
	@Transactional
	public List<Move> getAllMove(int enterId) {
		// TODO Auto-generated method stub
		return moveDao.findAllMove(enterId);
	}
	@Transactional
	public void delete(Move move) {
		moveDao.delete(move);
		
	}
	@Transactional
	public void update(Move move,int enterId) {
		moveDao.update(move,enterId);
		
	}
	@Transactional
	public void deleteByMoveId(int moveId,int enterId) {
		moveDao.deleteByMoveId(moveId,enterId);
		
	}
	@Transactional
	public Move getMoveByMoveId(int moveId,int enterId) {
		// TODO Auto-generated method stub
		return moveDao.getMoveByMoveId(moveId,enterId);
	}

}
