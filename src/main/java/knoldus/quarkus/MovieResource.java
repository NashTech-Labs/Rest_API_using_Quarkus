package knoldus.quarkus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Path("/movies")
public class MovieResource {
    public static List<Movie> movies = new ArrayList<>();

    /**
     * get movie names
     * @return ok response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies(){
        return Response.ok(movies).build();
    }

    /**
     * get size
     * @return size
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    public Integer countMovies(){
        return movies.size();
    }

    /**
     * post a movie
     * @param newMovie the new movie
     * @return ok response
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMovie(Movie newMovie){
        movies.add(newMovie);
        return Response.ok(movies).build();
    }

    /**
     * Update the movie name
     * @param id the id
     * @param title the title
     * @return ok response
     */
    @PUT
    @Path("{id}/{")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(
            @PathParam("id") Long id ,
            @QueryParam("title") String title){
        movies = movies.stream().map(movie ->{
            if(movie.getId().equals(id)){
                movie.setTitle(title);
            }
            return movie;
        }).collect(Collectors.toList());
        return Response.ok(movies).build();
    }

    /**
     * delete a movie
     * @param id the id
     * @return no content response
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteMovie(
            @PathParam("id") Long id) {
       Optional<Movie> movieToDelete = movies.stream().filter(movie ->
               movie.getId().equals(id)).findFirst();
       boolean removed = false;
       if (movieToDelete.isPresent()){
           removed = movies.remove(movieToDelete.get());
       }
       if (removed){
           return Response.noContent().build();
       }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


}
