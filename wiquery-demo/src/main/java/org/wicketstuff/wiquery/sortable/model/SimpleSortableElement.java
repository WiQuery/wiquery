package org.wicketstuff.wiquery.sortable.model;

import java.io.Serializable;

/**
 * @author Stephane Gleizes
 */
public class SimpleSortableElement implements Serializable
{

	private static final long serialVersionUID = -9146266790252473051L;

	private Integer position;

	private String name;

	public SimpleSortableElement()
	{
	}

	public SimpleSortableElement(final Integer position, final String name)
	{
		this.position = position;
		this.name = name;
	}

	public Integer getPosition()
	{
		return position;
	}

	public void setPosition(Integer position)
	{
		this.position = position;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
