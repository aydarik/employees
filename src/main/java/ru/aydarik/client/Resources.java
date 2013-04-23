package ru.aydarik.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {

	@Source("table.css")
	CssResources cssTable();

	@Source("icons/add.png")
	ImageResource add();

	@Source("icons/edit.png")
	ImageResource edit();

	@Source("icons/delete.png")
	ImageResource delete();
}
