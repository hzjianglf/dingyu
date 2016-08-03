package net.risesoft.soa.filecube.service;

import egov.appservice.ac.model.Resource;
import java.util.List;
import java.util.Map;

public abstract interface PermissionService
{
  public abstract List<Map<String, Object>> getOperations(String paramString1, String paramString2);

  public abstract Boolean hasPermission(String paramString1, String paramString2, String paramString3);

  public abstract List<Map<String, Object>> getSubResourcesTree(String paramString1, String paramString2, String paramString3);

  public abstract List<Map<String, Object>> getResourceRoots(String paramString);

  public abstract List<Map<String, Object>> findFolder(String paramString1, String paramString2, String paramString3);

  public abstract boolean getPermissionByFileUid(String paramString1, String paramString2, String paramString3);

  public abstract boolean getPermissionByFolderUid(String paramString1, String paramString2, String paramString3);

  public abstract List<Resource> getSubResources(String paramString1, String paramString2, String paramString3, Map<String, String> paramMap);

  public abstract void grantPermission(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3);

  public abstract void grantPermission(String paramString1, String paramString2, String paramString3);

  public abstract void grantPermission(String paramString1, String paramString2, String[] paramArrayOfString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.PermissionService
 * JD-Core Version:    0.6.1
 */