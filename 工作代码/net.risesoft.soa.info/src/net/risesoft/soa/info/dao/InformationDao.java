package net.risesoft.soa.info.dao;

import java.util.List;
import net.risesoft.soa.info.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface InformationDao extends JpaRepository<Information, String>
{
  @Query("from Information where dn like ?1")
  public abstract List<Information> findByDN(String paramString);

  @Query("update Information set dn = replace(dn, ?1, ?2) where dn like ?3")
  public abstract void replaceDN(String paramString1, String paramString2, String paramString3);
}