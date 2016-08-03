package net.risesoft.soa.filecube.dao;

import java.util.List;
import net.risesoft.soa.filecube.model.FileShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface ShareDao extends JpaRepository<FileShare, String>
{
  @Query(" select f from FileShare f where f.userUid = ?1")
  public abstract List<FileShare> findByUserUid(String paramString);

  @Query(" select f from FileShare f where f.userUid = ?1 and (f.fileInfo.fileUid = ?2 or f.fileFolder.folderUid = ?2)")
  public abstract List<FileShare> findByUserUidAndFileUid(String paramString1, String paramString2);

  @Query(" select f from FileShare f where f.orgUid in(?1) and (f.fileInfo.fileUid = ?2 or f.fileFolder.folderUid = ?2) and f.cancelShareMe = ?3")
  public abstract FileShare shareToMe(String paramString1, String paramString2, boolean paramBoolean);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.ShareDao
 * JD-Core Version:    0.6.1
 */