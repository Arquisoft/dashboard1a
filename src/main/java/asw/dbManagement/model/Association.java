package asw.dbManagement.model;

public class Association {

	public static class Proponer {
		
		public void link(Participant participante, Propuesta propuesta) {
			propuesta._setParticipant(participante);
			participante._getPropuestas().add(propuesta);
		}

		public void unlink(Participant participante, Propuesta propuesta) {
			participante._getPropuestas().remove(propuesta);
			propuesta._setParticipant(null);
		}
	}
	
	public static class Comentar{
		
		public void link(Propuesta propuesta, Comentario comentario){
			comentario._setPropuesta(propuesta);
			propuesta._getComentario().add(comentario);
		}
		
		public void unlink(Propuesta propuesta,Comentario comentario){
			propuesta._getComentario().remove(comentario);
			comentario._setPropuesta(null);
		}
	}

}
