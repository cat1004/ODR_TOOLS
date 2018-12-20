package com.beiming.evidenceplatform.utils;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang3.StringUtils;

public class PdfUtil {
  public static boolean createPdf(String newFilePath, String no, String fileName, String code,
      String userName, String time, String sign) {
    boolean status = false;
    String xmlStr = "";
    try {
      xmlStr = "<?xml version='1.0' encoding='utf-8' ?>"
          + "<SealDocRequest><BASE_DATA><SYS_ID>sysId</SYS_ID>"
          + "<USER_ID>userId</USER_ID><USER_PSD>123456</USER_PSD>"
          + "</BASE_DATA><META_DATA> <IS_MERGER>false</IS_MERGER>"
          + "<MERGER_NO></MERGER_NO></META_DATA><FILE_LIST>"
          + "<TREE_NODE><FILE_NO>cunz.pdf</FILE_NO><IS_CODEBAR>false</IS_CODEBAR>"
          + "<CODEBAR_TYPE>0</CODEBAR_TYPE>"
          + "<CODEBAR_DATA>二维码条码内容，由业务系统提供数据，可以使用手机扫描识别内容</CODEBAR_DATA>"
          + "<X_COORDINATE>30000</X_COORDINATE><Y_COORDINATE>5000</Y_COORDINATE>"
          + "<SEAL_TYPE>0</SEAL_TYPE><RULE_TYPE>0</RULE_TYPE>"
          + "<RULE_NO>3</RULE_NO><RULE_INFO></RULE_INFO><CERT_NAME></CERT_NAME>"
          + "<CERT_PWD></CERT_PWD><SEAL_NAME></SEAL_NAME><SEAL_TYPE></SEAL_TYPE>"
          + "<MODEL_NAME>存证云</MODEL_NAME>"
          + "<CJ_TYPE>data</CJ_TYPE><REQUEST_TYPE>0</REQUEST_TYPE><FILE_PATH></FILE_PATH>"
          + "<FILE_BASE64></FILE_BASE64><ftp_address></ftp_address>"
          + "<ftp_port></ftp_port><ftp_user></ftp_user><ftp_pwd></ftp_pwd>"
          + "<ftp_openpath></ftp_openpath><ftp_savepath></ftp_savepath>"
          + "<AREA_SEAL>0</AREA_SEAL><AREA_DATA><Info>"
          + "<namestr>111111</namestr></Info></AREA_DATA><APP_DATA><Info>" + "<no>" + no + "</no>"
          + "<name>" + fileName + "</name>" + "<code>" + code + "</code>" + "<user>" + userName
          + "</user>" + "<time>" + time + "</time>" + "<sign>" + sign + "</sign>"
          + "<other>1、本文件已申请存证函证书，所有权利均属于申请人所有，未经授权，任何机构及个人不得转载、摘编或者其他方式使用本内容，否则许承担一切法律后果。\r\n2、本证书中的存证时间与中国科学XXXX中心同步。\r\n3、本证书证明电子文件子存证后无增减、修改、破坏，确保文件的完整性、原始性。</other>"
          + "</Info></APP_DATA></TREE_NODE></FILE_LIST></SealDocRequest>";
      String wsdlUrl = "http://121.42.226.56:9236/Seal/services/SealService?wsdl";
      String nameSpaceUri = "http://serv";
      Service service = new Service();
      Call call;
      call = (Call) service.createCall();
      call.setEncodingStyle("utf-8");
      call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));
      call.setOperationName(new QName(nameSpaceUri, "sealAutoPdf"));
      String s = (String) call.invoke(new Object[] {xmlStr});
      StringUtils.substringAfter(StringUtils.substringBeforeLast(s, "</FILE_MSG>"), "<FILE_MSG>");
      InputStream in =
          new URL("http://121.42.226.56:9236/Seal/doc/download.jsp?name=cunz.pdf").openStream();
      Files.copy(in, Paths.get(newFilePath), StandardCopyOption.REPLACE_EXISTING);
      status = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return status;
  }
}
