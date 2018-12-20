package com.beiming.evidenceplatform.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import org.springframework.util.ResourceUtils;

/**
 * @Auther: tyrion
 * @Date: 2018/6/11 10:08
 * @类描述: 文书生成工具类
 */
public class DocumentUtil {

  /**
   * 受理通知书(简易) 生成
   */
  public void acceptInformBookCreate(String path, String pathFinal, Map<String, Object> dataMap)
      throws IOException, TemplateException {
    /** 初始化配置文件 **/
    @SuppressWarnings("deprecation")
    Configuration configuration = new Configuration();
    /** 设置编码 **/
    configuration.setDefaultEncoding("utf-8");
    String fileDirectory = ResourceUtils.getFile("classpath:tpl_document").getAbsolutePath();
    String docxTemplate = ResourceUtils
        .getFile("classpath:tpl_document/tpl_accept_inform_book.docx")
        .getAbsolutePath();
    /** 加载文件 **/
    configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
    /** 加载模板 **/
    Template template = configuration.getTemplate("tpl_accept_inform_book.ftl");

    /** 准备数据 **/

    /** 指定输出word文件的路径 **/
    String outFilePath = path;
    File docFile = new File(outFilePath);
    FileOutputStream fos = new FileOutputStream(docFile);
    Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
    template.process(dataMap, out);

    if (out != null) {
      out.close();
    }
    XmlToDocx docx = new XmlToDocx();
    docx.outDocx(new File(path), docxTemplate, pathFinal);

  }

}
