package cn.lcxian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lcxian.pojo.Machine;
import cn.lcxian.service.MachineService;

@RestController
@RequestMapping("/Machine/")
public class MachineController {

	@Autowired
	private MachineService machineService;

	@RequestMapping("all")
	List<Machine> machineAll(){
		return machineService.selectAll();
	}
}
