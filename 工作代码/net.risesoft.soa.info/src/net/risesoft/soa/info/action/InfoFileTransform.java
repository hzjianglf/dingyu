package net.risesoft.soa.info.action;

import java.io.File;
import net.risesoft.soa.info.dao.InformationFileDao;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.ConfigUtil;
import net.risesoft.soa.info.util.DocumentToSWF;
import net.risesoft.soa.info.util.FileContentType;

public class InfoFileTransform extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private String id;
  private String type;
  private InformationFile informationFile;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String execute() throws Exception
  {
    InformationFileDao informationFileDao = (InformationFileDao)SpringUtil.getBean("informationFileDao");
    if ("file".equals(this.type)) {
      this.informationFile = ((InformationFile)informationFileDao.findOne(this.id));
      String path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + ".swf";
      File file = new File(path);
      if (!(file.exists())) {
        String tempPath = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
        DocumentToSWF docToSWF;
        if (FileContentType.isOfficeType(this.informationFile.getFileNameExt().toLowerCase())) {
          docToSWF = new DocumentToSWF(tempPath);
          docToSWF.docToswf();
        }
        if (FileContentType.isPdfType(this.informationFile.getFileNameExt().toLowerCase())) {
          docToSWF = new DocumentToSWF(tempPath);
          docToSWF.pdfToswf(false);
        }
      }
    }
    return this.type;
  }
}