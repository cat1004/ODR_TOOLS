package com.beiming.evidenceplatform.common.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.beiming.evidenceplatform.common.constants.ExcelConstants;

/**
 * excel 解析
 *
 * @author tp Date : 2018/6/6/006 15:43
 */
public class ExcelUtil {

  /**
   * 2003- 版本的excel
   */
  private static final String XLS = ".xls";
  /**
   * 2007+ 版本的excel
   */
  private static final String XLSX = ".xlsx";

  /**
   * 根据文件后缀，自适应上传文件的版本
   */
  private static Workbook getWorkbook(String filePath, String filename) throws Exception {
    Workbook workbook;
    String fileType = filename.substring(filename.lastIndexOf("."));
    InputStream inputStream = new FileInputStream(filePath);
    if (XLS.equals(fileType)) {
      //2003-
      workbook = new HSSFWorkbook(inputStream);
    } else if (XLSX.equals(fileType)) {
      //2007+
      workbook = new XSSFWorkbook(inputStream);
    } else {
      throw new Exception("解析的文件格式有误");
    }
    return workbook;
  }

  /**
   * @param @param path
   * @param @throws Exception    设定文件
   * @return List<Map                                                                                                                               <
               *       String
               *
               *       ,       Object>>
   * @Title: readXls
   * @Description: 处理xls文件 TODO 添加数据验证
   */
  public static Map<String, Object> readXls(String path, String filename) throws Exception {
    // HSSFWorkbook 标识整个excel
    Workbook hssfWorkbook = getWorkbook(path, filename);
    List<ExcelBean> result = new ArrayList<>();
    Map<String, Object> adjustMap = new HashMap<>();
    List<Map<String, Object>> errorList = new ArrayList<>();
    int size = hssfWorkbook.getNumberOfSheets();
    // 循环每一页，并处理当前循环页
    List<Sheet> hssfSheetArrayList = new ArrayList<>();
    for (int numSheet = 0; numSheet < size; numSheet++) {
      Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
      if (hssfSheet == null) {
        continue;
      }
      hssfSheetArrayList.add(hssfSheet);
    }
    hssfSheetArrayList.forEach(hssfSheet -> {
      // 处理当前页，循环读取每一行
      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
        // HSSFRow表示行
        //获取到表头
        Row row = hssfSheet.getRow(0);
        Row hssfRow = hssfSheet.getRow(rowNum);
        int minColIx = hssfRow.getFirstCellNum();
        int maxColIx = hssfRow.getLastCellNum();
        // 遍历改行，获取处理每个cell元素
        ExcelBean obj = null;
        Class<?> clz = ExcelBean.class;
        try {
          obj = (ExcelBean) clz.newInstance();
        } catch (Exception e) {
          e.printStackTrace();
        }
        for (int colIx = minColIx; colIx < maxColIx; colIx++) {
          // HSSFCell 表示单元格
          Cell cell = hssfRow.getCell(colIx);
          if (cell == null) {
            continue;
          }
          Cell cell1 = row.getCell(colIx);
          String title = cell1.getStringCellValue().trim();
          if (ExcelConstants.constantMap.get(title) != null) {
            try {
              String filedName = ExcelConstants.constantMap.get(title) + "";
              Field declaredField = clz.getDeclaredField(filedName);
              declaredField.setAccessible(true);
              String stringVal = getStringVal(cell);
              int rowIndex = rowNum + 1;
              int cloIndex = colIx + 1;
              if (StringUtils.isEmpty(stringVal)) {
                Map<String, Object> paramMap = new LinkedHashMap<>();
                paramMap.put("errorName", "字段校验问题");
                paramMap.put("errorFile", ExcelConstants.CASEXLS);
                paramMap.put("errorReason", String
                    .format("表头为【%s】,第%s行，第%s列，字段为空，请检查后重新上传", title, rowIndex, cloIndex));
                errorList.add(paramMap);
              } else {
                validateParam(errorList, stringVal, filedName, title,
                    rowIndex, cloIndex);
              }
              declaredField.set(obj, stringVal);
            } catch (Exception e) {
              e.printStackTrace();
            }
          } else {
            if (adjustMap.get(title) == null) {
              Map<String, Object> paramMap = new LinkedHashMap<>();
              paramMap.put("errorName", "模板问题");
              paramMap.put("errorFile", ExcelConstants.CASEXLS);
              paramMap.put("errorReason", String.format("列 【%s】 不存在，模板不准确请重新下载模板填写数据", title));
              errorList.add(paramMap);
              adjustMap.put(title, title);
            }
          }
        }
        //其他数据验证
        result.add(obj);
      }
    });
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("excelBean", result);
    resultMap.put("errorList", errorList);
    return resultMap;
  }

  /**
   * 验证excel中的数据
   */
  private static void validateParam(List<Map<String, Object>> resultList, String stringVal,
      String filedName, String title,
      int rowIndex,
      int cloIndex) {
    Map<String, Object> paramMap = new LinkedHashMap<>();
    paramMap.put("errorName", "字段校验问题");
    paramMap.put("errorFile", ExcelConstants.CASEXLS);
    if (ExcelConstants.CODE.equals(filedName)) {
      if (!ValidateExcelData.isIDCard(stringVal)) {
        paramMap.put("errorReason",
            String.format("表头为【%s】,第%s行，第%s列，%s错误，请检查后重新上传", title, rowIndex, cloIndex, title));
      }
    } else if (ExcelConstants.MOBILEPHONE.equals(filedName)) {
      if (!ValidateExcelData.isMobile(stringVal)) {
        paramMap.put("errorReason",
            String.format("表头为【%s】,第%s行，第%s列，%s错误，请检查后重新上传", title, rowIndex, cloIndex, title));
      }
    } else if (ExcelConstants.MAILADDRESS.equals(filedName)) {
      if (!ValidateExcelData.isEmail(stringVal)) {
        paramMap.put("errorReason",
            String.format("表头为【%s】,第%s行，第%s列，%s错误，请检查后重新上传", title, rowIndex, cloIndex, title));
      }
    } else if (ExcelConstants.PRINCIPAL.equals(filedName)) {
      if (!ValidateExcelData.isNumber(stringVal)) {
        paramMap.put("errorReason",
            String.format("表头为【%s】,第%s行，第%s列，%s错误，请检查后重新上传", title, rowIndex, cloIndex, title));
      }
    } else if (ExcelConstants.FEE.equals(filedName)) {
      if (!ValidateExcelData.isNumber(stringVal)) {
        paramMap.put("errorReason",
            String.format("表头为【%s】,第%s行，第%s列，%s错误，请检查后重新上传", title, rowIndex, cloIndex, title));
      }
    } else if (ExcelConstants.INTERESTS.equals(filedName)) {
      if (!ValidateExcelData.isNumber(stringVal)) {
        paramMap.put("errorReason",
            String.format("表头为【%s】,第%s行，第%s列，%s错误，请检查后重新上传", title, rowIndex, cloIndex, title));
      }
    } else if (ExcelConstants.CARDNO.equals(filedName)) {
      if (!ValidateExcelData.isCardNo(stringVal)) {
        paramMap.put("errorReason",
            String.format("表头为【%s】,第%s行，第%s列，%s错误，请检查后重新上传", title, rowIndex, cloIndex, title));
      }
    } else if (ExcelConstants.ACCOUNTNO.equals(filedName)) {
      if (!ValidateExcelData.isCardNo(stringVal)) {
        paramMap.put("errorReason",
            String.format("表头为【%s】,第%s行，第%s列，%s错误，请检查后重新上传", title, rowIndex, cloIndex, title));
      }
    }
    if (paramMap.get("errorReason") != null) {
      resultList.add(paramMap);
    }
  }

  /**
   * 改造poi默认的toString（）方法如下
   *
   * @param @param cell
   * @param @return 设定文件
   * @return String    返回类型
   * @Title: getStringVal
   * @Description: 1.对于不熟悉的类型，或者为空则返回""控制串 2.如果是数字，则修改单元格类型为String，然后返回String，这样就保证数字不被格式化了
   */
  public static String getStringVal(Cell cell) {
    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_BOOLEAN:
        return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
      case Cell.CELL_TYPE_FORMULA:
        return cell.getCellFormula();
      case Cell.CELL_TYPE_NUMERIC:
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
      case Cell.CELL_TYPE_STRING:
        return cell.getStringCellValue();
      default:
        return "";
    }
  }
}
