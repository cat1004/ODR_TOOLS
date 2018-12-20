package com.beiming.evidenceplatform.common.utils.evidenceplatform;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import javax.swing.*;

/**
 * @author : chenpeng
 * @date : 2018-08-22 17:30 存证函工具类,主要用于用户
 */
public class PDFLetterUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(PDFLetterUtils.class);

  //生成pdf 格式定义
  public static final Rectangle RECT = new Rectangle(PageSize.A4);
  public static final char[] PASSWORD = "123456".toCharArray(); // keystory密码

  /**
   * @param hash        hash值
   * @param time  时间
   * @param userName        用户真实名
   * @param voucherId 存证函uuid
   * @param fileName        文件名称
   */
  //生成存证函pdf(调用了添加签章与证书的方法)
  public static String generatePDF(String outDir, String hash, String time, String userName, String voucherId, String fileName, String fileId) throws Exception {
    BaseFont baseFont;
    baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
    Font evidenceid = new Font(baseFont, 14, Font.NORMAL);
    Font title = new Font(baseFont, 24, Font.BOLD);
    Font twotitle = new Font(baseFont, 16, Font.BOLD);
    Font text = new Font(baseFont, 16, Font.NORMAL);

    LOGGER.info("接收到的参数为:" + "OutScr:" + outDir + ", hash:" + hash + ",time:" + time + ",userName" + userName + ",voucherId" + voucherId + ",fileName" + fileName + ",fileId" + fileId);

    // 换行声明
    Font fontChinese18 = new Font(baseFont, 14, Font.BOLD);
    Paragraph ankrow = new Paragraph(14f, " ", fontChinese18);

    baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);


    Document document = new Document(RECT);
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outDir));
    document.open();


    // 添加文字  存证编号
    Chunk evidence = new Chunk("存证编号：", evidenceid);
    Chunk evidenceIdValue = new Chunk(voucherId, text);
    // 存证函编号
    Phrase phrase = new Phrase(20);
    phrase.add(evidence);
    // 空格暂时没有解决
    phrase.add(evidenceIdValue);
    document.add(phrase);
    phrase.clear();
    document.add(ankrow);

    document.add(Chunk.NEWLINE);

    Paragraph p = new Paragraph("电子数据存证函", title);
    p.setAlignment(Element.ALIGN_CENTER);
    document.add(p);

    document.add(Chunk.NEWLINE);

    Chunk nameTitle = new Chunk("文件名：", twotitle);
    Chunk fileNameValue = new Chunk(fileName, text);
    // 文件名定义
    phrase = new Phrase(20);
    phrase.add(nameTitle);
    // 空格暂时没有解决
    phrase.add(fileNameValue);
    document.add(phrase);
    phrase.clear();
    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk fileIdTitle = new Chunk("文件编号：", twotitle);
    Chunk fileIdValue = new Chunk(fileId, text);
    phrase.add(fileIdTitle);
    // 空格暂时没有解决
    phrase.add(fileIdValue);
    document.add(phrase);
    phrase.clear();
    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk userNameTitle = new Chunk("申请人：", twotitle);
    Chunk userNameValue = new Chunk(userName, text);
    phrase.add(userNameTitle);
    // 空格暂时没有解决
    phrase.add(userNameValue);
    document.add(phrase);
    phrase.clear();
    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk createTimeTitle = new Chunk("存证时间：", twotitle);
    Chunk createTimeValue = new Chunk(time, text);
    phrase.add(createTimeTitle);
    // 空格暂时没有解决
    phrase.add(createTimeValue);
    document.add(phrase);
    phrase.clear();
    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk hashTitle = new Chunk("数字指纹：", twotitle);
    Chunk hashValue = new Chunk(hash, text);
    phrase.add(hashTitle);
    // 空格暂时没有解决
    phrase.add(hashValue);
    document.add(phrase);
    phrase.clear();
    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk manif = new Chunk("声     明：", twotitle);
    phrase.add(manif);
    // 1、本文件已申请存证函证书，所有权益均属于申请人所有，未经授权，任何机构及个人不得转载、摘编或其他方式使用本文件内容，否则须承担一切法律后果。
    Paragraph p1 = new Paragraph("1、本文件已申请存证函证书，所有权益均属于申请人所有，未经授权，任何机构及个人不得转载、摘编或其他方式使用本文件内容，\n否则须承担一切法律后果。", text);
    phrase.add(p1);
    Paragraph p2 = new Paragraph("\n                        2、本证书中的存证时间与中国科学院授时授时中心的标准时间同步。", text);
    phrase.add(p2);

    Paragraph p3 = new Paragraph("\n                        3、本证书证明电子文件自存证后无增减、修改、破坏，确保文件的完整性、原始性。", text);
    phrase.add(p3);


    document.add(phrase);
    document.add(ankrow);
    phrase.clear();

    document.add(ankrow);
    document.add(ankrow);
    document.add(ankrow);

    Paragraph p4 = new Paragraph("北明存证平台" + time, text);
    p4.setAlignment(Element.ALIGN_RIGHT);

    document.close();
    writer.close();
    LOGGER.info("生成没有签章的证书完成");

    // 添加证书
    addition(outDir, outDir.substring(0, outDir.lastIndexOf(".")) + "存证函.pdf");
    String path = outDir.substring(0, outDir.lastIndexOf(".")) + "存证函.pdf";
    File file = new File(outDir);
    file.delete();
    return path;
  }

  //将静态资源加载,同时调用签章方法 // !!!待合并
  public static void addition(String src, String dest) throws Exception {
    try {
      // 读取keystore ，获得私钥和证书链
      LOGGER.info("加载签章资源");
      String keystore = ResourceUtils.getFile("classpath:client1.p12").getPath();
      KeyStore ks = KeyStore.getInstance("PKCS12");
      ks.load(new FileInputStream(keystore), PASSWORD);
      String alias = (String) ks.aliases().nextElement();
      PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
      Certificate[] chain = ks.getCertificateChain(alias);
      addStamper(
          src,
          String.format(dest, 3),
          chain,
          pk,
          DigestAlgorithms.SHA1,
          null,
          MakeSignature.CryptoStandard.CMS,
          "Test 3",
          "Ghent");
    } catch (Exception e) {
      LOGGER.error("加载签章资源失败,异常是:", e);
      JOptionPane.showMessageDialog(null, e.getMessage());

      e.printStackTrace();
    }
  }

  //添加签章 //!!!逗号改下 addStamper
  public static void addStamper(
      String src, // 需要签章的pdf文件路径
      String dest, // 签完章的pdf文件路径
      Certificate[] chain, // 证书链
      PrivateKey pk, // 签名私钥
      String digestAlgorithm, // 摘要算法名称，例如SHA-1
      String provider, // 密钥算法提供者，可以为null
      MakeSignature.CryptoStandard subfilter, // 数字签名格式，itext有2种
      String reason, // 签名的原因，显示在pdf签名属性中，随便填
      String location) // 签名的地点，显示在pdf签名属性中，随便填
      throws GeneralSecurityException, IOException, DocumentException {

    LOGGER.info("开始执行签章与证书流程");

    // 下边的步骤都是固定的，照着写就行了，没啥要解释的
    // Creating the reader and the stamper，开始pdfreader
    PdfReader reader = new PdfReader(src);
    // 目标文件输出流
    FileOutputStream os = new FileOutputStream(dest);
    // 创建签章工具PdfStamper ，最后一个boolean参数
    // false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
    // true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
    PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0', null, true);
    // 获取数字签章属性对象，设定数字签章的属性
    PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
    appearance.setReason(reason);
    appearance.setLocation(location);
    // 设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
    // 签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
    // 四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
    appearance.setVisibleSignature(new Rectangle(550, 150, 300, 300), 1, "sig1");
    // 读取图章图片，这个image是itext包的image
    String img = ResourceUtils.getFile("classpath:tim.jpg").getPath();
    Image image = Image.getInstance(img);
    appearance.setSignatureGraphic(image);
    appearance.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
    // 设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
    appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);

    // 这里的itext提供了2个用于签名的接口，可以自己实现，后边着重说这个实现
    // 摘要算法
    ExternalDigest digest = new BouncyCastleDigest();
    // 签名算法
    ExternalSignature signature = new PrivateKeySignature(pk, digestAlgorithm, null);
    // 调用itext签名方法完成pdf签章

    LOGGER.info("开始执行签名流程");
    MakeSignature.signDetached(
        appearance, digest, signature, chain, null, null, null, 0, subfilter);
  }

  //存证凭单生成 // !!! proof名字改为ticket
  public static boolean ticket(String evidenceId, String dest, String userName, String time, String fileName, String fileNameType, String length, String evidenceType, String clientType, String proofId, String hash, String voucherStauts) throws IOException, DocumentException {
    LOGGER.info("开始执行凭单生成流程");
    BaseFont baseFont;
    baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
    Font evidenceid = new Font(baseFont, 14, Font.NORMAL);
    Font title = new Font(baseFont, 24, Font.BOLD);
    Font twotitle = new Font(baseFont, 16, Font.BOLD);
    Font text = new Font(baseFont, 16, Font.NORMAL);
    Font message = new Font(baseFont, 16, Font.NORMAL, BaseColor.RED);
    // 换行声明
    Font fontChinese18 = new Font(baseFont, 14, Font.BOLD);
    Paragraph ankrow = new Paragraph(14f, " ", fontChinese18);

    // 创建文档实例
    Document document = new Document(RECT);
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
    document.open();

    document.add(ankrow);

    Paragraph p1 = new Paragraph("存证凭证单", title);
    p1.setAlignment(Element.ALIGN_CENTER);
    document.add(p1);
    document.add(Chunk.NEWLINE);


    Paragraph p2 = new Paragraph("兹证明用户" + userName + "于" + time + "秒于北明存证平台存储【" + fileName + "】文件，文件基本信息如下：", text);
    document.add(p2);


    Phrase phrase = new Phrase(20);
    Chunk fileId = new Chunk("名称：  ", twotitle);
    Chunk fileIdValue = new Chunk("【" + clientType + "】" + fileName + fileNameType, text);
    phrase.add(fileId);
    // 空格暂时没有解决
    phrase.add(fileIdValue);
    document.add(phrase);

    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk size = new Chunk("大小：", twotitle);
    Chunk sizeValue = new Chunk(length, text);
    phrase.add(size);
    // 空格暂时没有解决
    phrase.add(sizeValue);
    document.add(phrase);

    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk createTime = new Chunk("存证时间：", twotitle);
    Chunk createTimeValue = new Chunk(time, text);
    phrase.add(createTime);
    // 空格暂时没有解决
    phrase.add(createTimeValue);
    document.add(phrase);

    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk evidenceTypeTitle = new Chunk("来源：", twotitle);
    Chunk evidenceTypeValue = new Chunk(clientType, text);
    phrase.add(evidenceTypeTitle);
    // 空格暂时没有解决
    phrase.add(evidenceTypeValue);
    document.add(phrase);

    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk clientTypeTitle = new Chunk("类型：", twotitle);
    Chunk clientTypeValue = new Chunk(evidenceType, text);
    phrase.add(clientTypeTitle);
    // 空格暂时没有解决
    phrase.add(clientTypeValue);
    document.add(phrase);

    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk proofIdtitile = new Chunk("文件编号：", twotitle);
    Chunk proofIdValue = new Chunk(evidenceId, text);
    phrase.add(proofIdtitile);
    // 空格暂时没有解决
    phrase.add(proofIdValue);
    document.add(phrase);

    document.add(ankrow);

    phrase = new Phrase(20);
    Chunk hashtile = new Chunk("文件Hash：", twotitle);
    Chunk hashValue = new Chunk(hash, text);
    phrase.add(hashtile);
    // 空格暂时没有解决
    phrase.add(hashValue);
    document.add(phrase);

    document.add(ankrow);


    phrase = new Phrase(20);
    Chunk voucherIdtitle = new Chunk("存证函记录：", twotitle);
    Chunk voucherIdValue = new Chunk(voucherStauts, text);
    phrase.add(voucherIdtitle);
    // 空格暂时没有解决
    phrase.add(voucherIdValue);
    document.add(phrase);

    document.add(ankrow);

    Paragraph p3 = new Paragraph("该凭证不具备任何法律效力，仅供用户校对存储文件信息完整性使用", message);
    document.add(p3);
    document.add(ankrow);

    Paragraph p4 = new Paragraph(" 存单号：" + proofId + "\n北明存证平台\n" + time.substring(0, time.lastIndexOf("日") + 1), text);
    p4.setAlignment(Element.ALIGN_RIGHT);

    document.add(p4);
    document.close();
    writer.close();
    LOGGER.info("存证凭单生成完成");

    return true;

  }

}
