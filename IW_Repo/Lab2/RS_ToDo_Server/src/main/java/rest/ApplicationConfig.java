package rest;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import common.ToDoList;

public class ApplicationConfig extends ResourceConfig {

	/**
     * Default constructor
     */
    public ApplicationConfig() {
    	this(new ToDoList());
    }


    /**
     * Main constructor
     * @param addressBook a provided address book
     */
    public ApplicationConfig(final ToDoList tdlist) {
    	register(ToDoListService.class);
    	register(MOXyJsonProvider.class);
    	register(new AbstractBinder() {

			@Override
			protected void configure() {
				bind(tdlist).to(ToDoList.class);
			}});
	}	

}
