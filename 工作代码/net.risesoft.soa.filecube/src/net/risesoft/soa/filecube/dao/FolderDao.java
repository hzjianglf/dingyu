package net.risesoft.soa.filecube.dao;

import java.util.List;
import net.risesoft.soa.filecube.model.FileFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FolderDao extends JpaRepository<FileFolder, String>
{
  @Query("select f from FileFolder f where f.parentUid = ?1 and f.deleted = ?2")
  public abstract List<FileFolder> findByParentUid(String paramString, boolean paramBoolean);

  @Query("select f from FileFolder f where f.resourceUid = ?1 ")
  public abstract FileFolder findByResourceUid(String paramString);

  @Query("select f from FileFolder f where f.name = ?1 and f.folderLevel = ?2 ")
  public abstract FileFolder findByNameAndPath(String paramString1, String paramString2);

  @Query("select f from FileFolder f where f.name like ?1 and f.folderLevel like ?2 order by f.createDate")
  public abstract List<FileFolder> findByNameAndPName(String paramString1, String paramString2);

  @Query("select f from FileFolder f where f.name like ?1")
  public abstract List<FileFolder> findByName(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.FolderDao
 * JD-Core Version:    0.6.1
 */