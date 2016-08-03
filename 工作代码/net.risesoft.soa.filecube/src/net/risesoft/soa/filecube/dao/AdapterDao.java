package net.risesoft.soa.filecube.dao;

import net.risesoft.soa.filecube.model.FileAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface AdapterDao extends JpaRepository<FileAdapter, String>
{
  @Query(" from FileAdapter a where a.name =?1 ")
  public abstract FileAdapter findByName(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.dao.AdapterDao
 * JD-Core Version:    0.6.1
 */