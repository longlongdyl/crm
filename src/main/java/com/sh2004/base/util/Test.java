package com.sh2004.base.util;

import com.sh2004.bean.Activity;
import com.sh2004.bean.Clue;
import com.sh2004.bean.Tran;
import com.sh2004.mapper.TranMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.util
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/27 21:13
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class Test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        TranMapper bean = beanFactory.getBean(TranMapper.class);
        List<Tran> trans = bean.selectAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("交易信息");
        HSSFRow firstRow = sheet.createRow(0);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment((short)2);
        HSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 20);//设置字体大小
        cellStyle.setFont(font);
        HSSFCell cell1 = firstRow.createCell(0);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue("交易报表信息");
        HSSFCell cell2 = firstRow.createCell(1);

        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 16);
        sheet.addMergedRegion(region);

        //交易的字段名称
        String[] titles = {"所有者","金额","名称","预计成交日期","客户",
                "阶段","类型","来源","市场活动","联系人","创建人","创建时间"
                ,"修改人","修改时间","描述","联系纪要","下次联系时间"};
        HSSFRow secondRow = sheet.createRow( 1);
        for(int i = 0 ; i < titles.length; i++){
            HSSFCell cell = secondRow.createCell(i);
            cell.setCellValue(titles[i]);
        }
        for (int i = 0; i <trans.size() ; i++) {
            Method[] declaredMethods = Tran.class.getDeclaredMethods();
            Field[] declaredFields = Tran.class.getDeclaredFields();
            Tran tran = trans.get(i);
            HSSFRow row = sheet.createRow( i+2);
            for (int j = 0; j <declaredMethods.length ; j++) {
                Method declaredMethod = declaredMethods[j];
                String name = declaredMethod.getName();
                for (int k = 0; k <titles.length; k++) {
                    //因为舍弃了id属性所以下标+1
                    Field declaredField = declaredFields[k+1];
                    String fieldName = declaredField.getName();
                    fieldName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                    if (name.contains("get"+fieldName)){
                        HSSFCell cell = row.createCell(k);
                        String invoke = (String) declaredMethod.invoke(tran);
                        cell.setCellValue(invoke);
                        return;
                    }
                }
            }
        }
        File file = new File("D:/test.xls");
        FileOutputStream outputStream = new FileOutputStream(file);
        //将Excel写入输出流中
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
