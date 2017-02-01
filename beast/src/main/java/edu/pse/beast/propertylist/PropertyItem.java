package edu.pse.beast.propertylist;

import java.util.LinkedList;
import java.util.UUID;

import edu.pse.beast.datatypes.propertydescription.FormalPropertiesDescription;
import edu.pse.beast.datatypes.propertydescription.PostAndPrePropertiesDescription;
import edu.pse.beast.datatypes.propertydescription.SymbolicVariable;
import edu.pse.beast.datatypes.propertydescription.SymbolicVariableList;

/**
*
* @author Justin
*/
public class PropertyItem {
	
	private PostAndPrePropertiesDescription description;
	private Boolean willBeTested;
	
	public PropertyItem(PostAndPrePropertiesDescription descr, Boolean testStatus) {
		description = descr;
		willBeTested = testStatus;
	}
	public PropertyItem(PostAndPrePropertiesDescription descr) {
		this(descr, true);
	}
	public PropertyItem() {
		this(new PostAndPrePropertiesDescription(UUID.randomUUID().toString()), false);
	}
	
	
	public PostAndPrePropertiesDescription getDescription() {
		return description;
	}
	public void setDescription(PostAndPrePropertiesDescription descr) {
		this.description = descr;
	}
	public void setDescription(String newName, FormalPropertiesDescription preProp,
			FormalPropertiesDescription postProp, SymbolicVariableList symVars) {
		this.description = new PostAndPrePropertiesDescription(newName, preProp, postProp, symVars);
	}
	
	public Boolean willBeTested() {
		return willBeTested;
	}
	public void setTestStatus(boolean newStatus) {
		willBeTested = newStatus;
	}
	public void toggleTestStatus() {
		willBeTested = !willBeTested;
	}

	@Override
	public boolean equals (Object o) {
		if (o == null || this.getClass() != o.getClass()) return false;
		if (this.description.getName().equals(((PropertyItem)o).description.getName())) return true;
		else return false;
	}
	
}
