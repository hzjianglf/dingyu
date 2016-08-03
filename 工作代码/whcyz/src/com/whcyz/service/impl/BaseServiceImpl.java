package com.whcyz.service.impl;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.whcyz.service.BaseService;
import com.whcyz.util.SqlKeyword;

public abstract class BaseServiceImpl implements BaseService{
	@Override
	public String WHERE_DISABLE_FALSE(){
		return NEED_DISABLE?SqlKeyword.WHERE_DISABLE_FALSE:"";
	}
	@Override
	public String AND_DISABLE_FALSE(){
		return NEED_DISABLE?SqlKeyword.AND_DISABLE_FALSE:"";
	}
	@Override
	public abstract String table();
	
	@Override
	public abstract Model<?> dao();
	@Override
	public boolean isExist(String where,Object... params){
		return findFirstWithColumns("id",where,params)!=null;
	}
	@Override
	public boolean isNotExist(String where,Object... params){
		return findFirstWithColumns("id",where,params)==null;
	}
	@Override
	public int getCount(String where,Object... params){
		return getList("id", where, params).size();
	}
	@Override
	public int getCount(){
		return getList("id").size();
	}
	
	@Override
	public <T> List<T> getList() {
		return (List<T>)dao().find(new StringBuilder().append(SqlKeyword.SELECT_ALL_FROM).append(table()).append(WHERE_DISABLE_FALSE()).toString());
	}
	
	@Override
	public <T> List<T> getListOrderBy(String column,String rule) {
		return (List<T>)dao().find(new StringBuilder().append(SqlKeyword.SELECT_ALL_FROM).append(table()).append(WHERE_DISABLE_FALSE()).append(SqlKeyword.ORDER_BY).append(column).append(" ").append(rule).toString());
	}

	@Override
	public <T> List<T> getList(String columns) {
		return getList(columns,null,null);
	}

	@Override
	public <T> List<T> getList(String columns, String where, Object... params) {
		if(where==null||where.isEmpty()){
			return (List<T>)dao().find(new StringBuilder().append(SqlKeyword.SELECT).append(columns).append(SqlKeyword.FROM).append(table()).append(WHERE_DISABLE_FALSE()).toString());
		}
		return (List<T>)dao().find(new StringBuilder().append(SqlKeyword.SELECT).append(columns).append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).toString(), params);
	}
	
	@Override
	public <T> List<T> getListWithExtras(String columns, String where,String extras, Object... params) {
		if(where==null||where.isEmpty()){
			return (List<T>)dao().find(new StringBuilder().append(SqlKeyword.SELECT).append(columns).append(SqlKeyword.FROM).append(table()).append(WHERE_DISABLE_FALSE()).append(extras).toString());
		}
		return (List<T>)dao().find(new StringBuilder().append(SqlKeyword.SELECT).append(columns).append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).append(extras).toString(), params);
	}

	@Override
	public boolean save(Model<?> model) {
		return model.save();
	}

	@Override
	public boolean update(Model<?> model) {
		return model.update();
	}
	@Override
	public int updateWithSql(String sql,Object... params) {
		return Db.update(sql, params);
	}
	@Override
	public int updateWithSet(String set,String where,Object... params) {
		return Db.update(new StringBuilder().append(SqlKeyword.UPDATE).append(table()).append(SqlKeyword.SET).append(set).append(SqlKeyword.WHERE).append(where).toString(),params);
	}
	@Override
	public boolean deleteById(Object id){
		return Db.deleteById(table(), id);
	}
	@Override
	public int delete(String where, Object... params){
		return  Db.update(new StringBuilder().append(SqlKeyword.DELETE_FROM).append(table()).append(SqlKeyword.WHERE).append(where).toString(),params);
	}
	@Override
	public boolean deleteByIds(Object[] ids){
		return Db.update(SqlKeyword.DELETE_FROM+table()+SqlKeyword.WHERE_ID_IN,ids)==ids.length;
	}
	@Override
	public boolean disableById(Object id) {
		return Db.update(new StringBuilder().append(SqlKeyword.UPDATE).append(table()).append(SqlKeyword.SET_DISABLE_TRUE).append(SqlKeyword.WHERE_ID).append(AND_DISABLE_FALSE()).toString(),id)==1;
	}
	@Override
	public boolean disableByIds(Object[] ids){
		return Db.update(new StringBuilder().append(SqlKeyword.UPDATE).append(table()).append(SqlKeyword.SET_DISABLE_TRUE).append(SqlKeyword.WHERE_ID_IN).toString(),ids)>0;
	}
	@Override
	public boolean disable(String where,Object... params){
		return Db.update(new StringBuilder().append(SqlKeyword.UPDATE).append(table()).append(SqlKeyword.SET_DISABLE_TRUE).append(SqlKeyword.WHERE).append(where).toString(),params)==1;
	}
	@Override
	public <T extends Model<?>> T findById(Object id) {
		return (T)dao().findById(id);
	}
	@Override
	public <T extends Model<?>> T findById(Object id,String columns) {
		return (T)dao().findById(id, columns);
	}


	@Override
	public <T extends Model<?>> T findFirst(){
		return (T)dao().findFirst(new StringBuilder().append(SqlKeyword.SELECT_ALL_FROM).append(table()).append(WHERE_DISABLE_FALSE()).append(SqlKeyword.LIMIT_ONE).toString());
	}
	@Override
	public <T extends Model<?>> T findFirstWithColumns(String columns){
		return (T)dao().findFirst(new StringBuilder().append(SqlKeyword.SELECT).append(columns).append(SqlKeyword.FROM).append(table()).append(WHERE_DISABLE_FALSE()).append(SqlKeyword.LIMIT_ONE).toString());
	}
	@Override
	public <T extends Model<?>> T findFirstWithColumns(String columns,String where,Object... params){
		return (T)dao().findFirst(new StringBuilder().append(SqlKeyword.SELECT).append(columns).append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).append(SqlKeyword.LIMIT_ONE).toString(),params);
	}
	@Override
	public <T extends Model<?>> T findFirst(String where,Object... params){
		return (T)dao().findFirst(new StringBuilder().append(SqlKeyword.SELECT).append(SqlKeyword.ALL).append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).append(SqlKeyword.LIMIT_ONE).toString(),params);
	}
	@Override
	public <T extends Model<?>> T findFirstByCache(String cacheKey,final String columns,final String where,final Object... params){
		return CacheKit.get(table(), cacheKey, new IDataLoader() {
			@Override
			public Object load() {
				return (T)dao().findFirst(new StringBuilder().append(SqlKeyword.SELECT).append(columns).append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).append(SqlKeyword.LIMIT_ONE).toString(),params);
			}
		}); 
	
	}
	@Override
	public Page<?> paginate(int pageNumber,int pageSize){
		return dao().paginate(pageNumber<=0?1:pageNumber, pageSize,SqlKeyword.SELECT_ALL, new StringBuilder().append(SqlKeyword.FROM).append(table()).append(WHERE_DISABLE_FALSE()).toString());
	}
	@Override
	public Page<?> paginate(int pageNumber,int pageSize, String sort, String order){
		return dao().paginate(pageNumber<=0?1:pageNumber, pageSize,SqlKeyword.SELECT_ALL, new StringBuilder().append(SqlKeyword.FROM).append(table()).append(WHERE_DISABLE_FALSE()).append(SqlKeyword.ORDER_BY).append(sort).append(" ").append(order).toString());
	}
	@Override
	public Page<?> paginate(int pageNumber,int pageSize, String sort, String order, String columns, String where, Object... params){
		return dao().paginate(pageNumber<=0?1:pageNumber, pageSize,SqlKeyword.SELECT+columns, new StringBuilder().append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).append(SqlKeyword.ORDER_BY).append(sort).append(" ").append(order).toString(), params);
	}
	@Override
	public Page<?> paginate(int pageNumber,int pageSize,String columns){
		return dao().paginate(pageNumber<=0?1:pageNumber, pageSize, SqlKeyword.SELECT+columns, new StringBuilder().append(SqlKeyword.FROM).append(table()).append(WHERE_DISABLE_FALSE()).toString());
	}
	@Override
	public Page<?> paginate(int pageNumber,int pageSize,String columns,String where,Object... params){
		return dao().paginate(pageNumber<=0?1:pageNumber, pageSize, SqlKeyword.SELECT+columns,new StringBuilder().append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).toString(),params);
	}
	@Override
	public Page<?> paginateWithExtras(int pageNumber,int pageSize,String columns,String extras){
		return dao().paginate(pageNumber<=0?1:pageNumber, pageSize, SqlKeyword.SELECT+columns,new StringBuilder().append(SqlKeyword.FROM).append(table()).append(AND_DISABLE_FALSE()).append(extras).toString());
	}
	@Override
	public Page<?> paginateWithExtras(int pageNumber,int pageSize,String columns,String where,String extras,Object... params){
		return dao().paginate(pageNumber<=0?1:pageNumber, pageSize, SqlKeyword.SELECT+columns,new StringBuilder().append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).append(extras).toString(),params);
	}
	@Override
	public <T> List<T> getListByCache(String cacheKey){
		return CacheKit.get(table(), cacheKey, new IDataLoader() {
			@Override
			public Object load() {
				return getList();
			}
		});
	}
	@Override
	public Page<?> paginateWithWhereAndSort(int pageNumber,int pageSize, String sort, String order, String columns, String where, Object... params){
		if(StrKit.isBlank(where)){
			return dao().paginate(pageNumber<=0?1:pageNumber, pageSize,SqlKeyword.SELECT+columns, new StringBuilder().append(SqlKeyword.FROM).append(table()).append(SqlKeyword.ORDER_BY).append(sort).append(" ").append(order).toString(), params);
		}else{
			return dao().paginate(pageNumber<=0?1:pageNumber, pageSize,SqlKeyword.SELECT+columns, new StringBuilder().append(SqlKeyword.FROM).append(table()).append(SqlKeyword.WHERE).append(where).append(AND_DISABLE_FALSE()).append(SqlKeyword.ORDER_BY).append(sort).append(" ").append(order).toString(), params);
		}
	}
	@Override
	public <T> List<T> getListByCache(String cacheKey,final String columns){
		return getListByCache(cacheKey, columns, null, null);
	}
	@Override
	public <T> List<T> getListByCache(String cacheKey,final String columns,final String where, final Object... params){
		if(where==null&&where.isEmpty()){
			return CacheKit.get(table(), cacheKey, new IDataLoader() {
				@Override
				public Object load() {
					return getList(columns);
				}
			});
		}
		
		return CacheKit.get(table(), cacheKey, new IDataLoader() {
			@Override
			public Object load() {
				return getList(columns, where, params);
			}
		});
	}
	@Override
	public <T extends Model<?>> T findOneByCache(String cacheKey,final Object id){
		return CacheKit.get(table(), cacheKey, new IDataLoader() {
			@Override
			public Object load() {
				return findById(id);
			}
		}); 
	}


	
}
