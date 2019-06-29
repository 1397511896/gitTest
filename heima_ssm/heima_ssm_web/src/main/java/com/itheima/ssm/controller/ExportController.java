package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IExportService;
import org.apache.ibatis.jdbc.Null;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportController {
	@Autowired
	private IExportService exportService;

	@Autowired
	private  HttpServletResponse response;
	/**
	 * Excel 2007版本前

		Excel 的工作簿对应POI的HSSFWorkbook对象；
		Excel 的工作表对应POI的HSSFSheet对象；
		Excel 的行对应POI的HSSFRow对象；
		Excel 的单元格对应POI的HSSFCell对象。
		
		
		
		Excel 2007版本及07以后版本
		
		Excel 的工作簿对应POI的XSSFWorkbook对象；
		Excel 的工作表对应POI的XSSFSheet对象；
		Excel 的行对应POI的XSSFRow对象；
		Excel 的单元格对应POI的XSSFCell对象。
//	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/order.do")
	public void exportOrderExcel(@RequestParam(name = "orderStatusId",defaultValue = "") String orderStatusId,
								 @RequestParam(name = "startTime",defaultValue = "") String startTime,
								 @RequestParam(name = "endTime",defaultValue = "") String endTime
								 ) throws IOException{
//
//		System.out.println(orderStatusId);
//		System.out.println(startTime);
//		System.out.println(endTime);
		List<Orders> ordersList = exportService.findByArgs(orderStatusId,startTime,endTime);
//		System.out.println(ordersList);


		//1:创建一个工作簿(即excel文档)
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		//2:创建工作表 sheel页 
		XSSFSheet xssfSheet = xssfWorkbook.createSheet("订单报表"); //指定sheet页名称为:订单报表
		
		// 准备表头数据
		String [] header = {"用户ID","订单编号","产品名称","金额","下单时间","订单状态"};
		// 准备表体数据
//		List<Orders> bodyList = createBodyData();
		
		//3:创建表头
		//3.1创建表头行
		XSSFRow headerRow = xssfSheet.createRow(0);// 创建第一行
		//3.2创建表头所有列
		for(int i = 0;i<header.length;i++){
			//创建每一列
			XSSFCell xssfCell = headerRow.createCell(i);
			//为每个单元格填充值
			xssfCell.setCellValue(header[i]);
		}
		
		//4:创建数据体行 创建每行对应的列 并为每列赋值
		for(int i=0;i<ordersList.size();i++){
			Orders orders = ordersList.get(i);
			XSSFRow row = xssfSheet.createRow(i+1); //创建行-----注意这里要从第二行开始创建 因为刚开始表头已经占了了一行
			
			XSSFCell idCell = row.createCell(0); //创建当前行第一列的单元格
			idCell.setCellValue(orders.getId());
			
			XSSFCell userNameCell = row.createCell(1);//创建当前行第二列的单元格
			userNameCell.setCellValue(orders.getOrderNum());
			
			XSSFCell nickNameCell = row.createCell(2);//创建当前行第三列的单元格
			nickNameCell.setCellValue(orders.getProduct().getProductName());
			
			XSSFCell addressCell = row.createCell(3); //创建当前行第四列的单元格
			addressCell.setCellValue(orders.getProduct().getProductPrice());
			
			XSSFCell sexCell = row.createCell(4);//创建当前行第五列的单元格
			sexCell.setCellValue(orders.getOrderTimeStr());
			
			XSSFCell phoneCell = row.createCell(5);//创建当前行第六列的单元格
			phoneCell.setCellValue(orders.getOrderStatusStr());
			
		}
		
		
		//使用字节流输出
		//定义文件名
		String fileName = startTime+"至"+endTime+"已发货订单报表.xlsx";
		//设置文件下载的响应头
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String((fileName).getBytes(), "iso-8859-1"));
		//使用工作簿对象进行输出到字节流  这里的字节流是response对象提供
		xssfWorkbook.write(response.getOutputStream());
		//关闭
		xssfWorkbook.close();
		
	}

/*	private List<Orders> createBodyData() {
		List<Orders> ordersList = new ArrayList<>();
		for(int i=0;i<20;i++){
			ordersList.add(new Orders());
		}
		return ordersList;
	}*/

}
