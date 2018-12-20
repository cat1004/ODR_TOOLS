package com.beiming.evidenceplatform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.beiming.evidenceplatform.domain.PhotoFiles;

import tk.mybatis.mapper.common.Mapper;

public interface PhotoFilesMapper extends Mapper<PhotoFiles> {

  /**
   * column对应字段，id对应字段id
   */
  List<PhotoFiles> getPhotosList(@Param("id") Long id, @Param("column") String column);

  /**
   * 删除图片
   */
  void updatePhotoFiles(PhotoFiles photoFiles);

  /**
   * 保存图片
   * @param photoFiles
   * @return
   */
  long savePhotoFiles(PhotoFiles photoFiles);


  /**
   * 获取图片的url
   * @param photoFiles
   * @return
   */
  List<String> getPotoFileUrls(PhotoFiles photoFiles);
}
