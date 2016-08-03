package net.risesoft.soa.filecube.service;

import java.util.List;
import net.risesoft.soa.filecube.model.FileCommentary;

public abstract interface CommentaryService
{
  public abstract FileCommentary save(FileCommentary paramFileCommentary);

  public abstract List<FileCommentary> findByFileUid(String paramString);

  public abstract void delete(String paramString);

  public abstract FileCommentary findById(String paramString);

  public abstract List<FileCommentary> findUserCount(String paramString);
}

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.CommentaryService
 * JD-Core Version:    0.6.1
 */