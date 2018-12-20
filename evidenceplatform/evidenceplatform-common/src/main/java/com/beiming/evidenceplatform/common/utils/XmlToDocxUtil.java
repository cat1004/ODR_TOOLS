package com.beiming.evidenceplatform.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.docx4j.TraversalUtil;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.finders.RangeFinder;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.wml.Body;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.Document;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * xml和doc转换工具
 */
public class XmlToDocxUtil {

  public XmlToDocxUtil() {
  }
/*
  public static void main(String[] args) throws IOException {

    try {
      XmlToDocxUtil docx = new XmlToDocxUtil();
      String templatePath = "";
      String targetPath = "";
      MultipartFile imgFile = null;
      String bookmarkName = "";
      docx.addImg(templatePath, targetPath, imgFile, bookmarkName);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }*/

  /**
   * 转为doc
   *
   * @param documentFile 动态生成数据的docunment.xml文件
   * @param docxTemplate docx的模板
   * @param toFilePath 需要导出的文件路径
   */
  public void outDocx(File documentFile, String docxTemplate, String toFilePath)
      throws ZipException, IOException {
    try {
      File docxFile = new File(docxTemplate);
      ZipFile zipFile = new ZipFile(docxFile);
      Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
      ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(toFilePath));
      int len = -1;
      byte[] buffer = new byte[1024];
      while (zipEntrys.hasMoreElements()) {
        ZipEntry next = zipEntrys.nextElement();
        InputStream is = zipFile.getInputStream(next);
        // 把输入流的文件传到输出流中 如果是word/document.xml由我们输入
        zipout.putNextEntry(new ZipEntry(next.toString()));
        if ("word/document.xml".equals(next.toString())) {
          InputStream in = new FileInputStream(documentFile);
          while ((len = in.read(buffer)) != -1) {
            zipout.write(buffer, 0, len);
          }
          in.close();
        } else {
          while ((len = is.read(buffer)) != -1) {
            zipout.write(buffer, 0, len);
          }
          is.close();
        }
      }
      zipout.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 合并图片
   *
   * @param templatePath 需要修改的文件的地址
   * @param targetPath 生成后文件地址
   * @param imgFile 图片文件
   * @param bookmarkName 模板签名书签
   * @return 成功true
   * @throws Exception
   */
  public Boolean addImg(String templatePath, String targetPath, MultipartFile imgFile,
      String bookmarkName) throws Exception {

    // 载入模板文件
    WordprocessingMLPackage wPackage = WordprocessingMLPackage
        .load(new FileInputStream(templatePath));
    // 提取正文
    MainDocumentPart mainDocumentPart = wPackage.getMainDocumentPart();
    Document wmlDoc = (Document) mainDocumentPart.getJaxbElement();
    Body body = wmlDoc.getBody();
    // 提取正文中所有段落
    List<Object> paragraphs = body.getContent();
    // 提取书签并创建书签的游标
    RangeFinder rt = new RangeFinder("CTBookmark", "CTMarkupRange");
    new TraversalUtil(paragraphs, rt);
    // 遍历书签
    for (CTBookmark bm : rt.getStarts()) {
      // 这儿可以对单个书签进行操作，也可以用一个map对所有的书签进行处理
      if (bm.getName().equals(bookmarkName)) {
        // 读入图片并转化为字节数组，因为docx4j只能字节数组的方式插入图片
        InputStream is = new BufferedInputStream(imgFile.getInputStream());
        byte[] bytes = IOUtils.toByteArray(is);
        // 穿件一个行内图片
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage
            .createImagePart(wPackage, bytes);

        // createImageInline函数的前四个参数我都没有找到具体啥意思，，，，
        // 最有一个是限制图片的宽度，缩放的依据
        Inline inline = imagePart.createImageInline(null, null, 0, 1, 800, false);
        // 获取该书签的父级段落
        P p = (P) (bm.getParent());

        ObjectFactory factory = new ObjectFactory();
        // R对象是匿名的复杂类型，然而我并不知道具体啥意思，估计这个要好好去看看ooxml才知道
        R run = factory.createR();
        // drawing理解为画布？
        Drawing drawing = factory.createDrawing();
        drawing.getAnchorOrInline().add(inline);
        run.getContent().add(drawing);
        p.getContent().add(run);
      }
    }
    wPackage.save(new FileOutputStream(targetPath));
    return true;
  }

}
