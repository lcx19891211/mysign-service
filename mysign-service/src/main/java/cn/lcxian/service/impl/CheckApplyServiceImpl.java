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
import cn.lcxian.service.CheckApplyService;
import cn.lcxian.utils.VoParse;
import cn.lcxian.vo.ApplyParam;
import cn.lcxian.vo.CheckApply;

@Service
public class CheckApplyServiceImpl implements CheckApplyService {

	@Autowired
	private StudiesMapper studiesMapper;

	/**
	 * 根据HIS申请单生成PACS检查单
	 */
	@Override
	public String add(CheckApply checkApply) {

		if(checkApply == null)
			throw new ParamVerifiedException("申请单信息不能为空！");

		Studies entity = VoParse.toStudies(checkApply);
		int count = studiesMapper.insert(entity);
		if(count < 1)
			throw new ParamVerifiedException("申请单插入失败！");
		return entity.getStudyGUID();
	}

	/**
	 * 更新PACS检查单信息
	 */
	@Override
	public String update(String orderguid, CheckApply checkApply) {

		if(StringUtils.isEmpty(orderguid))
			throw new ParamVerifiedException("没选择更新的申请单号！");
		if(checkApply == null)
			throw new ParamVerifiedException("申请单信息不能为空！");

		Studies entity = VoParse.toStudies(checkApply);
		int count = studiesMapper.updateById(entity);

		if(count < 1)
			throw new ParamVerifiedException("找不到对应更新的申请单！");
		return "OK";
	}

	/**
	 * 根据参数查询检查单信息
	 */
	@Override
	public List<CheckApply> getOrders(ApplyParam applyParam) {

		if(applyParam == null)
			throw new ParamVerifiedException("申请单信息不能为空！");
		QueryWrapper<Studies> queryWrapper = new QueryWrapper<Studies>();
		Studies entity = new Studies().
				setOrderNo(applyParam.getOrderNo()).
				setStudyStatus(applyParam.getOrderStatus()).
				setOrderType(applyParam.getOrderType()).
				setPatientName(applyParam.getPatientName()).
				setPatientRID(applyParam.getPatientRID()).
				setBodyCheckupNo(applyParam.getRHCN()).
				setRefDept(applyParam.getRefDeptName()).
				setRefDoctor(applyParam.getRefDoctorName());

		queryWrapper.setEntity(entity);
		List<Studies> studiesList = studiesMapper.selectList(queryWrapper);
		if(studiesList == null || studiesList.size() <=0)
			throw new ParamVerifiedException("找不到对应的申请单信息！");

		List<CheckApply> checkApplyList = new ArrayList<>();
		for(Studies studies : studiesList) {
			checkApplyList.add(VoParse.toCheckApply(studies));
		}
		return checkApplyList;
	}

	/**
	 * 根据申请单号查询检查单信息
	 */
	@Override
	public CheckApply getOrder(String orderguid) {

		if(StringUtils.isEmpty(orderguid))
			throw new ParamVerifiedException("没选择更新的申请单号！");

		QueryWrapper<Studies> queryWrapper = new QueryWrapper<Studies>();
		queryWrapper.eq("OrderNo", orderguid);
		Studies studies = studiesMapper.selectOne(queryWrapper);

		if(studies == null)
			throw new ParamVerifiedException("找不到对应的申请单！");
		
		return VoParse.toCheckApply(studies);
	}

	/**
	 * 获取检查单状态
	 */
	@Override
	public Integer getStatus(String orderguid) {

		if(StringUtils.isEmpty(orderguid))
			throw new ParamVerifiedException("没选择更新的申请单号！");

		Studies studies = studiesMapper.selectById(orderguid);

		if(studies == null)
			throw new ParamVerifiedException("找不到对应的申请单！");
		return studies.getStudyStatus();
	}

	/**
	 * 删除指定id检查单
	 */
	@Override
	public String remove(String orderguid) {

		if(StringUtils.isEmpty(orderguid))
			throw new ParamVerifiedException("没选择更新的申请单号！");
		int count = studiesMapper.deleteById(orderguid);
		if(count < 1)
			throw new ParamVerifiedException("找不到对应删除的申请单！");
		return "OK";
	}

}
