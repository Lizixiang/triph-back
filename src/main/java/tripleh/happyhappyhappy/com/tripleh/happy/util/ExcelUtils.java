package tripleh.happyhappyhappy.com.tripleh.happy.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Author: zixli
 * Date: 2020/7/23 14:14
 * FileName: ExcelUtils
 * Description: Excel工具类
 */
public class ExcelUtils {

     private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 导出Excel
     *
     * @param title 文件名(要有文件名后缀,如.xlsx)
     * @param pojoClass 要导出的实体类class
     * @param dataSet 要导出的数据集合
     * @param hasSecondTitle 是否有第二标题的标识(true,代表有),加上这个参数      可以避免一些不必要的逻辑判断
     * @return
     * @throws Exception
     */
    public static File creatExportExcel(String title, Class<?> pojoClass, Collection<?> dataSet, Boolean hasSecondTitle) throws Exception {
        //根据文件名生成文件
        File file = new File(title);
        FileOutputStream fout = null;
        //创建工作簿
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建sheet
        Sheet sheet = hssfWorkbook.createSheet();
        int index = 0;
        //创建第一行单元格(表头)
        Row row = sheet.createRow(index);
        //第二行表头先定义为空
        Row secondRow = null;
        //如果hasSecondTitle为true,创建第二行单元格
        if (hasSecondTitle) {
            //行数往下一位,index这个变量在这个方法里需要共享,因为(index+1)这行就是填充数据的那行
            index = 1;
            secondRow = sheet.createRow(index);
        }
        //设置样式(用简单样式,不骚气)
        HSSFCellStyle styleMain = hssfWorkbook.createCellStyle();
        //通过反射取得实体所有字段
        Field[] fields = pojoClass.getDeclaredFields();
        Excel excel;
        //开始遍历
        for (Field field : fields) {
            //判断字段是否有这注解
            excel = field.getAnnotation(Excel.class);
            if (excel != null) {
                //取得单元格索引号
                int cellIndex = excel.columnIndex();
                //取得表头名称
                String cellName = excel.exportName();
                try {
                    //生成第一行单元格数据
                    creCell(row, cellIndex, cellName, styleMain);
                    //如果第二行不为空
                    if (Objects.nonNull(secondRow)) {
                        //取得第二行表头名称
                        String exportSecondName = excel.exportSecondName();
                        //生成第二行创建单元格数据
                        creCell(secondRow, cellIndex, exportSecondName, styleMain);
                    }
                } catch (Exception e) {
                    logger.error("导出创建表头失败" + e.getMessage());
                }
            }
        }
        //填充数据的行数是标题数下面一行+1
        int rowIndex = index + 1;
        if (!CollectionUtils.isEmpty(dataSet)) {
            for (Object object : dataSet) {
                //根据rowIndex索引值创建excel的row
                row = sheet.createRow(rowIndex++);
                for (Field field : fields) {
                    //数据设为可修改
                    field.setAccessible(true);
                    excel = field.getAnnotation(Excel.class);
                    if (excel != null) {
                        //取得单元格索引号
                        int cellIndex = excel.columnIndex();
                        //取得单元格值(有数据转换就需要转)
                        Object obj = field.get(object);
                        String cellValue = Objects.isNull(obj) ? "" : String.valueOf(field.get(object));
                        String exportConvertSign = excel.exportConvertSign();
                        //格式转换
                        if (!StringUtils.isBlank(exportConvertSign)) {
                            String[] arr = exportConvertSign.split(",");
                            if (Objects.nonNull(obj)) {
                                for (String string : arr) {
                                    String[] arr1 = string.split("-");
                                    if (arr1[0].equals(String.valueOf(obj))) {
                                        cellValue = arr1[1];
                                    }
                                }
                            }
                        }
                        //日期转换
                        String dateToString = excel.dateToString();
                        if (!StringUtils.isBlank(dateToString)) {
                            cellValue = DateFormatUtils.format((Date) obj, dateToString);
                        }
                        try {
                            creCell(row, cellIndex, cellValue, styleMain);
                        } catch (Exception e) {
                            logger.error("导出数据失败" + e.getMessage());
                        }
                    }
                }
            }
        }
        try

        {
            fout = new FileOutputStream(file);
            hssfWorkbook.write(fout);
            return file;
        } finally

        {
            //关闭资源
            try {
                if (hssfWorkbook != null) {
                    hssfWorkbook.close();
                }
                if (fout != null) {
                    fout.close();
                }
            } catch (Exception e) {
                logger.error("导出资源关闭失败" + e.getMessage());
            }
        }
    }

    /**
     * 将上传的exel文件转成对应实体集合
     *
     * @param file 上传的导入文件
     * @param pojoClass 导出的数据的class
     * @return
     */
    public static List<Object> importExel(MultipartFile file, Class<?> pojoClass) throws Exception {
        //把每行转换成<标题名,值>的map集合
        List<Map<String, String>> mapList = new ArrayList<>();
        //创建excel工作簿
        Workbook workbook = getWorkBook(file);
        int index = 0;
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            //获取第一个sheet
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            //获取标题行
            Row titleRow = sheet.getRow(0);
            //开始的单元格下标
            int startCell = titleRow.getFirstCellNum();
            //末尾的单元格下标
            int endCell = titleRow.getLastCellNum();
            //遍历excel行数
            for (Row row : sheet) {
                //取得该行行数
                int rowNum = row.getRowNum();
                //我这里有两行标题行,所以row要大于1,才能去取数据,而row.getCell(4) != null是为了判断该行数据是不是空的,excel定义个序号列,必填项,序号不为空才代表这行有数据,可以去读取
                if (rowNum > 0 && !StringUtils.isBlank(getCellValue(row.getCell(0)))) {
                    //map给个初始长度,因为大部分情况,作为开发,是知道到底要导入多少字段的
                    Map<String, String> valueMap = new HashMap<>(60);
                    for (int i = startCell; i < endCell; i++) {
                        //取得表头名
                        String title = titleRow.getCell(i).getStringCellValue();
                        //取得单元格数值
                        String value = Objects.nonNull(row.getCell(i)) ? getCellValue(row.getCell(i)) : null;
                        valueMap.put(title, value);
                    }
                    mapList.add(valueMap);
                }
            }
        }
        //转化完后集合要是为空,说明没数值,给出提示
        if (CollectionUtils.isEmpty(mapList)) {
            logger.error("暂无数据可导入");
        }
        //最终返回要导入的实例集合
        return convertMapToEntity(mapList, pojoClass);
    }

    /**
     * 创建单元格
     *
     * @param row excel行数
     * @param c 单元格索引,简单理解成单元格格数
     * @param cellValue 该单元格数值
     * @param style 单元格style
     */
    private static void creCell(Row row, int c, String cellValue, CellStyle style) {
        Cell cell = row.createCell(c);
        cell.setCellValue(cellValue);
        cell.setCellStyle(style);
    }

    /**
     * 创建Workbook工作薄对象
     *
     * @param file 上传的文件
     * @return
     */
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith("xls")) {
                //2003,填充导入excel文件数据进入工作簿
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                //2007 及2007以上,填充导入excel文件数据进入工作簿
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.error("上传文件生成工作簿出错");
        }
        return workbook;
    }

    /**
     * excel表格中数据的转换
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (Objects.isNull(cell)) {
            return cellValue;
        }
        int cellType = cell.getCellType();
        //根据数据的类型进行相应转换
        switch (cellType) {
            //数字类型
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            //字符串类型
            case Cell.CELL_TYPE_STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            //布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            //公式类型
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            //空值类型
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            //错误类型
            case Cell.CELL_TYPE_ERROR:
                cellValue = "错误类型";
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    /**
     * 对字段做类型转换(类型和实体一一对应,如果有其他类型,可以对该方法进行扩展)
     *
     * @param fieldValue 字段值
     * @param fieldType  字段类型
     * @param fieldName  字段名称
     * @param line       excel行数
     * @return
     */
    public static Object typeCast(String fieldValue, Class fieldType, String fieldName, String line) throws ParseException {
        Object value = fieldValue;
        if (Objects.nonNull(value)) {
            //判断字段是否是日期类型
            if (fieldType.isAssignableFrom(Date.class)) {
                value = DateUtils.parseDate(fieldValue);
                if (Objects.isNull(value)) {
                    //抛出自定义的业务异常,给前端显示,提示用户的第几行哪个字段出了错
                    logger.error("第"+line+"行"+fieldName+"格式有误");
                }
            }
            //判断字段是否是数字类型
            if (fieldType.isAssignableFrom(BigDecimal.class)) {
                try {
                    value = new BigDecimal(fieldValue);
                } catch (Exception e) {
                    //抛出自定义的业务异常,给前端显示,提示用户的第几行哪个字段出了错
                    logger.error("第"+line+"行"+fieldName+"格式有误");
                }
            }
        }
        return value;
    }

    /**
     * 把map转换成实体
     *
     * @param mapList 数据的map集合
     * @param pojoClass 数据实例类型
     * @return
     * @throws Exception
     */
    public static List<Object> convertMapToEntity(List<Map<String, String>> mapList, Class<?> pojoClass) throws Exception {
        //对象集合
        List<Object> objectList = new ArrayList<>();
        //集合初始化行数(方便之后错误提示可以具体到行数)
        int line = 3;
        //遍历mapList
        for (Map<String, String> entityMap : mapList) {
            //实例化
            Object object = pojoClass.newInstance();
            //反射取得类各个字段
            Field[] fields = pojoClass.getDeclaredFields();
            //遍历map
            for (Map.Entry<String, String> entry : entityMap.entrySet()) {
                String key = entry.getKey();
                //遍历对象字段
                for (Field field : fields) {
                    //取得注解属性和字段名称
                    Excel excel = field.getAnnotation(Excel.class);
                    Class fieldType = field.getType();
                    if (Objects.nonNull(excel)) {
                        String fieldName = excel.importName();
                        //如果注解中的importName(导入字段名)和key(excel标题)一致,则进行设值
                        if (StringUtils.isNotEmpty(fieldName) && key.equals(fieldName)) {
                            String fieldValue = entityMap.get(key);
                            field.setAccessible(true);
                            Object value = null;
                            if (!StringUtils.isBlank(fieldValue)) {
                                value = typeCast(fieldValue, fieldType, fieldName, String.valueOf(line));
                            }
                            field.set(object, value);
                            break;
                        }
                    }
                }
            }
            objectList.add(object);
            line++;
        }
        return objectList;
    }

}


