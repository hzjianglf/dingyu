package net.risesoft.soa.info.dao;

import java.util.List;
import net.risesoft.soa.info.model.InformationFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface InformationFileDao extends JpaRepository<InformationFile, String>
{
  @Query("from InformationFile where instanceID = ?1 order by tabIndex")
  public abstract List<InformationFile> findByInstanceID(String paramString);

  @Query("from InformationFile where instanceID = ?1 and status = ?2 order by tabIndex")
  public abstract List<InformationFile> findByInstanceID(String paramString, int paramInt);

  @Query("from InformationFile where instanceID = ?1 and status = ?2 and fileType = ?3 order by tabIndex")
  public abstract List<InformationFile> findByInstanceID(String paramString1, int paramInt, String paramString2);
}