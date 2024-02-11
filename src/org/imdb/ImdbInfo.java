package org.imdb;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Servlet implementation class ImdbInfo
 */
@WebServlet("/ImdbInfo")
public class ImdbInfo extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	//private static final String rootURL = "http://www.omdbapi.com";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImdbInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movie = request.getParameter("movieName");
		System.out.println(movie.contains(" "));
		if(movie.contains(" ")){
			movie=movie.replaceAll("\\s", "+");
		}
		System.out.println(movie);
		 
		Client cli = Client.create();
		String apiKey = System.getenv("IMDB_API_KEY");
		WebResource resource = cli.resource("http://www.omdbapi.com"+"/?t="+movie+"&apikey="+apiKey);
		String strResp =  resource.get(String.class);
		ClientResponse resp = resource.get(ClientResponse.class);
		ClientResponse cr = (ClientResponse) resp;
		InputStream stream = cr.getEntityInputStream();
		org.json.simple.parser.ContainerFactory cf = new org.json.simple.parser.ContainerFactory() {
			
			@Override
			public Map createObjectContainer() {
				// TODO Auto-generated method stub
				return new HashMap();
			}
			
			@Override
			public List creatArrayContainer() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		JSONParser parser = new JSONParser();
		HashMap obj =null;
		try {
			 obj = (HashMap) parser.parse(new InputStreamReader(stream), cf);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(obj.containsKey("Error")){
			response.getWriter().println("<html>");
			response.getWriter().println("<body  background=\"http://www.wallpaperup.com/uploads/wallpapers/2014/01/10/223345/b30a0f4c2cc96778690cdf0da61ee078.jpg\">");
			response.getWriter().println("<h1> Movie Doesn't exist in IMDB<h1>");
			
			response.getWriter().println("</body>");
			response.getWriter().println("</html>");
		}
		else{
		System.out.println(resp.toString());
		System.out.println(resp.getStatus());
		String posterUrl = (String) obj.get("Poster");
		String imdbUrl = "http://www.imdb.com/title/"+(String) obj.get("imdbID");
		System.out.println(imdbUrl);
		String out = resp.getEntity(String.class);
				response.getWriter().println("<html>");
				response.getWriter().println("<body background=\"http://treschicstudio.com/wp-content/themes/paula/images/body/background.jpg\">");
				response.getWriter().println("<style type=\"text/css\">");
				response.getWriter().println("html {");
				response.getWriter().println("border:9px black ridge;");
				response.getWriter().println("font-family: 'Book Antiqua';");
				response.getWriter().println("margin:0px; padding:0px; width:98%; height:115%;");
				response.getWriter().println("}");
				response.getWriter().println("</style>");
				response.getWriter().println("<center><a href=\""+imdbUrl+"\"><h1> "+obj.get("Title")+"</h1></a></center>");
				response.getWriter().println("<center><br><img src=\""+posterUrl+"\""+" alt=\"HTML5 Icon\" style=\"width:256px;height:400px;\"></center>");

				response.getWriter().println("<br><marquee><i> <b>"+obj.get("Plot")+"</b></i></marquee>");
				response.getWriter().println("<br><i style=\"padding-left: 13cm\"> Released Year</i>: <b>"+obj.get("Year")+"</b>");
				response.getWriter().println("<br><i style=\"padding-left: 13cm\" > Genre</i>: <b>"+obj.get("Genre")+"</b>");
				response.getWriter().println("<br><i style=\"padding-left: 13cm\"> Director</i><b>: "+obj.get("Director")+"</b>");
				response.getWriter().println("<br><i style=\"padding-left: 13cm\"> Actors</i>: <b>"+obj.get("Actors")+"</b>");
				
				response.getWriter().println("<br><i style=\"padding-left: 13cm\"> IMDB Rating</i><b>: "+obj.get("imdbRating")+"</b>");
				response.getWriter().println("<br><i style=\"padding-left: 13cm\"> Awards</i><b>: "+obj.get("Awards")+"</b>");
				
				//response.getWriter().println("<br><h6>Data in JSON Format<h6><textarea  style=\"width: 1300px; height: 60px; background-color: grey \" readonly>"+strResp+"</textarea>");
				response.getWriter().println("</body>");
				response.getWriter().println("</html>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
