package com.sh2004.base.util;

import com.sh2004.bean.Activity;
import com.sh2004.bean.Clue;
import com.sh2004.bean.Tran;
import com.sh2004.mapper.ActivityMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.util
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/30 14:34
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class Excel {
    public static <T, K> void excel
            (String xml, Class<T> tClass, String title, String h1, String[] titles, K obj, int sub) throws Exception {
        BeanFactory beanFactory = new
                ClassPathXmlApplicationContext(xml);
        List<K> list = null;
        T bean = beanFactory.getBean(tClass);
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            String name = declaredMethod.getName();
            if (name.contains("selectAll")) {
                Object invoke = declaredMethod.invoke(bean);
                list = (List<K>) invoke;
            }
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        HSSFRow firstRow = sheet.createRow(0);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment((short) 2);
        HSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 20);
        cellStyle.setFont(font);
        HSSFCell cell1 = firstRow.createCell(0);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue(h1);
        HSSFCell cell2 = firstRow.createCell(1);
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 16);
        sheet.addMergedRegion(region);
        HSSFRow secondRow = sheet.createRow(1);
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = secondRow.createCell(i);
            cell.setCellValue(titles[i]);
        }

        for (int i = 0; i < list.size(); i++) {
            Method[] objMethods = obj.getClass().getDeclaredMethods();
            Field[] objFields = obj.getClass().getDeclaredFields();
            K obj0 = list.get(i);
            HSSFRow row = sheet.createRow(i + 2);
            for (int j = 0; j < objMethods.length; j++) {
                Method objMethod = objMethods[j];
                String name = objMethod.getName();
                for (int k = 0; k < titles.length; k++) {
                    Field objField = objFields[k + sub];
                    String fieldName = objField.getName();
                    fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    if (name.contains("get" + fieldName)) {
                        HSSFCell cell = row.createCell(k);
                        String invoke = (String) objMethod.invoke(obj0);
                        cell.setCellValue(invoke);
                        break;
                    }
                }
            }
        }
        File file = new File("D:/test2.xls");
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) throws Exception {

        /*Tran tran = new Tran();
        String[] titles = {"所有者","金额","名称","预计成交日期","客户",
                "阶段","类型","来源","市场活动","联系人","创建人","创建时间"
                ,"修改人","修改时间","描述","联系纪要","下次联系时间"};
        //String xml,Class<T> tClass,String title,String h1,String[] titles,K obj,int sub
        //配置文件路径,   Mapper的Class,  下面的标题,  正文的标题,  第一行的名称,  实体类,  从第几下标的属性开始
        Clue clue = new Clue();
        String[] t = {"fullname","appellation","owner","company","job",
                "email","phone","website","mphone","state","source","createBy"
                ,"createTime","editBy","editTime","description","contactSummary","nextContactTime","address"};
        List<String> strings = new ArrayList<>();
        Object[] objects = strings.toArray();
      //  excel("spring/applicationContext.xml", ClueMapper.class,"线索信息","线索统计",t,clue,1);



        Activity activity = new Activity();
        Field[] declaredFields = activity.getClass().getDeclaredFields();
        List<String> stringList = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            stringList.add(declaredField.getName());
        }
        //stringList.remove(0);
    //    stringList.remove(stringList.size()-1);
        String[] acs = stringList.toArray(new String[stringList.size()]);
        excel("spring/applicationContext.xml", ActivityMapper.class,"市场活动","市场活动统计",acs,activity,0);
    }*/
    }
}
