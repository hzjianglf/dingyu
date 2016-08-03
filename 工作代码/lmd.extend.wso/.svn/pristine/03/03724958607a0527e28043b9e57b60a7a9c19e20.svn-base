package lmd.extend.wso.dao;

 import java.util.List;
 import lmd.extend.wso.model.MonitorMessFlow;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.jpa.repository.QueryHints;
 import org.springframework.stereotype.Repository;

 @Repository
 public abstract interface MonitorMFDao extends JpaRepository<MonitorMessFlow, String>, JpaSpecificationExecutor<MonitorMessFlow>
 {
  
//   @Query("select count(*) from MonitorMessFlow mmf where mmf.updatedate like ?1")
     @Query("select count(*) from MonitorMessFlow mmf where  to_char(mmf.updatedate,'yyyy-MM-dd HH:mm:ss')> ?1 and to_char(mmf.updatedate,'yyyy-MM-dd HH:mm:ss')< ?2")
     @QueryHints({@javax.persistence.QueryHint(name="org.hibernate.cacheable", value="true")})
     public abstract int findByMessFlow(String paramString,String paramString2);

 }