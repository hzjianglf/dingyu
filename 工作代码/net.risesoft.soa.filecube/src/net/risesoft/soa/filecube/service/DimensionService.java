package net.risesoft.soa.filecube.service;

import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.model.Dimension;

public abstract interface DimensionService
{
  public abstract List<String> getDimension(String paramString);

  public abstract List<Dimension> getDimensionTree(List<Dimension> paramList);

  public abstract Map<String, Object> getDimensionFiles(String paramString, int paramInt1, int paramInt2);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.DimensionService
 * JD-Core Version:    0.6.1
 */