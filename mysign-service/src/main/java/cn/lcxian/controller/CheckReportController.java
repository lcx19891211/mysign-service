package cn.lcxian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lcxian.service.CheckReportService;
import cn.lcxian.vo.CheckReport;
import cn.lcxian.vo.ReportsParam;
import cn.lcxian.vo.ResponseResult;

@RestController
@RequestMapping("/Consult/Rest")
public class CheckReportController {

	@Autowired
	private CheckReportService checkReportService;

	@RequestMapping("/Reports/{orderguid}")
	ResponseResult getReport(@PathVariable String orderguid){
		CheckReport checkReport = checkReportService.selectById(orderguid);
		if(checkReport != null)
			return ResponseResult.success(checkReport);
		else 
			return ResponseResult.failure();
	}

	@RequestMapping("/Reports")
	ResponseResult getReports(ReportsParam reportsParam){
		List<CheckReport> list = checkReportService.selectByParams(reportsParam);
		if(list != null && list.size() > 0)
			return ResponseResult.success(list);
		else 
			return ResponseResult.failure();
	}
}
