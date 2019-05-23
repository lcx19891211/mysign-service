package cn.lcxian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.lcxian.dao.StudiesMapper;
import cn.lcxian.exception.ParamVerifiedException;
import cn.lcxian.pojo.Studies;
import cn.lcxian.service.CheckReportService;
import cn.lcxian.utils.VoParse;
import cn.lcxian.vo.CheckReport;
import cn.lcxian.vo.ReportsParam;

@Service
public class CheckReportServiceImpl implements CheckReportService {

	@Autowired
	private StudiesMapper studiesMapper;

	/**
	 * 根据申请单id查询报告
	 */
	@Override
	public CheckReport selectById(String orderguid) {
		if(StringUtils.isEmpty(orderguid)) 
			throw new ParamVerifiedException("id不能为空");
		
		QueryWrapper<Studies> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("OrderNo", orderguid);
		Studies studies = studiesMapper.selectOne(queryWrapper );
		CheckReport checkReport = VoParse.toCheckReport(studies);
		return checkReport;
	}


	/**
	 * 根据参数查询报告
	 */
	@Override
	public List<CheckReport> selectByParams(ReportsParam reportsParam) {
		
		if(reportsParam==null || StringUtils.isEmpty(reportsParam.getOrderNo())) {
			throw new ParamVerifiedException("报告参数不能为空！");
		}
		Studies entitys = 
				new Studies().setOrderNo(reportsParam.getOrderNo());
		QueryWrapper<Studies> queryWrapper = new QueryWrapper<>();
		queryWrapper.setEntity(entitys);
		List<Studies> studiesList = studiesMapper.selectList(queryWrapper);
		if(studiesList == null || studiesList.size() <= 0)
			throw new ParamVerifiedException("找不到对应检查信息！");
		//遍历Studies集合转换为对应的检查报告集合
		List<CheckReport> list = new ArrayList<CheckReport>();
		for(Studies studies : studiesList) {
			list.add(VoParse.toCheckReport(studies));
		}
		return list;
	}

}
