package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.PhotoFilesMapper;
import com.beiming.evidenceplatform.domain.PhotoFiles;
import com.beiming.evidenceplatform.service.PhotoFilesService;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * @author yanlulu
 * @version 创建时间：2018年6月29日 上午9:23:14 类说明
 */
@Service
public class PhotoFilesServiceImpl implements PhotoFilesService {

  @Resource
  private PhotoFilesMapper photoFilesMapper;

  @Override
  public List<PhotoFiles> getPhotosList(Long id, String column) {
    return photoFilesMapper.getPhotosList(id, column);
  }

  @Override
  public long savePhotoFiles(PhotoFiles photoFiles)  {
    return photoFilesMapper.savePhotoFiles(photoFiles);
  }

  @Override
  public void updatePhotoFiles(PhotoFiles photoFiles) {
    photoFilesMapper.updatePhotoFiles(photoFiles);
  }

  @Override
  public List<String> getPotoFileUrls(PhotoFiles photoFiles) {
    return photoFilesMapper.getPotoFileUrls(photoFiles);
  }
}
