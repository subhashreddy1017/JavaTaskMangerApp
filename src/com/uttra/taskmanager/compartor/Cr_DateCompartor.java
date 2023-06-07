package com.uttra.taskmanager.compartor;

import java.util.Comparator;

import com.uttra.taskmanager.bean.TaskBean;

public class Cr_DateCompartor implements Comparator<TaskBean>{

	@Override
	public int compare(TaskBean o1, TaskBean o2) {
		// TODO Auto-generated method stub
		return o1.getCr_date().compareTo(o2.getCr_date());
	}

}
