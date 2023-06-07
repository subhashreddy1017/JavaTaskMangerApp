package com.uttra.taskmanager.compartor;

import java.util.Comparator;

import com.uttra.taskmanager.bean.TaskBean;

public class End_Date_Comparator implements Comparator<TaskBean> {

	@Override
	public int compare(TaskBean o1, TaskBean o2) {
		// TODO Auto-generated method stub
		return o1.getEnd_date().compareTo(o2.getEnd_date());
	}

}
