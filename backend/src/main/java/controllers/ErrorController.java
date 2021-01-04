package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.*;
import util.LoggerSingleton;

public class ErrorController {

	public void handle(HttpServletRequest req, HttpServletResponse res, Throwable t) throws IOException {
		// TODO Auto-generated method stub
		if (t == null) {
			res.setStatus(500);
			res.getWriter().write("OOPS something went wrong");
			return;
		}

		// all of my custom errors will end up here
		if (t instanceof AbstractHttpException) {

			AbstractHttpException err = (AbstractHttpException) t;
			System.out.println(t.getMessage());
			res.setStatus(err.getStatusCode());
			res.getWriter().write(err.getMessage());

		} else {
			t.printStackTrace();
			LoggerSingleton.getLogger().error(t.getStackTrace());
			res.setStatus(500);
			res.getWriter().write("OOPS something went wrong");
		}

	}

}
