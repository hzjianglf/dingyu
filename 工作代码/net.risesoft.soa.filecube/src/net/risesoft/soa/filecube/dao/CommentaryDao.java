package net.risesoft.soa.filecube.dao;

import java.util.List;
import net.risesoft.soa.filecube.model.FileCommentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface CommentaryDao extends JpaRepository<FileCommentary, String>
{
  @Query("select f from FileCommentary f where f.fileInfo.fileUid = ?1 order by f.createDate desc ")
  public abstract List<FileCommentary> findByFileUid(String paramString);

  @Query("select f.userUid from FileCommentary f where f.fileInfo.fileUid = ?1 GROUP BY f.userUid")
  public abstract List<FileCommentary> findUserCount(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.CommentaryDao
 * JD-Core Version:    0.6.1
 */