package com.uttra.taskmanager.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
/*public TaskBean(String name, String description, Date cr_date, Date end_date, String status, int priority,
			String tags, long time) {*/
import com.uttra.taskmanager.Constants;
import com.uttra.taskmanager.bean.TaskBean;
import com.uttra.taskmanager.exception.com.TaskNotFound;

public class TaskModel {
	public static ArrayList<String> listOfCategory() 
	{
		ArrayList<String> al=new ArrayList<>();
		try 
		{
			File f = new File(Constants.FILEPATH);
			File[] fa = f.listFiles();
			for (File f1 : fa) {
				if ((f1 != null) && (f1.getName().endsWith(".txt")))
				{
					al.add(f1.getName());
				}
			}
		}catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return al;
	}
	public static ArrayList<TaskBean> allCategoryTask(String path) {
		ArrayList<TaskBean> as = new ArrayList<TaskBean>();
		BufferedReader bf = null;
		try {

			File f = new File(path);
			File[] fa = f.listFiles();
			for (File f1 : fa) {
				if ((f1 != null) && (f1.getName().endsWith(".txt"))) {
					bf = new BufferedReader(new FileReader(f1));
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String line;
					while ((line = bf.readLine()) != null) {
						String[] s = line.split(":");
						if(s.length>5) {
								TaskBean t=new TaskBean(s[0],s[1],sdf.parse(s[2]),sdf.parse(s[3]),s[4],Integer.parseInt(s[5]),s[6],Long.parseLong(s[7]));
								as.add(t);
						}
						}
					}
				}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		} finally {
			if (bf != null)
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return as;
	}
	
	public static ArrayList<TaskBean> containsTasks(String name, String catName) {
		ArrayList<TaskBean> al = new ArrayList<>();
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while ((line = br.readLine()) != null) {
				String[] s = line.split(":");
				if (s.length > 3 && s[0].contains(name)) {
					TaskBean t=new TaskBean(s[0],s[1],sdf.parse(s[2]),sdf.parse(s[3]),s[4],Integer.parseInt(s[5]),s[6],Long.parseLong(s[7]));
					al.add(t);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return al;
	}

	public static ArrayList<TaskBean> listofTasks(String catName) {
		ArrayList<TaskBean> al = new ArrayList<>();
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while ((line = br.readLine()) != null) {
				String[] s = line.split(":");
				if (s.length > 3)
				{
					TaskBean t=new TaskBean(s[0],s[1],sdf.parse(s[2]),sdf.parse(s[3]),s[4],Integer.parseInt(s[5]),s[6],Long.parseLong(s[7]));
					al.add(t);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return al;

	}

	public static boolean removeTask(String catName, String tskName) throws TaskNotFound {
		boolean b = false;
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		ArrayList<String> al = new ArrayList<>();
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(":");
				if (arr[0].equals(tskName)) {
					b = true;
					continue;
				}
				al.add(line);
			}
			if (!b) {
				throw new TaskNotFound("Given Task Name Not Present");
			}
			br.close();
			br = null;
			bw = new BufferedWriter(new FileWriter(f));
			System.out.println(al.size());
			for (String s : al) {
				System.out.println(s);
				bw.write(s);
				bw.newLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return false;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return true;
	}

	public static String updateTask(TaskBean old, TaskBean newBean, String catName) {
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		ArrayList<String> al = new ArrayList<>();
		Date d = new Date();
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			System.out.println(old.getName());
			br = new BufferedReader(new FileReader(f));
			String line;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(":");
				if (arr[0].equals(old.getName())) {
					String Cr_date = sdf.format(newBean.getCr_date());
					String end_date = sdf.format(newBean.getEnd_date());
					line = newBean.getName() + ":" + newBean.getDescription() + ":" + Cr_date + ":" + end_date + ":"
							+ newBean.getStatus() + ":" + newBean.getPriority() + ":" + newBean.getTags() + ":"
							+ d.getTime();
				}
				al.add(line);
			}
			br.close();
			br = null;
			bw = new BufferedWriter(new FileWriter(f));
			System.out.println(al.size());
			for (String s : al) {
				System.out.println(s);
				bw.write(s);
				bw.newLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return "SUCCUES";
	}

	public static TaskBean getTask(String tskName, String catName) {
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		BufferedReader br = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (f.exists()) {
				br = new BufferedReader(new FileReader(f));
				String line;
				while ((line = br.readLine()) != null) {
					String[] s = line.split(":");
					if (s[0].equals(tskName)) {
						for (int i = 0; i < s.length; i++) {
							System.out.println(s[i]);
						}
						TaskBean t=new TaskBean(s[0],s[1],sdf.parse(s[2]),sdf.parse(s[3]),s[4],Integer.parseInt(s[5]),s[6],Long.parseLong(s[7]));
						return t;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

	public static String searchTask(String searchName, String catName) {
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			while ((line = br.readLine()) != null) {
				String[] s = line.split(":");
				if (s[0].equals(searchName))
					return line;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "Task DoesNot Exsit";
	}

	public static String addTask(TaskBean t, String catName) {
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		if (f.exists()) {
			BufferedWriter bw = null;
			try {
				Date d = new Date();
				bw = new BufferedWriter(new FileWriter(f, true));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String cr_date = sdf.format(t.getCr_date());
				String end_date = sdf.format(t.getEnd_date());
				bw.write(t.getName() + ":" + t.getDescription() + ":" + cr_date + ":" + end_date + ":" + t.getStatus()
						+ ":" + t.getPriority() + ":" + t.getTags() + ":" + d.getTime());
				bw.newLine();
			} catch (Exception e) {
				e.getStackTrace();
				return "Failed" + e.getMessage();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}
		return "SUCCESS";
	}

	public static boolean checkCategoryExist(String catName) {
		return new File(String.valueOf(Constants.PATH) + catName + ".txt").exists();
	}

	public static void createCategory(String catName) {
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean checkTaskName(String catName, String name) {
		File f = new File(String.valueOf(Constants.PATH) + catName + ".txt");
		BufferedReader br = null;
		boolean flag = false;
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			while ((line = br.readLine()) != null) {
				String[] s = line.split(":");
				if (s[0].equals(name)) {
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

}
