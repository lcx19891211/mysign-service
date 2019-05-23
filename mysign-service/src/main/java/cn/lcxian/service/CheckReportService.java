package cn.lcxian.service;

import java.util.List;

import cn.lcxian.vo.CheckReport;
import cn.lcxian.vo.ReportsParam;

public interface CheckReportService {

	CheckReport selectById(String orderguid);
	
	List<CheckReport> selectByParams(ReportsParam reportsParam);
}
