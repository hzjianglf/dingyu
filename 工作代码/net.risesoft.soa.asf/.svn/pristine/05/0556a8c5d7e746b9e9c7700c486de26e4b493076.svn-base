package net.risesoft.soa.asf.dao;

import java.util.List;
import net.risesoft.soa.asf.model.ServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public abstract interface ServiceLogDao extends JpaRepository<ServiceLog, String>, JpaSpecificationExecutor<ServiceLog>
{
  @Query("select distinct l.object from net.risesoft.soa.asf.model.ServiceLog l")
  public abstract List<String> cmpNames();
}