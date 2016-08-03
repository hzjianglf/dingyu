package net.risesoft.soa.filecube.service;

import java.io.InputStream;
import java.util.List;
import net.risesoft.soa.filecube.model.FileAttachment;

public abstract interface AttachmentService
{
  public abstract FileAttachment findById(String paramString);

  public abstract void convertFile(String paramString);

  public abstract void save(FileAttachment paramFileAttachment, InputStream paramInputStream);

  public abstract FileAttachment save(FileAttachment paramFileAttachment);

  public abstract List<FileAttachment> findByFileUid(String paramString);

  public abstract void delete(String paramString);

  public abstract void update(FileAttachment paramFileAttachment);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.AttachmentService
 * JD-Core Version:    0.6.1
 */