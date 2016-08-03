package net.risesoft.soa.filecube.service;

import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.model.FileFolder;

public abstract interface FolderService
{
  public abstract List<FileFolder> findByParentUid(String paramString);

  public abstract FileFolder save(FileFolder paramFileFolder);

  public abstract void saveFolderAndResource(FileFolder paramFileFolder);

  public abstract void saveAndCreateResouces(List<FileFolder> paramList);

  public abstract List<Map<String, Object>> listFiles(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4);

  public abstract FileFolder findById(String paramString);

  public abstract List<FileFolder> findByName(String paramString1, String paramString2);

  public abstract void findParents(List<FileFolder> paramList, String paramString);

  public abstract void delete(String paramString);

  public abstract void deleteFolderAndResource(String paramString);

  public abstract void batchDeleteFolderAndResource(String paramString);

  public abstract void batchDelete(String paramString);

  public abstract void logicDelete(String paramString);

  public abstract Map<String, Object> globalFolderTree();

  public abstract FileFolder getDepartMentFolderRoot(String paramString);

  public abstract Map<String, Object> departMentFolderTree(String paramString);

  public abstract Map<String, Object> allDepartMentFolderTree();

  public abstract List<FileFolder> findParentsByUid(String paramString);

  public abstract void moves(String paramString, FileFolder paramFileFolder);

  public abstract void moveFolderAndResource(String paramString, FileFolder paramFileFolder);

  public abstract void batchLogicDelete(String paramString);

  public abstract Map<String, Object> personnelFolderTree(String paramString1, String paramString2);

  public abstract FileFolder findByResourceUid(String paramString);

  public abstract void update(FileFolder paramFileFolder);

  public abstract void updateFolderAndResource(FileFolder paramFileFolder);

  public abstract void createResource(FileFolder paramFileFolder);

  public abstract FileFolder findByNameAndPath(String paramString1, String paramString2);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.FolderService
 * JD-Core Version:    0.6.1
 */