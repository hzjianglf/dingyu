package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.model.FileFavorites;
import net.risesoft.soa.filecube.model.FileFolder;
import net.risesoft.soa.filecube.model.FileInfo;
import net.risesoft.soa.filecube.service.FavoritesService;
import net.risesoft.soa.filecube.util.OperationType;
import net.risesoft.soa.filecube.util.StringUtils;
import net.risesoft.soa.framework.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class FavoriteAction extends BaseAction
{

  @Autowired
  private FavoritesService favoritesService;
  private FileFavorites favorites;
  private FileInfo fileInfo;
  private int start;
  private int rows;

  public void add()
  {
    this.favorites.setFavoriteDate(new Date());
    this.favorites.setUserUid(this.sessionUser.getUserUID());
    FileFavorites fav = this.favoritesService.save(this.favorites);
    if (this.favorites.getFileFolder() != null)
      saveSystemLog(OperationType.FC_ADD.name(), "收藏文件夹", 
        "文件夹标识：" + this.favorites.getFileFolder().getFolderUid());
    if (this.favorites.getFileInfo() != null) {
      saveSystemLog(OperationType.FC_ADD.name(), "收藏文件", 
        "文件标识：" + this.favorites.getFileInfo().getFileUid());
    }

    printJson(fav);
  }

  public void delete()
  {
    this.favoritesService.delete(this.favorites.getFavoritesUid());
    saveSystemLog(OperationType.FC_ADD.name(), "取消收藏", "记录唯一标识：" + this.favorites.getFavoritesUid());
    printJson("success", "ok");
  }

  public void isFavorited()
  {
    List all = this.favoritesService.findByUserAndFile(
      this.sessionUser.getUserUID(), this.fileInfo.getFileUid());

    if ((all != null) && (!all.isEmpty()))
      printJson("favorited", ((FileFavorites)all.get(0)).getFavoritesUid());
    else
      printJson("favorited", "notFavorited");
  }

  public String preList()
  {
    Map m = this.favoritesService.findByUserUid(this.sessionUser.getUserUID(), this.start, this.rows);
    List favoritesAlls = (List)m.get("datas");
    ActionContext.getContext().getContextMap().put("favoritesAlls", favoritesAlls);
    ActionContext.getContext().getContextMap().put("start", Integer.valueOf(this.start));
    ActionContext.getContext().getContextMap().put("rows", Integer.valueOf(this.rows));
    ActionContext.getContext().getContextMap().put("totalCount", m.get("totalCount"));
    saveSystemLog(OperationType.FC_VIEW.toString(), "查看收藏记录", "查看自己收藏记录！");
    return "preList";
  }

  public void relationFavorite()
  {
    List<FileFavorites> favoritesAlls = 
      this.favoritesService.findRelationFavorite(this.fileInfo.getFileUid(), this.sessionUser.getUserUID());
    List rtnList = new ArrayList();

    for (FileFavorites favorites : favoritesAlls) {
      Map rtnMap = new HashMap();
      rtnMap.put("fileUid", favorites.getFileInfo().getFileUid());
      String fileName = favorites.getFileInfo().getName();
      rtnMap.put("fileName", fileName);
      rtnMap.put("fileShortName", StringUtils.getShortStr(fileName, 10));
      rtnMap.put("fileExtension", favorites.getFileInfo().getExtension());
      rtnList.add(rtnMap);
    }
    printJsonArray(rtnList);
  }

  public FileFavorites getFavorites() {
    return this.favorites;
  }

  public void setFavorites(FileFavorites favorites) {
    this.favorites = favorites;
  }

  public FileInfo getFileInfo() {
    return this.fileInfo;
  }

  public void setFileInfo(FileInfo fileInfo) {
    this.fileInfo = fileInfo;
  }

  public int getStart() {
    return this.start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getRows() {
    return this.rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }
}