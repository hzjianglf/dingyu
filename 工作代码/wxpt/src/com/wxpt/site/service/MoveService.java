package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Move;

public interface MoveService {
public List<Move> findByMoveName(String moveName, int enterId);

public void save(Move move, int enterId);

public List<Move> getMoveList(int page, int rows, String sql, int enterId);

public int getMoveById(String hql);

public List<Move> getAllMove(int enterId);

public void delete(Move move);

public void update(Move move, int enterId);

public void deleteByMoveId(int moveId, int enterId);

public Move getMoveByMoveId(int moveId, int enterId);
}
