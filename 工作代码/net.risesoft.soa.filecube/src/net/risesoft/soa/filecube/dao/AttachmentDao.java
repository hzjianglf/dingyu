package net.risesoft.soa.filecube.dao;

import java.util.List;
import net.risesoft.soa.filecube.model.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface AttachmentDao extends JpaRepository<FileAttachment, String>
{
  @Query("select f from FileAttachment f where f.fileInfo.fileUid = ?1 and f.deleted = ?2")
  public abstract List<FileAttachment> findByFileUid(String paramString, boolean paramBoolean);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.AttachmentDao
 * JD-Core Version:    0.6.1
 */