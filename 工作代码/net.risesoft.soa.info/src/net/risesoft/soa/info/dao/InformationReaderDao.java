package net.risesoft.soa.info.dao;

import net.risesoft.soa.info.model.InformationReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface InformationReaderDao extends JpaRepository<InformationReader, String>
{
}