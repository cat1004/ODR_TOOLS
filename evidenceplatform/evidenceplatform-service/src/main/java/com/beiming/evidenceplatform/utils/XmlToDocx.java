package com.beiming.evidenceplatform.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
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
import org.docx4j.wml.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlToDocx {

  private Logger logger = LoggerFactory.getLogger(getClass());

  public XmlToDocx() {
    // TODO Auto-generated constructor stub
  }


  /**
   * @param documentFile 动态生成数据的docunment.xml文件
   * @param docxTemplate docx的模板
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
      logger.error("XmlToDocx outDocx error {}", e);
    }
  }

  /**
   * @param templatePath 模板地址
   * @param targetPath 生成后文件地址
   * @param imagePath 图片地址
   * @param bookmarkName 签名地址
   * @author xieguangliu
   */
  public void addImg(String templatePath, String targetPath, String imagePath, String bookmarkName)
      throws Exception {

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
    // imagePath = "F:\\bm_eclipse\\aa.png";
    // 遍历书签
    for (CTBookmark bm : rt.getStarts()) {
      // 这儿可以对单个书签进行操作，也可以用一个map对所有的书签进行处理
      if (bm.getName().equals(bookmarkName)) {
        // 读入图片并转化为字节数组，因为docx4j只能字节数组的方式插入图片
        InputStream is = new FileInputStream(imagePath);
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
  }

  public void addImgAll(String templatePath, String targetPath, String imagePathPro,
      String imagePathRep,
      String bookmarkNamePro, String bookmarkNameRep) throws Exception {
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
      if (bm.getName().equals(bookmarkNamePro)) {
        // 读入图片并转化为字节数组，因为docx4j只能字节数组的方式插入图片
        InputStream is = new FileInputStream(imagePathPro);
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
      if (bm.getName().equals(bookmarkNameRep)) {
        // 读入图片并转化为字节数组，因为docx4j只能字节数组的方式插入图片
        InputStream is = new FileInputStream(imagePathRep);
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
  }

  /**
   * @param templatePath 模板地址
   * @param targetPath 生成后文件地址
   * @author xieguangliu
   */
  public void addDate(String templatePath, String targetPath) throws Exception {

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
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    int date = c.get(Calendar.DATE);
    // 遍历书签
    for (CTBookmark bm : rt.getStarts()) {
      // 这儿可以对单个书签进行操作，也可以用一个map对所有的书签进行处理
      if (bm.getName().equals("day")) {
        // 获取该书签的父级段落
        P p = (P) (bm.getParent());
        ObjectFactory factory = new ObjectFactory();
        R run = factory.createR();
        Text t = new Text();
        t.setValue(year + " 年" + month + " 月" + date + " 日");
        run.getContent().add(t);
        p.getContent().add(run);
      }
    }
    wPackage.save(new FileOutputStream(targetPath));
  }

}
