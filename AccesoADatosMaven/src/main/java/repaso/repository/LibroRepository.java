package repaso.repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import repaso.model.LibreriaException;
import repaso.model.Libro;

public class LibroRepository {

	private Set<Libro> libros;

	public LibroRepository() {
		super();
		this.libros = new HashSet<Libro>();
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}
	
	 public void addlibro (Libro l) {
		 libros.add(l);
	 }
	 
	 public void eliminarlibro (Libro l) {
		 libros.remove(l);
	 }
	 
	 public Libro leerlibro (String isbn) {
		 
		boolean encontrado = false;
		Libro libro = null;
		
		Iterator<Libro> it = libros.iterator();
		while (it.hasNext() && !encontrado) {
			Libro u = it.next();
		
		if (libro.getIsbn().equalsIgnoreCase(isbn)) {
			encontrado = true;
			libro = u;
		}
	 }
		return libro;
	 }
	 
	 public Libro actualizalibro (Libro l) throws LibreriaException {
		 
		 Libro lib = this.leerlibro(l.getIsbn());
		 if (lib != null) {
			 libros.remove(lib);
			 libros.add(l);
		 } else {
			 throw new LibreriaException("No existe el libro");
		 }
		 return lib;
		 
	 }
}
