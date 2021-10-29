package com.revature;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.revature.DAO.EmployeeDAO;
import com.revature.DAO.ReimbursementDAO;
import com.revature.Entities.Employee;
import com.revature.Entities.Reimbursement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App 
{
    public static void main( String[] args )
    {
        Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/public", Location.CLASSPATH);
		}).start(7000);

		Logger logger = LoggerFactory.getLogger(App.class);
		
		app.get("/", ctx -> ctx.html("<h1>Meme Approval Portal</h1><div><video width=\"320\" height=\"240\" autoplay=\"\" loop=\"\"><source src=\"https://i.imgur.com/4uz9W3s.mp4\" type=\"video/mp4\"></video></div>"));

//      ########################
//		EMPLOYEE
// 		########################
		List<Employee> employees = EmployeeDAO.readAll();
		
		app.get("/employees", ctx -> ctx.jsonStream(employees));

		app.get("/employees/{id}", ctx -> {
			int id = Integer.parseInt(ctx.pathParam("id"));
			Employee employee = EmployeeDAO.readById(id);
			ctx.json(employee);
		});
		
//		########################
//		REIMBURSEMENT
// 		########################
		
		List<Reimbursement> reimbursements = ReimbursementDAO.readAll();

		app.get("/reimbursements", ctx -> ctx.jsonStream(reimbursements));

		app.get("/reimbursements/{rid}", ctx -> {
			int rid = Integer.parseInt(ctx.pathParam("rid"));
			Reimbursement reimbursement = ReimbursementDAO.readByRid(rid);
			ctx.json(reimbursement);
		});

		app.post("reimbursements", ctx -> {
			Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
			ReimbursementDAO.save(reimbursement);
			ctx.status(201);
			ctx.redirect("reimbursements.html");
		});

		app.get("reimbursementForm", ctx -> ctx.redirect("addReimbursement.html"));

		app.post("reimbursementForm", ctx -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			int uid = Integer.parseInt(ctx.formParam("uid"));
			double amount = Double.parseDouble(ctx.formParam("amount"));
			String comment = ctx.formParam("comment");
			Reimbursement reimbursement = new Reimbursement(uid, amount, LocalDate.now().format(formatter), 0, comment);
			logger.info("This the Reimbursement object from postman :" + reimbursement);
			ReimbursementDAO.save(reimbursement);
			ctx.status(201);
			ctx.redirect("addReimbursements.html");
		});

		app.put("reimbursement/{id}", ctx -> {
			String comment = ctx.pathParam("comment");
			int status = Integer.parseInt(ctx.pathParam("status"));
			Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
			ReimbursementDAO.update(comment, status, reimbursement);
			ctx.status(200);
			ctx.redirect("addReimbursements.html");
		});

	}
}
