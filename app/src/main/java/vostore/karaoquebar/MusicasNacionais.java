package vostore.karaoquebar;
public class MusicasNacionais {

    public String id,cantor,nomeMusica;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MusicasNacionais(){

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

    public MusicasNacionais(String id, String cantor, String nomeMusica) {
        this.id = id;
        this.cantor = cantor;
        this.nomeMusica = nomeMusica;
    }




}
