package datumizeProve.accessPoint.controller;

import servInterfaces.AddValManagerInterface;
import service.AddValManager;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Path("/datumizeAPI")
public class GetValue {

    private AddValManagerInterface addValManager = new AddValManager();

    @GET
    @Path("/getvalue")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVal(){
        String response = String.valueOf(addValManager.getVal());

        return response;
    }

    @GET
    @Path("/setvalue/{c}")
    @Produces(MediaType.TEXT_PLAIN)
    public String setVal(@PathParam("c") String c){
        addValManager.addVal(c);
        String response = String.valueOf(addValManager.getVal());
        return response;
    }
}
