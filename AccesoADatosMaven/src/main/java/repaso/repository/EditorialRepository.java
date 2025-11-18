package repaso.repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import repaso.model.Editorial;
import repaso.model.LibreriaException;
import repaso.model.Libro;

public class EditorialRepository {

	private Set<Editorial> editoriales;

	public EditorialRepository() {
		super();
		this.editoriales = new HashSet<Editorial>();
	}

	public Set<Editorial> getEditoriales() {
		return editoriales;
	}

	public void setEditoriales(Set<Editorial> editoriales) {
		this.editoriales = editoriales;
	}
	
	public void addeditorial (Editorial e) {
		editoriales.add(e);
	}
	
	public void eliminareditorial(Editorial e) {
		editoriales.remove(e);
	}
	
	public Editorial leereditorial (String cif) {
		 
		boolean encontrado = false;
		Editorial editorial = null;
		
		Iterator<Editorial> it = editoriales.iterator();
		while (it.hasNext() && !encontrado) {
			Editorial u = it.next();
		
		if (editorial.getCif().equalsIgnoreCase(cif)) {
			encontrado = true;
			editorial = u;
		}
	 }
		return editorial;
	 }
	 
	 public Editorial actualizaeditorial (Editorial e) throws LibreriaException {
		 
		 Editorial edi = this.leereditorial(e.getCif());
		 if (edi != null) {
			 editoriales.remove(edi);
			 editoriales.add(e);
		 } else {
			 throw new LibreriaException("No existe la editorial");
		 }
		 return edi;
		 
	 }
}
