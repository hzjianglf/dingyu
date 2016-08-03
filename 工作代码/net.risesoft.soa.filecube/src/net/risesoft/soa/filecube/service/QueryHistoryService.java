package net.risesoft.soa.filecube.service;

import java.util.List;
import net.risesoft.soa.filecube.model.FileQueryHistory;

public abstract interface QueryHistoryService
{
  public abstract void save(FileQueryHistory paramFileQueryHistory);

  public abstract List<FileQueryHistory> findByUserUid(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.QueryHistoryService
 * JD-Core Version:    0.6.1
 */