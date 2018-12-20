package com.beiming.evidenceplatform.common.utils.evidenceplatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Modified By: 压缩文件
 */
public class ZIPutils {

  private static final int BUFFER_SIZE = 2 * 1024;
  private static final Logger LOGGER = LoggerFactory.getLogger(ZIPutils.class);
  /**
   * @param outDir 压缩文件输出流
   * @param srcDir 压缩文件夹路径
   * @param keepDirStructure 是否保留原来的目录结构, true:保留目录结构;
   *        false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
   * @throws RuntimeException 压缩失败会抛出运行时异常
   */
  public static String toZip(List<String> srcDir, String outDir, boolean keepDirStructure)
      throws RuntimeException, Exception {
    LOGGER.info("zip压缩文件开始执行,传入的参数为srcDir:" + srcDir + ",outDir:" + outDir + "keepDirStructure:" + keepDirStructure);

    OutputStream out = new FileOutputStream(new File(outDir));

    long start = System.currentTimeMillis();
    ZipOutputStream zos = null;
    try {
      LOGGER.info("写入zip类型文件操作开始");
      zos = new ZipOutputStream(out);
      List<File> sourceFileList = new ArrayList<File>();
      for (String dir : srcDir) {
        File sourceFile = new File(dir);
        sourceFileList.add(sourceFile);
      }

      compress(sourceFileList, zos, keepDirStructure);
      long end = System.currentTimeMillis();
      LOGGER.info("压缩耗时" + (end - start) + " ms");

    } catch (Exception e) {
      LOGGER.error("ziputils出现异常,异常是:", e);
      throw new RuntimeException("zip error from ZipUtils", e);
    } finally {
      if (zos != null) {
        zos.close();
      }
    }
    return outDir;
  }

  /**
   * 递归压缩方法
   *
   * @param sourceFile 源文件
   * @param zos zip输出流
   * @param name 压缩后的名称
   * @param keepDirStructure 是否保留原来的目录结构, true:保留目录结构;
   *        false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
   */
  private static boolean compress(File sourceFile, ZipOutputStream zos, String name,
      boolean keepDirStructure) throws Exception {

    LOGGER.info("zip压缩文件compress方法执行,sourceFile:" + sourceFile + ",zos:" + zos + "name:" + name + ",keepDirStructure:" + keepDirStructure);

    byte[] buf = new byte[BUFFER_SIZE];
    if (sourceFile.isFile()) {
      zos.putNextEntry(new ZipEntry(name));
      int len;
      FileInputStream in = new FileInputStream(sourceFile);
      while ((len = in.read(buf)) != -1) {
        zos.write(buf, 0, len);
      }

      zos.closeEntry();
      in.close();
      return true;
    }
    File[] listFiles = sourceFile.listFiles();
    if ((listFiles == null || listFiles.length == 0) & keepDirStructure) {
      zos.putNextEntry(new ZipEntry(name + "/"));
      zos.closeEntry();
    }
    logicMethod(zos, listFiles, name, keepDirStructure);
    return true;
  }

  private static boolean compress(List<File> sourceFileList, ZipOutputStream zos,
      boolean keepDirStructure) throws Exception {
    byte[] buf = new byte[BUFFER_SIZE];
    LOGGER.info("zip压缩文件compress方法执行,sourceFileList:" + sourceFileList + ",zos:" + zos + ",keepDirStructure:" + keepDirStructure);

    for (File sourceFile : sourceFileList) {
      String name = sourceFile.getName();
      if (sourceFile.isFile()) {
        zos.putNextEntry(new ZipEntry(name));
        int len;
        FileInputStream in = new FileInputStream(sourceFile);
        while ((len = in.read(buf)) != -1) {
          zos.write(buf, 0, len);
        }
        zos.closeEntry();
        in.close();
        continue;
      }
      File[] listFiles = sourceFile.listFiles();
      if ((listFiles == null || listFiles.length == 0) & keepDirStructure) {
        zos.putNextEntry(new ZipEntry(name + "/"));
        zos.closeEntry();
      }
      logicMethod(zos, listFiles, name, keepDirStructure);
    }
    return true;
  }

  private static void logicMethod(ZipOutputStream zos, File[] listFiles, String name, boolean keepDirStructure) throws Exception {
    if (listFiles != null && listFiles.length != 0) {
      for (File file : listFiles) {
        if (keepDirStructure) {
          compress(file, zos, name + "/" + file.getName(), keepDirStructure);
        } else {
          compress(file, zos, file.getName(), keepDirStructure);
        }
      }
    }
  }
}
