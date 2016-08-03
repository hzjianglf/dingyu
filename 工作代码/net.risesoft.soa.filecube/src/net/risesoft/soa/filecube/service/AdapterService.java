package net.risesoft.soa.filecube.service;

import java.util.List;
import net.risesoft.soa.filecube.model.FileAdapter;

public abstract interface AdapterService
{
  public abstract FileAdapter findByUid(String paramString);

  public abstract FileAdapter findByName(String paramString);

  public abstract List<FileAdapter> findAll();

  public abstract void save(FileAdapter paramFileAdapter);

  public abstract void delete(FileAdapter paramFileAdapter);

  public abstract void deleteByUid(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.AdapterService
 * JD-Core Version:    0.6.1
 */