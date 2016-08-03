package net.risesoft.soa.filecube.dao;

import java.util.List;
import net.risesoft.soa.filecube.model.FileSearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface SearchLogDao extends JpaRepository<FileSearchLog, String>
{
  @Query("select l from FileSearchLog l where l.keyWords = ?1")
  public abstract List<FileSearchLog> findByKeyWords(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.SearchLogDao
 * JD-Core Version:    0.6.1
 */