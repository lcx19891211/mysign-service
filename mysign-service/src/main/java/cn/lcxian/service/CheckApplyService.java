package cn.lcxian.service;

import java.util.List;

import cn.lcxian.vo.ApplyParam;
import cn.lcxian.vo.CheckApply;

public interface CheckApplyService {

	String add(CheckApply checkApply);
	
	String update(String orderguid, CheckApply checkApply);
	
	List<CheckApply> getOrders(ApplyParam applyParam);
	
	CheckApply getOrder(String orderguid);
	
	Integer getStatus(String orderguid);
	
	String remove(String orderguid);
}
