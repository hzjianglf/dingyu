package net.risesoft.soa.filecube.dao;

import java.util.List;
import net.risesoft.soa.filecube.model.FileFavorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FavoritesDao extends JpaRepository<FileFavorites, String>
{
  @Query(" select f from FileFavorites f where f.userUid = ?1")
  public abstract List<FileFavorites> findByUserUid(String paramString);

  @Query(" select f from FileFavorites f where f.userUid = ?1 and (f.fileInfo.fileUid in (?2) or f.fileFolder.folderUid in (?2) )")
  public abstract List<FileFavorites> findByUserAndFile(String paramString, List<String> paramList);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.FavoritesDao
 * JD-Core Version:    0.6.1
 */