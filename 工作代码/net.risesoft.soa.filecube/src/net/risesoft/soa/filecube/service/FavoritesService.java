package net.risesoft.soa.filecube.service;

import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.model.FileFavorites;

public abstract interface FavoritesService
{
  public abstract FileFavorites save(FileFavorites paramFileFavorites);

  public abstract void delete(String paramString);

  public abstract Map<String, Object> findByUserUid(String paramString, int paramInt1, int paramInt2);

  public abstract List<FileFavorites> findByUserAndFile(String paramString1, String paramString2);

  public abstract List<FileFavorites> findRelationFavorite(String paramString1, String paramString2);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.FavoritesService
 * JD-Core Version:    0.6.1
 */