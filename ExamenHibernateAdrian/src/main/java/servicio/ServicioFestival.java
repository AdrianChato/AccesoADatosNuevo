package servicio;

import dao.CineDao;
import dao.PeliculaDao;
import dao.SalaDao;

public class ServicioFestival {

	private CineDao cineDao = new CineDao();
	private PeliculaDao peliculaDao = new PeliculaDao();
	private SalaDao salaDao = new SalaDao();
	public CineDao getCineDao() {
		return cineDao;
	}
	public void setCineDao(CineDao cineDao) {
		this.cineDao = cineDao;
	}
	public PeliculaDao getPeliculaDao() {
		return peliculaDao;
	}
	public void setPeliculaDao(PeliculaDao peliculaDao) {
		this.peliculaDao = peliculaDao;
	}
	public SalaDao getSalaDao() {
		return salaDao;
	}
	public void setSalaDao(SalaDao salaDao) {
		this.salaDao = salaDao;
	} 
	
	
}
