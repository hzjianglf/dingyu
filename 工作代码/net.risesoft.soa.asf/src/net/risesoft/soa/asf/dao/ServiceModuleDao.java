package net.risesoft.soa.asf.dao;

import net.risesoft.soa.asf.model.ServiceModule;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface ServiceModuleDao extends JpaRepository<ServiceModule, String>
{
}