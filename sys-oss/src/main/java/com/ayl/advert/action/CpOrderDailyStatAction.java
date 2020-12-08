package com.ayl.advert.action;


import com.ayl.advert.common.response.Pager;
import com.ayl.advert.common.response.Result;
import com.ayl.advert.common.response.ResultObject;
import com.ayl.advert.common.util.DateUtil;
import com.ayl.advert.common.util.ExcelUtil;
import com.ayl.advert.model.CpOrder;
import com.ayl.advert.model.CpOrderDailyStat;
import com.ayl.advert.service.CpOrderDailyStatService;

import com.ayl.advert.service.CpOrderService;
import com.ayl.advert.sys.model.SysUser;
import com.ayl.advert.sys.service.SysUserService;
import com.ayl.advert.sys.session.LoginUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cpOrderDailyStat")
public class CpOrderDailyStatAction {

    @Autowired
    private CpOrderService cpOrderService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CpOrderDailyStatService cpOrderDailyStatService;

    @GetMapping(path = "/find")
    public ModelAndView find(ModelAndView mv, Integer order_id) {
        mv.addObject("order_id", order_id);
        mv.setViewName("/cpOrderDailyStat/find/index");
        return mv;
    }

    @PostMapping(path = "/find")
    public Result find(String start_time, String end_time, Integer order_id, Integer pNum, Integer limit) {
        Map<String, Object> query = new HashMap<>(5, 1);
        query.put("order_id", order_id);
        query.put("start_time", start_time);
        query.put("end_time", end_time);

        SysUser sysUser = LoginUtil.getInstance().get();
        if (sysUser.getRole_id() == 2) {
            query.put("cp_id", sysUser.getId());
        }
        Pager<Map<String, Object>> pager = cpOrderDailyStatService.find(query, new Pager<Map<String, Object>>(pNum, limit));
        return new ResultObject<>(pager);
    }


    @GetMapping(path = "/add")
    public ModelAndView add(ModelAndView mv, Integer order_id) {
        Map<String, Object> cpQuery = new HashMap<>(5, 1);
        cpQuery.put("role_id", 2);
        mv.addObject("cps", sysUserService.findList(cpQuery));
        mv.addObject("record", cpOrderService.get(order_id));
        mv.setViewName("/cpOrderDailyStat/add/index");
        return mv;
    }

    @PostMapping(path = "/add")
    public Result add(CpOrderDailyStat cpOrderDailyStat) {
        this.cpOrderDailyStatService.saveOrUpdate(cpOrderDailyStat);
        return Result.ok();
    }

    @GetMapping(path = "/date/stat/find")
    public ModelAndView appConsumeDateStatfind(ModelAndView mv) {
        mv.setViewName("/cpOrderDailyStat/date/find/index");
        return mv;
    }

    @PostMapping(path = "/date/stat/find")
    public Result appConsumeDateStatfind(String start_time, String end_time, Long app_id, Integer pNum, Integer limit) {
        Map<String, Object> query = new HashMap<>(5, 1);
        query.put("app_id", app_id);
        query.put("start_time", start_time);
        query.put("end_time", end_time);
        SysUser sysUser = LoginUtil.getInstance().get();
        if (sysUser.getRole_id() == 2) {
            query.put("cp_id", sysUser.getId());
        }

        Pager<Map<String, Object>> pager = cpOrderDailyStatService.appConsumeDateStatFind(query, new Pager<Map<String, Object>>(pNum, limit));
        if (!pager.getList().isEmpty()) {
            Map<String, Object> data = cpOrderDailyStatService.appConsumeDateStat(query);

            pager.getList().add(data);
        }
        return new ResultObject<>(pager);
    }

    @PostMapping(path = "/date/stat/find/export")
    public void appConsumeDateStatfind(HttpServletResponse response, String start_time, String end_time, Long app_id) throws IOException {
        Map<String, Object> query = new HashMap<>(5, 1);
        query.put("app_id", app_id);
        query.put("start_time", start_time);
        query.put("end_time", end_time);
        SysUser sysUser = LoginUtil.getInstance().get();
        if (sysUser.getRole_id() == 2) {
            query.put("cp_id", sysUser.getId());
        }

        List<Map<String, Object>> list = cpOrderDailyStatService.appConsumeDateStatFind(query);
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet("订单统计");
            this.buildAppConsumeDateStatfind(workbook, sheet, list);
            ExcelUtil.write("订单统计.xls", workbook, response);
        } finally {
            workbook.close();
        }

    }

    private void buildAppConsumeDateStatDetailFindExport(HSSFWorkbook workbook, Sheet sheet, List<Map<String, Object>> list) {
        CellStyle centerStyle = workbook.createCellStyle();
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle leftStyle = workbook.createCellStyle();
        leftStyle.setAlignment(HorizontalAlignment.LEFT);
        leftStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle rightStyle = workbook.createCellStyle();
        rightStyle.setAlignment(HorizontalAlignment.RIGHT);
        rightStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle moneyStyle = workbook.createCellStyle();
        moneyStyle.setAlignment(HorizontalAlignment.RIGHT);
        moneyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        DataFormat format = workbook.createDataFormat();
        moneyStyle.setDataFormat(format.getFormat("¥##,##0.###0_);¥-##,##0.###0"));

        int row_number = -1;

        Row row = sheet.createRow(++row_number);
        row.setHeight((short) 400);

        int cell_number = -1;

        Cell cell;

        cell = row.createCell(++cell_number);
        cell.setCellStyle(centerStyle);
        cell.setCellValue("APP ID");
        sheet.setColumnWidth(cell_number, 4000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(leftStyle);
        cell.setCellValue("APP名称");
        sheet.setColumnWidth(cell_number, 6000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(centerStyle);
        cell.setCellValue("关键词");
        sheet.setColumnWidth(cell_number, 4000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(centerStyle);
        cell.setCellValue("日期");
        sheet.setColumnWidth(cell_number, 4000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(centerStyle);
        cell.setCellValue("单价");
        sheet.setColumnWidth(cell_number, 4000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(centerStyle);
        cell.setCellValue("下载量");
        sheet.setColumnWidth(cell_number, 4000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(moneyStyle);
        cell.setCellValue("消耗金额");
        sheet.setColumnWidth(cell_number, 4000);

        int total_complete_cnt = 0;
        BigDecimal total_amount = new BigDecimal(0);

        for (Map<String, Object> map : list) {
            row = sheet.createRow(++row_number);
            row.setHeight((short) 500);

            cell_number = -1;

            cell = row.createCell(++cell_number);
            cell.setCellStyle(centerStyle);
            cell.setCellValue(map.get("app_id").toString());

            cell = row.createCell(++cell_number);
            cell.setCellStyle(leftStyle);
            cell.setCellValue((String) map.get("app_name"));

            cell = row.createCell(++cell_number);
            cell.setCellStyle(centerStyle);
            cell.setCellValue((String) map.get("keyword"));

            cell = row.createCell(++cell_number);
            cell.setCellStyle(centerStyle);
            cell.setCellValue((String) map.get("date"));

            cell = row.createCell(++cell_number);
            cell.setCellStyle(centerStyle);
            cell.setCellValue(map.get("unit_price").toString());


            cell = row.createCell(++cell_number);
            cell.setCellStyle(centerStyle);
            cell.setCellValue(map.get("complete_cnt").toString());

            total_complete_cnt += ((BigDecimal) map.get("complete_cnt")).intValue();

            cell = row.createCell(++cell_number);
            cell.setCellStyle(moneyStyle);
            cell.setCellValue(map.get("amount").toString());

            total_amount = total_amount.add((BigDecimal) map.get("amount"));
        }
        row = sheet.createRow(++row_number);
        row.setHeight((short) 500);
        cell_number = -1;
        cell = row.createCell(++cell_number);
        cell = row.createCell(++cell_number);
        cell = row.createCell(++cell_number);
        cell = row.createCell(++cell_number);
        cell = row.createCell(++cell_number);
        cell.setCellValue("合计");
        cell.setCellStyle(centerStyle);
        cell = row.createCell(++cell_number);
        cell.setCellValue(total_complete_cnt);
        cell.setCellStyle(centerStyle);
        cell = row.createCell(++cell_number);
        cell.setCellStyle(moneyStyle);
        cell.setCellValue(total_amount.toString());

    }

    @GetMapping(path = "/date/stat/detail/find")
    public ModelAndView appConsumeDateStatDetailFind(ModelAndView mv, Long app_id, String date) {
        mv.addObject("app_id", app_id);
        mv.addObject("date", date);
        mv.setViewName("/cpOrderDailyStat/date/detail/find/index");
        return mv;
    }

    @PostMapping(path = "/date/stat/detail/find")
    public Result appConsumeDateStatDetailFind(Long app_id, String start_time, String date, Integer pNum, Integer limit) {
        Map<String, Object> query = new HashMap<>(5, 1);
        query.put("app_id", app_id);
        query.put("date", date);
        SysUser sysUser = LoginUtil.getInstance().get();
        if (sysUser.getRole_id() == 2) {
            query.put("cp_id", sysUser.getId());
        }

        Pager<Map<String, Object>> pager = cpOrderDailyStatService.appConsumeDateStatDetailFind(query, new Pager<Map<String, Object>>(pNum, limit));

        return new ResultObject<>(pager);
    }

    @PostMapping(path = "/date/stat/detail/find/export")
    public void appConsumeDateStatDetailFindExport(HttpServletResponse response, Long app_id, String date, String start_time, String end_time, String file_name) throws IOException {
        Map<String, Object> query = new HashMap<>(5, 1);
        query.put("app_id", app_id);
        query.put("date", date);
        query.put("start_time", start_time);
        query.put("end_time", end_time);
        SysUser sysUser = LoginUtil.getInstance().get();
        if (sysUser.getRole_id() == 2) {
            query.put("cp_id", sysUser.getId());
        }
        List<Map<String, Object>> list = cpOrderDailyStatService.appConsumeDateStatDetailFind(query);

        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet("统计");
            this.buildAppConsumeDateStatDetailFindExport(workbook, sheet, list);
            ExcelUtil.write(file_name + ".xls", workbook, response);
        } finally {
            workbook.close();
        }
    }

    private void buildAppConsumeDateStatfind(HSSFWorkbook workbook, Sheet sheet, List<Map<String, Object>> list) {

        CellStyle centerStyle = workbook.createCellStyle();
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle leftStyle = workbook.createCellStyle();
        leftStyle.setAlignment(HorizontalAlignment.LEFT);
        leftStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle rightStyle = workbook.createCellStyle();
        rightStyle.setAlignment(HorizontalAlignment.RIGHT);
        rightStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle moneyStyle = workbook.createCellStyle();
        moneyStyle.setAlignment(HorizontalAlignment.RIGHT);
        moneyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        DataFormat format = workbook.createDataFormat();
        moneyStyle.setDataFormat(format.getFormat("¥##,##0.###0_);¥-##,##0.###0"));

        int row_number = -1;

        Row row = sheet.createRow(++row_number);
        row.setHeight((short) 400);

        int cell_number = -1;

        Cell cell;

        cell = row.createCell(++cell_number);
        cell.setCellStyle(centerStyle);
        cell.setCellValue("APP ID");
        sheet.setColumnWidth(cell_number, 4000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(leftStyle);
        cell.setCellValue("APP名称");
        sheet.setColumnWidth(cell_number, 6000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(centerStyle);
        cell.setCellValue("日期");
        sheet.setColumnWidth(cell_number, 4000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(centerStyle);
        cell.setCellValue("下载量");
        sheet.setColumnWidth(cell_number, 4000);

        cell = row.createCell(++cell_number);
        cell.setCellStyle(moneyStyle);
        cell.setCellValue("消耗金额");
        sheet.setColumnWidth(cell_number, 4000);
        int total_complete_cnt = 0;
        BigDecimal total_amount = new BigDecimal(0);

        for (Map<String, Object> map : list) {
            row = sheet.createRow(++row_number);
            row.setHeight((short) 500);

            cell_number = -1;

            cell = row.createCell(++cell_number);
            cell.setCellStyle(centerStyle);
            cell.setCellValue(map.get("app_id").toString());

            cell = row.createCell(++cell_number);
            cell.setCellStyle(leftStyle);
            cell.setCellValue((String) map.get("app_name"));


            cell = row.createCell(++cell_number);
            cell.setCellStyle(centerStyle);
            cell.setCellValue((String) map.get("date"));


            cell = row.createCell(++cell_number);
            cell.setCellStyle(centerStyle);
            cell.setCellValue(map.get("complete_cnt").toString());

            total_complete_cnt += ((BigDecimal) map.get("complete_cnt")).intValue();

            cell = row.createCell(++cell_number);
            cell.setCellStyle(moneyStyle);
            cell.setCellValue(map.get("total_amount").toString());

            total_amount = total_amount.add((BigDecimal) map.get("total_amount"));

        }

        row = sheet.createRow(++row_number);
        row.setHeight((short) 500);
        cell_number = -1;
        cell = row.createCell(++cell_number);
        cell = row.createCell(++cell_number);
        cell = row.createCell(++cell_number);
        cell.setCellValue("合计");
        cell.setCellStyle(centerStyle);
        cell = row.createCell(++cell_number);
        cell.setCellValue(total_complete_cnt);
        cell.setCellStyle(centerStyle);
        cell = row.createCell(++cell_number);
        cell.setCellStyle(moneyStyle);
        cell.setCellValue(total_amount.toString());
    }


    @DeleteMapping(path = "/delete")
    public Result delete(Integer id) {
        cpOrderDailyStatService.delete(id);
        return Result.ok();
    }
}
