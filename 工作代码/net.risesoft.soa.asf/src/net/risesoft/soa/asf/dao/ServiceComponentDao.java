package net.risesoft.soa.asf.dao;

import net.risesoft.soa.asf.model.ServiceComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface ServiceComponentDao extends JpaRepository<ServiceComponent, String>
{
}