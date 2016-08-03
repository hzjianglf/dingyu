package net.risesoft.soa.filecube.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.model.FileFolder;
import net.risesoft.soa.filecube.model.FileInfo;

public abstract interface FileService
{
  public abstract List<FileInfo> findByFolderUid(String paramString);

  public abstract void convertFile(String paramString);

  public abstract FileInfo save(FileInfo paramFileInfo);

  public abstract void logicDelete(String paramString);

  public abstract void delete(String paramString);

  public abstract void batchDelete(String paramString);

  public abstract FileInfo findById(String paramString);

  public abstract void update(FileInfo paramFileInfo);

  public abstract void moves(String paramString, FileFolder paramFileFolder);

  public abstract FileInfo save(FileInfo paramFileInfo, File paramFile);

  public abstract FileInfo save(FileInfo paramFileInfo, InputStream paramInputStream);

  public abstract List<Map<String, Object>> findDeleteFileByUserUid(String paramString);

  public abstract void batchLogicDelete(String paramString);

  public abstract List<FileInfo> findByProperty(int paramInt1, int paramInt2, String paramString, Object paramObject);

  public abstract List<FileInfo> findByProperties(int paramInt1, int paramInt2, Map<String, Object> paramMap);

  public abstract void createResource(FileInfo paramFileInfo);

  public abstract void deleteImportFile(String paramString1, String paramString2);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.FileService
 * JD-Core Version:    0.6.1
 */