package com.csrf.action.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.csrf.action.model.GradesBusinessObject;
import com.csrf.action.model.GradesDataAccessObject;
import com.csrf.action.property.StudentDetails;
import com.csrf.action.property.SubjectNameId;

/**
 * Servlet implementation class StudentList
 */
public class StudentList extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	GradesDataAccessObject clsGDAO = new GradesDataAccessObject();
	GradesBusinessObject clsGBO = new GradesBusinessObject();

	private static final String ACTION_LIST  = "list";
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public StudentList()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String facultyId = null;
			String strAction = null;
			System.out.println("Hurrey !! i m in CRUD controller");			
			
			String facultyName =(String) request.getSession().getAttribute("Uname");			
			
			facultyId = clsGBO.getFacultyId(facultyName);
						
			//Getting Action to be performed..
			if(request.getParameter("ctrlAction")!= null)
			{
				strAction = request.getParameter("ctrlAction") ;				
			}
			else if(request.getAttribute("ctrlAction")!= null)
			{
				strAction = (String)request.getAttribute("ctrlAction");				
			}
			
			System.out.println("Action >>> " + strAction);
			
			if(strAction != null && strAction.equals(ACTION_LIST))
			{
				System.out.println("i am in CRUD controller and action is list!!!");
				ArrayList<StudentDetails> listStudent = new ArrayList<StudentDetails>();
				ArrayList<SubjectNameId> subjectList = new ArrayList<SubjectNameId>();
				
				listStudent = clsGDAO.getFacultyStudentList(facultyId);
				subjectList = clsGBO.getSubjectDetails();
				
				if(listStudent != null && subjectList != null)
				{
					
					request.setAttribute("listStudent", listStudent);
					request.setAttribute("subjectList", subjectList);
				}
				else
				{
					System.out.println("Error Occured!!");
					request.setAttribute("customMessage", "Error occured!!");
				}				
			}	
					
			request.getRequestDispatcher("ViewAllStudents.jsp").forward(request, response);
		}
		catch(Exception ex)
		{
			try
			
			{
				System.out.println("Error Occured!!");
				request.setAttribute("customMessage", "Error Occured!!");
				
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
