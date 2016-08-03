package net.risesoft.soa.filecube.service;

import java.util.List;
import net.risesoft.soa.filecube.model.FileOpenHistory;

public abstract interface OpenHistoryService
{
  public abstract void save(FileOpenHistory paramFileOpenHistory);

  public abstract List<FileOpenHistory> findByUserUid(String paramString);

  public abstract List<FileOpenHistory> findRelationOpenHistory(String paramString1, String paramString2);

  public abstract long findCountByFileUid(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.OpenHistoryService
 * JD-Core Version:    0.6.1
 */