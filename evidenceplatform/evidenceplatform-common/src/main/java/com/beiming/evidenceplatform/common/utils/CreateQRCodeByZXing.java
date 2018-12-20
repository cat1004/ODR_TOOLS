package com.beiming.evidenceplatform.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * package: com.beiming.libra.common.utils describe: TODO create_user: xiet create_date: 2018/5/9
 * create_time: 上午9:29
 **/
public class CreateQRCodeByZXing {

  public static void main(String[] args) {

    int width = 300;
    int height = 300;

    String format = "png";
    //这里如果你想自动跳转的话，需要加上https://
    String content = "https://www.baidu.com";

    HashMap hits = new HashMap();
    //编码
    hits.put(EncodeHintType.CHARACTER_SET, "utf-8");
    //纠错等级，纠错等级越高存储信息越少
    hits.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
    //边距
    hits.put(EncodeHintType.MARGIN, 2);

    try {
      BitMatrix bitMatrix = new MultiFormatWriter()
          .encode(content, BarcodeFormat.QR_CODE, width, height, hits);
      //如果做网页版输出可以用输出到流
//      MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
      Path path = new File("/Users/xiet/Pictures/zxingQRCode.png").toPath();
      MatrixToImageWriter.writeToPath(bitMatrix, format, path);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("that is all");
  }
}
