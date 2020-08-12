package mx.com.oropher.ws.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.com.oropher.ws.rest.client.model.Course;

public class CourseWsClient {

	private static final String COURSES = "/courses";
	private static final String COURSE_SERVICE_URL = "http://localhost:8080/courses/services/courseService/";

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(COURSE_SERVICE_URL).path(COURSES).path("/{id}").resolveTemplate("id", 123);
		Builder request = target.request();
		Course Course = request.get(Course.class);
		System.out.println(Course.getId());
		System.out.println(Course.getName());
		
		Course.setName("Oropher");
		WebTarget putTarget = client.target(COURSE_SERVICE_URL).path(COURSES);
		Response updateResponse = putTarget.request().put(Entity.entity(Course, MediaType.APPLICATION_XML));
		System.out.println(updateResponse.getStatus());
		updateResponse.close();
		
		Course newCourse = new Course();
		newCourse.setName("Bob");
		
		WebTarget postTarget = client.target(COURSE_SERVICE_URL).path(COURSES);
		Course createdCourse = postTarget.request().post(Entity.entity(newCourse, MediaType.APPLICATION_XML), Course.class);
		System.out.println(createdCourse.getId());
		System.out.println(createdCourse.getName());
		
		WebTarget deleteTarget = client.target(COURSE_SERVICE_URL).path(COURSES).path("/{id}").resolveTemplate("id", 125);
		Response deleteResponse = deleteTarget.request().delete(Response.class);
		System.out.println(deleteResponse.getStatus());
		deleteResponse.close();
		
		client.close();
		
	}

}
