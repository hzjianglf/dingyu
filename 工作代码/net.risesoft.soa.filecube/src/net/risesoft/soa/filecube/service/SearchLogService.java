package net.risesoft.soa.filecube.service;

import java.util.List;
import net.risesoft.soa.filecube.model.FileSearchLog;

public abstract interface SearchLogService
{
  public abstract List<FileSearchLog> findHotKeyWords();

  public abstract void save(FileSearchLog paramFileSearchLog);

  public abstract List<FileSearchLog> findByKeyWords(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.SearchLogService
 * JD-Core Version:    0.6.1
 */