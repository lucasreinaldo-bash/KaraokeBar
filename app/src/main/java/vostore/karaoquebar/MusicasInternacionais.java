package vostore.karaoquebar;
public class MusicasInternacionais {

    public String id,cantor,nomeMusica;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MusicasInternacionais(){

    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public MusicasInternacionais(String id, String cantor, String nomeMusica) {
        this.id = id;
        this.cantor = cantor;
        this.nomeMusica = nomeMusica;
    }




}
