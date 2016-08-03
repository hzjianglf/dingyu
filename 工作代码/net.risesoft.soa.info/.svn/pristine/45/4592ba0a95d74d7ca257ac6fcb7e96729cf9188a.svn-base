package net.risesoft.soa.info.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import net.risesoft.soa.framework.util.StreamUtil;
import net.risesoft.soa.info.dao.InformationFileDao;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("infoFileManager")
public class InfoFileManager
{

  @Autowired
  public InformationFileDao informationFileDao;

  @Transactional
  public void save(InformationFile informationFile, File file)
    throws Exception
  {
    FileInputStream fis = null;
    try {
      if (file != null) {
        fis = new FileInputStream(file);
        String path = ConfigUtil.filePath + informationFile.getRealPath() + informationFile.getId() + informationFile.getFileNameExt();
        File newFile = new File(path);
        StreamUtil.writeToFileAndClose(fis, newFile);
      }
      this.informationFileDao.save(informationFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new Exception("文件不存在!");
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("保存失败!");
    } finally {
      if (fis != null)
        fis.close();
    }
  }

  @Transactional
  public void saveFormInputStream(InformationFile informationFile, InputStream is) throws Exception
  {
    try {
      if (is != null) {
        String path = ConfigUtil.filePath + informationFile.getRealPath() + informationFile.getId() + informationFile.getFileNameExt();
        File newFile = new File(path);
        StreamUtil.writeToFileAndClose(is, newFile);
      }
      this.informationFileDao.save(informationFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new Exception("文件不存在!");
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("保存失败!");
    } finally {
      if (is != null)
        is.close();
    }
  }

  @Transactional
  public void update(InformationFile informationFile) throws Exception
  {
    try {
      this.informationFileDao.save(informationFile);
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("保存失败!");
    }
  }

  public InformationFile get(String id) {
    return ((InformationFile)this.informationFileDao.findOne(id));
  }

  @Transactional
  public void delete(String id) throws Exception {
    this.informationFileDao.delete(id);
  }

  public List<InformationFile> find(String instanceID) {
    return this.informationFileDao.findByInstanceID(instanceID);
  }

  public List<InformationFile> find(String instanceID, int status) {
    return this.informationFileDao.findByInstanceID(instanceID, status);
  }

  public List<InformationFile> find(String instanceID, int status, String fileType) {
    return this.informationFileDao.findByInstanceID(instanceID, status, fileType);
  }
}