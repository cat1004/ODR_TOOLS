package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.PhotoFiles;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月29日 上午9:22:05 图片操作类
 */
public interface PhotoFilesService {

  public List<PhotoFiles> getPhotosList(Long id, String column);

  long savePhotoFiles(PhotoFiles photoFiles);

  void updatePhotoFiles(PhotoFiles photoFiles);

  List<String> getPotoFileUrls(PhotoFiles photoFiles);
}
