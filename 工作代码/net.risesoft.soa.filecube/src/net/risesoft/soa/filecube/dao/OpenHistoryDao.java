package net.risesoft.soa.filecube.dao;

import java.util.List;
import net.risesoft.soa.filecube.model.FileOpenHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface OpenHistoryDao extends JpaRepository<FileOpenHistory, String>
{
  @Query("from FileOpenHistory f where f.userUid = ?1")
  public abstract List<FileOpenHistory> findByUserUid(String paramString);

  @Query("from FileOpenHistory f where f.userUid = ?1 and f.fileInfo.fileUid = ?2")
  public abstract FileOpenHistory findByUserAndFile(String paramString1, String paramString2);

  @Query("select count(*) from FileOpenHistory f where f.fileInfo.fileUid = ?1")
  public abstract long findCountByFileUid(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.OpenHistoryDao
 * JD-Core Version:    0.6.1
 */