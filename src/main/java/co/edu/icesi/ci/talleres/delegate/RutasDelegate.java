package co.edu.icesi.ci.talleres.delegate;


import co.edu.icesi.ci.talleres.model.Tmio1Ruta;

public interface RutasDelegate {
	
	public Tmio1Ruta saveRuta(Tmio1Ruta ruta);
	public Tmio1Ruta validarRuta(Tmio1Ruta ruta) throws Exception;
	public Tmio1Ruta findById(int id) throws Exception;
	public Iterable<Tmio1Ruta> findAll();
	public void delete(Tmio1Ruta ruta);

}
