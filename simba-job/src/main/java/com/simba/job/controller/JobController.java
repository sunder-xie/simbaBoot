package com.simba.job.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.json.JsonResult;
import com.simba.job.model.Job;
import com.simba.job.service.JobService;

/**
 * 任务 Controller
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@RequestMapping("/list")
	public String list() {
		return "job/list";
	}

	@RequestMapping("/getList")
	public String getList(Pager pager, ModelMap model) {
		List<Job> list = jobService.page(pager);
		model.put("list", list);
		return "job/table";
	}

	@ResponseBody
	@RequestMapping("/count")
	public JsonResult count() {
		int count = jobService.count();
		return new JsonResult(count, "", 200);
	}

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "job/add";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(int id, ModelMap model) {
		Job job = jobService.get(id);
		model.put("job", job);
		return "job/update";
	}

	@RequestMapping("/add")
	public String add(Job job, ModelMap model) throws ParseException {
		jobService.add(job);
		return "redirect:/job/list";
	}

	@RequestMapping("/update")
	public String update(Job job, ModelMap model) throws ParseException {
		jobService.update(job);
		return "redirect:/job/list";
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(Integer[] ids, ModelMap model) throws ParseException {
		List<Integer> idList = Arrays.asList(ids);
		jobService.batchDelete(idList);
		return new JsonResult();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(int id, ModelMap model) throws ParseException {
		jobService.delete(id);
		return new JsonResult();
	}

	/**
	 * 启动任务
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws ParseException
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping("/start")
	public JsonResult start(int id, ModelMap model) throws ParseException {
		jobService.startJob(id);
		return new JsonResult();
	}

	/**
	 * 暂停任务
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws ParseException
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping("/stop")
	public JsonResult stop(int id, ModelMap model) throws ParseException {
		jobService.stopJob(id);
		return new JsonResult();
	}
}
