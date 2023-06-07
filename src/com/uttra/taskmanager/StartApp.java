package com.uttra.taskmanager;

import java.text.SimpleDateFormat;
import java.util.*;

import com.uttra.taskmanager.bean.TaskBean;
import com.uttra.taskmanager.compartor.Cr_DateCompartor;
import com.uttra.taskmanager.compartor.End_Date_Comparator;
import com.uttra.taskmanager.compartor.Time_Comparater;
import com.uttra.taskmanager.model.TaskModel;
import com.uttra.taskmanager.task.util.TaskUtil;

public class StartApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Date cr_date = null, end_date;
		int choice = 0, tchoice = 0, priority, upChoice = 0, tskchoice = 0;
		String catName, tskName, disc, dte, tags, searchName, upName, status;
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		try {
			while (choice != 6) {
				System.out.println("-----------------------------");
				System.out.println("Enter the 1 to Create Category");
				System.out.println("Enter the 2 to Load Category");
				System.out.println("Enter the 3 to Search");
				System.out.println("Enter the 4 to List");
				System.out.println("Enter the 5 to Exit");
				System.out.println("-----------------------------");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter Unique Name To Create Category");
					catName = sc1.nextLine();
					tchoice=0;
					while (!(TaskUtil.checkValidName(catName))) {
						System.out.println("Name not should start with digith or blank ");
						System.out.println("Enter valid name");
						catName = sc1.nextLine();
					}
					if (TaskModel.checkCategoryExist(catName)) {
						System.out.println("This Category Alredy Exist");
						break;
					} else {
						TaskModel.createCategory(catName);
					}
					while (tchoice != 6) {
						System.out.println("-----------------------------");
						System.out.println("Enter 1 to Add Task");
						System.out.println("Enter 2 to Edit Task");
						System.out.println("Enter 3 to remove Task");
						System.out.println("Enter 4 to List of Task");
						System.out.println("Enter 5 to Search Task");
						System.out.println("Enter 6 to goBack");
						System.out.println("-----------------------------");
						System.out.println();
						System.out.println("Enter the choice");
						tchoice = sc.nextInt();
						switch (tchoice) {
						case 1:
							System.out.println("Enter the unique Task name");
							tskName = sc1.nextLine();
							while (!(TaskUtil.checkValidName(tskName)) || TaskModel.checkTaskName(catName, tskName)) {
								System.out.println(
										"TaskName not should start with digith or blank or TaskName Should be unique");
								System.out.println("Enter valid name");
								tskName = sc1.nextLine();
							}

							System.out.println("Write description");
							disc = sc1.nextLine();
							System.out.println("Enter cr_date in form dd/mm/yyyy");
							dte = sc1.nextLine();
							while (!TaskUtil.dateValidation(dte)) {
								System.out.println("Enter the Valid Date");
								dte = sc1.nextLine();
							}

							cr_date = dt.parse(dte);
							System.out.println("Enter end_date in form dd/mm/yyyy");
							dte = sc1.nextLine();
							while (!TaskUtil.dateValidation(dte)) {
								System.out.println("Enter the Valid Date");
								dte = sc1.nextLine();
							}
							end_date = dt.parse(dte);

							System.out.println("Enter the Status");
							status = sc1.nextLine();

							System.out.println("Enter priority for task 1 is too low and 10 is high");
							priority = sc.nextInt();
							while (!(TaskUtil.priorityCheck(priority))) {
								System.out.println("Enetr the priority 1 to 10");
								priority = sc.nextInt();
							}

							System.out.println("Enter the tags suppert by ',' ");
							tags = sc1.nextLine();
							TaskBean t = new TaskBean(tskName, disc, cr_date, end_date, status, priority, tags,cr_date.getTime());
							String result = TaskModel.addTask(t, catName);
							if (result.equals("SUCCESS")) {
								System.out.println("TASK" + tskName + "Added");
							} else {
								System.out.println("TASK" + tskName + "Failed");
							}
							break;
						case 2:
							System.out.println("Enter the name of the task to update");
							upName = sc1.nextLine();
							upChoice=0;
							if (!TaskModel.checkTaskName(catName, upName)) {
								System.out.println("Task was not exsist");
							} else {
								TaskBean oldTask = TaskModel.getTask(upName, catName);
								System.out.println(oldTask);
								TaskBean upTask = TaskModel.getTask(upName, catName);
								;

								while (upChoice != 8) {
									System.out.println("-----------------------------");
									System.out.println("Enter 1 to updateName");
									System.out.println("Enter 2 to updatedescription");
									System.out.println("Enter 3 to update cr_Date");
									System.out.println("Enter 4 to upadte end_Date");
									System.out.println("Enter 5 to update Status");
									System.out.println("Enter 6 to updatePriority");
									System.out.println("Enter 7 to upadateTags");
									System.out.println("Enter 8 to goBack");
									System.out.println("-----------------------------");
									System.out.println("Enter the choice");
									upChoice = sc.nextInt();
									switch (upChoice) {
									case 1:
										System.out.println("Enter the name to update");
										tskName = sc1.nextLine();
										while (!(TaskUtil.checkValidName(tskName))
												|| TaskModel.checkTaskName(catName, tskName)) {
											System.out.println(
													"TaskName not should start with digith or blank or TaskName Should be unique");
											System.out.println("Enter valid name");
											tskName = sc1.nextLine();
										}
										upTask.setName(tskName);

										break;
									case 2:
										System.out.println("Enter the Description");
										disc = sc1.nextLine();
										upTask.setDescription(disc);
										break;
									case 3:
										System.out.println("Enter the Cr-Date in the form of dd/MM/yyyy");
										dte = sc1.nextLine();
										while (!TaskUtil.dateValidation(dte)) {
											System.out.println("Enter the Valid Date");
											dte = sc1.nextLine();
										}
										cr_date = dt.parse(dte);
										upTask.setCr_date(cr_date);
										break;
									case 4:
										System.out.println("Enter the end-Date in the form of dd/MM/yyyy");
										dte = sc1.nextLine();
										while (!TaskUtil.dateValidation(dte)) {
											System.out.println("Enter the Valid Date");
											dte = sc1.nextLine();
										}
										end_date = dt.parse(dte);
										upTask.setEnd_date(end_date);
										break;
									case 5:
										System.out.println("Enter the Status");
										status = sc1.nextLine();
										upTask.setStatus(status);
										break;
									case 6:
										System.out.println("Enter the Priority");
										priority = sc.nextInt();
										while (!(TaskUtil.priorityCheck(priority))) {
											System.out.println("Enetr the priority 1 to 10");
											priority = sc.nextInt();
										}
										upTask.setPriority(priority);
										break;
									case 7:
										System.out.println("Enter the Tags supprted by ','");
										tags = sc1.nextLine();
										upTask.setTags(tags);
										break;
									case 8:
										break;

									}
									System.out.println(upTask.toString());
									String report = TaskModel.updateTask(oldTask, upTask, catName);
									if (report.equals("SUCCUES")) {
										System.out.println("Updated");
									} else {
										System.out.println("NotUpdated");
									}
								}
							}

							break;
							
						case 3:
							System.out.println("Enter the Task Name to remove");
							tskName = sc1.nextLine();
							while (!(TaskUtil.checkValidName(tskName))) {
								System.out.println(
										"TaskName not should start with digith or blank or TaskName Should be unique");
								System.out.println("Enter valid name");
								tskName = sc1.nextLine();
							}
							boolean b = TaskModel.removeTask(catName, tskName);
							if (b) {
								System.out.println("Task Removed");
							} else {
								System.out.println("Not removed");
							}
							break;
						case 4:
							tskchoice=0;
							while (tskchoice != 7) {
								System.out.println("-----------------------------");
								System.out.println("Enter 1 to Get Tasks Based on Create Date for Specific Category ");
								System.out.println("Enter 2 to Get Tasks Based on DUE Date for Specific Category ");
								System.out.println("Enter 3 to Get Tasks Based on Create Date for All Category ");
								System.out.println("Enter 4 to Get Tasks Based on DUE Date for All Category");
								System.out.println("Enter 5 to Get Tasks Based on Create Time for All Category ");
								System.out.println("Enter 6 to Get Tasks Based on Create TIME for SPECIFIC Category ");
								System.out.println("Enter 7 to Exit");
								System.out.println("-----------------------------");
								System.out.println("Enter your choice");
								tskchoice = sc.nextInt();
								switch (tskchoice) {
								case 1:
									System.out.println("Enter the Category Name");
									catName = sc1.nextLine();
									if(!TaskModel.checkCategoryExist(catName)) 
									{
										System.out.println("TASK NOT EXIST...........");
										break;
									}
									ArrayList<TaskBean> als = new ArrayList<>();
									als = TaskModel.listofTasks(catName);
									Collections.sort(als, new Cr_DateCompartor());
									for (TaskBean tb : als) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 2:
									System.out.println("Enter the Category Name");
									catName = sc1.nextLine();
									if(!TaskModel.checkCategoryExist(catName)) 
									{
										System.out.println("Category NOT EXIST...........");
										break;
									}
									ArrayList<TaskBean> a = new ArrayList<>();
									a = TaskModel.listofTasks(catName);
									Collections.sort(a, new End_Date_Comparator());
									for (TaskBean tb : a) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 3:
									System.out.println("ALL Caterogry Task's Based On Create Date");
									ArrayList<TaskBean> alC = new ArrayList<>();
									alC=TaskModel.allCategoryTask(String.valueOf(Constants.FILEPATH));
									Collections.sort(alC,new Cr_DateCompartor());
									for (TaskBean tb : alC) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 4:
									System.out.println("ALL Caterogry Task's Based On Create Date");
									ArrayList<TaskBean> alE = new ArrayList<>();
									alE=TaskModel.allCategoryTask(String.valueOf(Constants.FILEPATH));
									Collections.sort(alE,new End_Date_Comparator());
									for (TaskBean tb : alE) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 5:
									System.out.println("ALL Caterogry Task's Based On Create TIME");
									ArrayList<TaskBean> alT = new ArrayList<>();
									alT=TaskModel.allCategoryTask(String.valueOf(Constants.FILEPATH));
									Collections.sort(alT,new Time_Comparater());
									for (TaskBean tb : alT) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 6:
									System.out.println("Enter the Category Name To Sort Based on Create Date Time");
									catName = sc1.nextLine();if(!TaskModel.checkCategoryExist(catName)) 
									{
										System.out.println("Category NOT EXIST...........");
										break;
									}
									ArrayList<TaskBean> aT = new ArrayList<>();
									aT = TaskModel.listofTasks(catName);
									Collections.sort(aT, new End_Date_Comparator());
									for (TaskBean tb : aT) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 7:
									break;
								}
							}
							break;
						case 5:
							System.out.println("Enter the name to the search ");
							searchName = sc1.nextLine();
							ArrayList<TaskBean> tal = new ArrayList<>();
							tal = TaskModel.containsTasks(searchName, catName);
							for (TaskBean tb : tal) {
								String task = tb.toString();
								System.out.println(task);
							}
						case 6:
							break;
						}

					}
					break;
				case 2:
					System.out.println("Cateroys's");
					int i=0;
					 ArrayList<String> al=new ArrayList<>();
	                    al=TaskModel.listOfCategory();
	                    if(al.size()==0) 
	                    {
	                    	System.out.println("No Category's are there");
	                    }else 
	                    {
	                    	for(String s:al) 
	                    	{
	                    		System.out.println(++i+" "+s);
	                    	}
	                    }
					System.out.println("Enter the Category Name to Load");
					catName=sc1.nextLine();
					tchoice=0;
					while(!TaskUtil.checkValidName(catName)) 
					{
						System.out.println("Enter the Valid CategoryName");
						catName=sc1.nextLine();
					}
					while(!TaskModel.checkCategoryExist(catName)) 
					{
					   System.out.println("Please type the category name already created ");
					   catName=sc1.nextLine();
					}
					while (tchoice != 6) {
						System.out.println("-----------------------------");
						System.out.println("Enter 1 to Add Task");
						System.out.println("Enter 2 to Edit Task");
						System.out.println("Enter 3 to remove Task");
						System.out.println("Enter 4 to List of Task");
						System.out.println("Enter 5 to Search Task");
						System.out.println("Enter 6 to goBack");
						System.out.println("-----------------------------");
						System.out.println();
						System.out.println("Enter the choice");
						tchoice = sc.nextInt();
						switch (tchoice) {
						case 1:
							System.out.println("Enter the unique Task name");
							tskName = sc1.nextLine();
							while (!(TaskUtil.checkValidName(tskName)) || TaskModel.checkTaskName(catName, tskName)) {
								System.out.println(
										"TaskName not should start with digith or blank or TaskName Should be unique");
								System.out.println("Enter valid name");
								tskName = sc1.nextLine();
							}

							System.out.println("Write description");
							disc = sc1.nextLine();
							System.out.println("Enter cr_date in form dd/mm/yyyy");
							dte = sc1.nextLine();
							while (!TaskUtil.dateValidation(dte)) {
								System.out.println("Enter the Valid Date");
								dte = sc1.nextLine();
							}

							cr_date = dt.parse(dte);
							System.out.println("Enter end_date in form dd/mm/yyyy");
							dte = sc1.nextLine();
							while (!TaskUtil.dateValidation(dte)) {
								System.out.println("Enter the Valid Date");
								dte = sc1.nextLine();
							}
							end_date = dt.parse(dte);

							System.out.println("Enter the Status");
							status = sc1.nextLine();

							System.out.println("Enter priority for task 1 is too low and 10 is high");
							priority = sc.nextInt();
							while (!(TaskUtil.priorityCheck(priority))) {
								System.out.println("Enetr the priority 1 to 10");
								priority = sc.nextInt();
							}

							System.out.println("Enter the tags suppert by ',' ");
							tags = sc1.nextLine();
							TaskBean t = new TaskBean(tskName, disc, cr_date, end_date, status, priority, tags,cr_date.getTime());
							String result = TaskModel.addTask(t, catName);
							if (result.equals("SUCCESS")) {
								System.out.println("TASK" + tskName + "Added");
							} else {
								System.out.println("TASK" + tskName + "Failed");
							}
							break;
						case 2:
							System.out.println("Enter the name of the task to update");
							upName = sc1.nextLine();
							upChoice=0;
							if (!TaskModel.checkTaskName(catName, upName)) {
								System.out.println("Task was not exsist");
							} else {
								TaskBean oldTask = TaskModel.getTask(upName, catName);
								System.out.println(oldTask);
								TaskBean upTask = TaskModel.getTask(upName, catName);
								;

								while (upChoice != 8) {
									System.out.println("-----------------------------");
									System.out.println("Enter 1 to updateName");
									System.out.println("Enter 2 to updatedescription");
									System.out.println("Enter 3 to update cr_Date");
									System.out.println("Enter 4 to upadte end_Date");
									System.out.println("Enter 5 to update Status");
									System.out.println("Enter 6 to updatePriority");
									System.out.println("Enter 7 to upadateTags");
									System.out.println("Enter 8 to goBack");
									System.out.println("-----------------------------");
									System.out.println("Enter the choice");
									upChoice = sc.nextInt();
									switch (upChoice) {
									case 1:
										System.out.println("Enter the name to update");
										tskName = sc1.nextLine();
										while (!(TaskUtil.checkValidName(tskName))
												|| TaskModel.checkTaskName(catName, tskName)) {
											System.out.println(
													"TaskName not should start with digith or blank or TaskName Should be unique");
											System.out.println("Enter valid name");
											tskName = sc1.nextLine();
										}
										upTask.setName(tskName);

										break;
									case 2:
										System.out.println("Enter the Description");
										disc = sc1.nextLine();
										upTask.setDescription(disc);
										break;
									case 3:
										System.out.println("Enter the Cr-Date in the form of dd/MM/yyyy");
										dte = sc1.nextLine();
										while (!TaskUtil.dateValidation(dte)) {
											System.out.println("Enter the Valid Date");
											dte = sc1.nextLine();
										}
										cr_date = dt.parse(dte);
										upTask.setCr_date(cr_date);
										break;
									case 4:
										System.out.println("Enter the end-Date in the form of dd/MM/yyyy");
										dte = sc1.nextLine();
										while (!TaskUtil.dateValidation(dte)) {
											System.out.println("Enter the Valid Date");
											dte = sc1.nextLine();
										}
										end_date = dt.parse(dte);
										upTask.setEnd_date(end_date);
										break;
									case 5:
										System.out.println("Enter the Status");
										status = sc1.nextLine();
										upTask.setStatus(status);
										break;
									case 6:
										System.out.println("Enter the Priority");
										priority = sc.nextInt();
										while (!(TaskUtil.priorityCheck(priority))) {
											System.out.println("Enetr the priority 1 to 10");
											priority = sc.nextInt();
										}
										upTask.setPriority(priority);
										break;
									case 7:
										System.out.println("Enter the Tags supprted by ','");
										tags = sc1.nextLine();
										upTask.setTags(tags);
										break;
									case 8:
										break;

									}
									System.out.println(upTask.toString());
									String report = TaskModel.updateTask(oldTask, upTask, catName);
									if (report.equals("SUCCUES")) {
										System.out.println("Updated");
									} else {
										System.out.println("NotUpdated");
									}
								}
							}

							break;
							
						case 3:
							System.out.println("Enter the Task Name to remove");
							tskName = sc1.nextLine();
							while (!(TaskUtil.checkValidName(tskName))) {
								System.out.println(
										"TaskName not should start with digith or blank or TaskName Should be unique");
								System.out.println("Enter valid name");
								tskName = sc1.nextLine();
							}
							boolean b = TaskModel.removeTask(catName, tskName);
							if (b) {
								System.out.println("Task Removed");
							} else {
								System.out.println("Not removed");
							}
							break;
						case 4:
							tskchoice=0;
							while (tskchoice != 7) {
								System.out.println("-----------------------------");
								System.out.println("Enter 1 to Get Tasks Based on Create Date for Specific Category ");
								System.out.println("Enter 2 to Get Tasks Based on DUE Date for Specific Category ");
								System.out.println("Enter 3 to Get Tasks Based on Create Date for All Category ");
								System.out.println("Enter 4 to Get Tasks Based on DUE Date for All Category");
								System.out.println("Enter 5 to Get Tasks Based on Create Time for All Category ");
								System.out.println("Enter 6 to Get Tasks Based on Create TIME for SPECIFIC Category ");
								System.out.println("Enter 7 to Exit");
								System.out.println("-----------------------------");
								System.out.println("Enter your choice");
								tskchoice = sc.nextInt();
								switch (tskchoice) {
								case 1:
									System.out.println("Tasks Based on Create Date for Specific Category");
									if(!TaskModel.checkCategoryExist(catName)) 
									{
										System.out.println("Category NOT EXIST...........");
										break;
									}
									ArrayList<TaskBean> als = new ArrayList<>();
									als = TaskModel.listofTasks(catName);
									if(als.size()==0) 
									{
										System.out.println("No Task's Present");
									}
									Collections.sort(als, new Cr_DateCompartor());
									for (TaskBean tb : als) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 2:
									System.out.println("Tasks Based on DUE Date for Specific Category");
									if(!TaskModel.checkCategoryExist(catName)) 
									{
										System.out.println("Category NOT EXIST...........");
										break;
									}
									ArrayList<TaskBean> a = new ArrayList<>();
									a = TaskModel.listofTasks(catName);
									Collections.sort(a, new End_Date_Comparator());
									for (TaskBean tb : a) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 3:
									System.out.println("ALL Caterogry Task's Based On Create Date");
									ArrayList<TaskBean> alC = new ArrayList<>();
									alC=TaskModel.allCategoryTask(String.valueOf(Constants.FILEPATH));
									Collections.sort(alC,new Cr_DateCompartor());
									for (TaskBean tb : alC) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 4:
									System.out.println("ALL Caterogry Task's Based On Create Date");
									ArrayList<TaskBean> alE = new ArrayList<>();
									alE=TaskModel.allCategoryTask(String.valueOf(Constants.FILEPATH));
									Collections.sort(alE,new End_Date_Comparator());
									for (TaskBean tb : alE) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 5:
									System.out.println("ALL Caterogry Task's Based On Create TIME");
									ArrayList<TaskBean> alT = new ArrayList<>();
									alT=TaskModel.allCategoryTask(String.valueOf(Constants.FILEPATH));
									Collections.sort(alT,new Time_Comparater());
									for (TaskBean tb : alT) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 6:
									System.out.println("Category's Sort Based on Create Date Time");
									ArrayList<TaskBean> aT = new ArrayList<>();
									aT = TaskModel.listofTasks(catName);
									Collections.sort(aT, new End_Date_Comparator());
									for (TaskBean tb : aT) {
										String s = tb.toString();
										System.out.println(s);
									}
									break;
								case 7:
									break;
								}
							}
							break;
							
						
						case 5:
							System.out.println("Enter the name to the search ");
							searchName = sc1.nextLine();
							ArrayList<TaskBean> tal = new ArrayList<>();
							tal = TaskModel.containsTasks(searchName, catName);
							for (TaskBean tb : tal) {
								String task = tb.toString();
								System.out.println(task);
							}
						case 6:
							break;
						}
					}
					break;
				case 3:
					System.out.println("Enter the Category Name to search");
					catName=sc1.nextLine();
					boolean exist=TaskModel.checkCategoryExist(catName);
					if(!exist)
					{
						System.out.println("Category not Exisit");
						break;
					}
					System.out.println("Enter the name to the search ");
					searchName = sc1.nextLine();
					ArrayList<TaskBean> tal = new ArrayList<>();
					tal = TaskModel.containsTasks(searchName, catName);
					for (TaskBean tb : tal) {
						String task = tb.toString();
						System.out.println(task);
					}
					break;
				case 4:
					System.out.println("List of Category");
                    ArrayList<String> a=new ArrayList<>();
                    a=TaskModel.listOfCategory();
                    int j=0;
                    if(a.size()==0) 
                    {
                    	System.out.println("No Category's are there");
                    }else 
                    {
                    	for(String s:a) 
                    	System.out.println(++j+" "+s);
                    }
                    break;
				case 5:
					System.out.println("ThankYou Byee....");
					System.exit(0);
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}finally 
		{
			sc.close();
			sc1.close();
		}
	}

}
