package net.risesoft.soa.info.dao;

import java.util.List;
import net.risesoft.soa.info.model.InformationIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface InformationIndexDao extends JpaRepository<InformationIndex, String>
{
  @Query("select count(id) from InformationIndex where infoID = ?1")
  public abstract long getCount(String paramString);

  @Query("from InformationIndex where infoID = ?1 and instance = ?2")
  public abstract List<InformationIndex> findByInstance(String paramString1, String paramString2);
}