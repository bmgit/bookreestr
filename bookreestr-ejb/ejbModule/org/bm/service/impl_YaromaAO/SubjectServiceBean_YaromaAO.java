/**
 * 
 */
package org.bm.service.impl_YaromaAO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.bm.ejb_YaromaAO.SubjectEjbBean_YaromaAO;
import org.bm.model_YaromaAO.Book_YaromaAO;
import org.bm.model_YaromaAO.Subject_YaromaAO;

/**
 * @author Black Moon
 *
 */
@Stateless
@WebService(portName="Subject")
public class SubjectServiceBean_YaromaAO {
	
	@EJB
	SubjectEjbBean_YaromaAO	dao;
	
	public List<Subject_YaromaAO> getAll() {        
		return dao.getAll();
	}	
	
	public Subject_YaromaAO getSubject(int id) {
		Subject_YaromaAO s = dao.get(id);
		List<Book_YaromaAO> books = s.getBooks();
		int sz = books.size();
		return dao.get(id);
	}
	
	public int addSubject(Subject_YaromaAO s) {
		return dao.add(s);		
	}
	
	public int getNewId(){
		return dao.getNewId();		
	}	
	
	public void deleteSubject(int id) {
		dao.delete(id);		
	}

	public void updateSubject(Subject_YaromaAO s) {
		dao.update(s);		
	}	
}
