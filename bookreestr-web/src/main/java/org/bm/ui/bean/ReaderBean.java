/**
 * 
 */
package org.bm.ui.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.bm.model.Reader;
import org.bm.service.impl.ReaderBean1;
import org.icefaces.ace.component.celleditor.CellEditor;
import org.icefaces.ace.component.datatable.DataTable;
import org.icefaces.ace.event.RowEditEvent;
import org.icefaces.ace.model.table.RowState;

/**
 * @author Black Moon
 *
 */
@ManagedBean
@SessionScoped 
public class ReaderBean extends GridBean<Reader> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SELECTOR = "form2:gridReaders"; 
	
	ReaderBean1 rb = new ReaderBean1();
	
	@PostConstruct
    public void init() {
        items = rb.getAll();
    }
	
	@Override
	public void add(){
		
		Reader item = new Reader();
		item.setId(rb.getNewId());
		items.add(item); 
		
		UIComponent u = FacesContext.getCurrentInstance().getViewRoot().findComponent(SELECTOR); 
		DataTable table = (DataTable)u;
		 
		RowState itemState = stateMap.get(item); 
		
		for (org.icefaces.ace.component.column.Column c : table.getColumns()) { 
			CellEditor editor = c.getCellEditor(); 
			
			if (editor != null)			
				itemState.addActiveCellEditor(editor);			
		}
		
		isNew = true;
	}
	
	@Override
	public void edit(RowEditEvent e){
		Reader r = (Reader)e.getObject();
		
		if (isNew) {			
			rb.add(r);
			isNew = false;
		}
		else
			rb.update(r);		
	}

	@Override
	public void delete(ActionEvent e) {
		for (Object o : stateMap.getSelected())
		{
			Reader r = (Reader)o;
			if (!isNew)			
				rb.delete(r.getId());
			items.remove(r);
		}
		
		isSelected = false;
	}
}

