package net.risesoft.soa.filecube.dao;

import net.risesoft.soa.filecube.model.FileAdapterLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface AdapterLogDao extends JpaRepository<FileAdapterLog, String>
{
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.AdapterLogDao
 * JD-Core Version:    0.6.1
 */