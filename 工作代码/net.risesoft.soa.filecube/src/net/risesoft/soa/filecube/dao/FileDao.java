package net.risesoft.soa.filecube.dao;

import java.util.List;
import net.risesoft.soa.filecube.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FileDao extends JpaRepository<FileInfo, String>
{
  @Query(" select f from FileInfo f where f.fileFolder.folderUid = ?1")
  public abstract List<FileInfo> findByFolderUid(String paramString);

  @Query(" delete from FileInfo f where f.app =?1 and f.importUid = ?2")
  public abstract void deleteImportFile(String paramString1, String paramString2);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.FileDao
 * JD-Core Version:    0.6.1
 */