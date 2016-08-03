package net.risesoft.soa.filecube.service;

import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.model.FileShare;

public abstract interface ShareService
{
  public abstract void save(FileShare paramFileShare);

  public abstract void save(List<FileShare> paramList);

  public abstract void delete(String paramString);

  public abstract Map<String, Object> findByUserUid(String paramString, int paramInt1, int paramInt2);

  public abstract List<FileShare> findByUserUidAndFileUid(String paramString1, String paramString2);

  public abstract Map<String, Object> shareToMeList(String paramString, int paramInt1, int paramInt2);

  public abstract boolean shareToMe(String paramString1, String paramString2);

  public abstract void deleteOne(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.ShareService
 * JD-Core Version:    0.6.1
 */