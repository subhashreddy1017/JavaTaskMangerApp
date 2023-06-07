package com.uttra.taskmanager.compartor;

import java.util.Comparator;

import com.uttra.taskmanager.bean.TaskBean;

public class Time_Comparater implements Comparator<TaskBean> {

	@Override
	public int compare(TaskBean o1, TaskBean o2) {
		// TODO Auto-generated method stub
		return (int) (o1.getTime()-o2.getTime());
	}

}
