package cn.lcxian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lcxian.service.CheckApplyService;
import cn.lcxian.vo.ApplyParam;
import cn.lcxian.vo.CheckApply;
import cn.lcxian.vo.ResponseResult;

@RestController
@RequestMapping("Orders/")
public class CheckApplyController {

	@Autowired
	private CheckApplyService checkApplyService;
	
	@RequestMapping("new")
	public ResponseResult add(CheckApply checkApply) {
		return ResponseResult.success(checkApplyService.add(checkApply));
	}

	@RequestMapping("{orderguid}/update")
	public ResponseResult update(
			@PathVariable String orderguid, CheckApply checkApply) {
		return ResponseResult.success(checkApplyService.update(orderguid, checkApply));
	}
	@RequestMapping("")
	public ResponseResult getOrders(ApplyParam applyParam) {
		return ResponseResult.success(checkApplyService.getOrders(applyParam));
	}
	@RequestMapping("{orderguid}")
	public ResponseResult getOrder(@PathVariable String orderguid) {
		return ResponseResult.success(checkApplyService.getOrder(orderguid));
	}
	@RequestMapping("{orderguid}/Status")
	public ResponseResult getStatus(
			@PathVariable String orderguid) {
		return ResponseResult.success(checkApplyService.getStatus(orderguid));
	}
	@RequestMapping("{orderguid}/delete")
	public ResponseResult remove(
			@PathVariable String orderguid) {
		return ResponseResult.success(checkApplyService.remove(orderguid));
	}
}
