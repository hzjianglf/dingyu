package net.risesoft.soa.info.dao;

import java.util.List;
import net.risesoft.soa.info.model.InformationDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface InformationDescDao extends JpaRepository<InformationDesc, String>
{
  @Query("from InformationDesc where infoID = ?1 order by tabIndex")
  public abstract List<InformationDesc> findByInfoID(String paramString);
}