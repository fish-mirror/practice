package com.zjicm.job.service;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.dto.Page;
import com.zjicm.job.domain.Job;

public interface JobService {

	/**
	 * 获取一个职位详情
	 *
	 * @param id
	 * @return
	 */
	Job get(Integer id);

	/**
	 * 发布或更新
	 * @param job
	 */
	void save(Job job);

	/**
	 * 查看职位列表
	 * @param userId
	 * @param status
	 * @param page
	 * @param size
	 * @return
	 */
	PageResult<Job> page(String userNumber, int status, int page,int size);
	
}
