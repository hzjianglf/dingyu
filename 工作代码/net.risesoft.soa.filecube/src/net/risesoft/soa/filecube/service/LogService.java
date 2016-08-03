package net.risesoft.soa.filecube.service;

import java.util.List;
import net.risesoft.soa.filecube.model.FileLog;

public abstract interface LogService
{
  public abstract void save(FileLog paramFileLog);

  public abstract List<FileLog> findAll();

  public abstract List<FileLog> findAll(int paramInt1, int paramInt2);

  public abstract long count();
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.LogService
 * JD-Core Version:    0.6.1
 */