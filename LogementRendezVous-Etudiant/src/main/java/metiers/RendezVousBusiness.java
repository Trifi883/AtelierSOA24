package metiers;

import entities.Logement;
import entities.RendezVous;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RendezVousBusiness {
    public static List<RendezVous> listeRendezVous;
    LogementBusiness logementMetier = new LogementBusiness();

    public RendezVousBusiness() {
        listeRendezVous = new ArrayList<>();
        
        Logement log1 = logementMetier.getLogementsByReference(1);
        Logement log2 = logementMetier.getLogementsByReference(2);
        Logement log3 = logementMetier.getLogementsByReference(3);

         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

         listeRendezVous.add(new RendezVous(1, currentDate, "10:00", log1, "23456789"));
        listeRendezVous.add(new RendezVous(2, currentDate, "14:30", log2, "98765432"));
        listeRendezVous.add(new RendezVous(3, currentDate, "16:00", log3, "55443322"));
        listeRendezVous.add(new RendezVous(4, currentDate, "11:30", log1, "11223344"));
        listeRendezVous.add(new RendezVous(5, currentDate, "15:00", log2, "99887766"));
    }

    public boolean addRendezVous(RendezVous rendezVous){

        int refLogement=rendezVous.getLogement().getReference();

        Logement logement=logementMetier.getLogementsByReference(refLogement);

        if(logement!=null){
            rendezVous.setLogement(logement);
            return listeRendezVous.add(rendezVous);}
        return false;
    }
    public List<RendezVous> getListeRendezVous() {
        return listeRendezVous;
    }

    public void setListeRendezVous(List<RendezVous> listeRendezVous) {
        this.listeRendezVous = listeRendezVous;
    }
    public List<RendezVous> getListeRendezVousByLogementReference(int reference) {
        List<RendezVous> liste=new ArrayList<RendezVous>();
        for(RendezVous r:listeRendezVous){
            if(r.getLogement().getReference()==reference)
                liste.add(r);
        }
        return liste;
    }
    public RendezVous getRendezVousById(int id){
        RendezVous rendezVous=null;
        for(RendezVous r:listeRendezVous){
            if(r.getId()==id)
                rendezVous=r;
        }
        return rendezVous;
    }
    public boolean deleteRendezVous(int id){
        Iterator<RendezVous> iterator=listeRendezVous.iterator();
        while(iterator.hasNext()){
            RendezVous r=iterator.next();
            if(r.getId()==id){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public boolean updateRendezVous(int idRendezVous, RendezVous updatedRendezVous) {
        for (int i = 0; i < listeRendezVous.size(); i++) {
            RendezVous r = listeRendezVous.get(i);
            if (r.getId() == idRendezVous) {

                Logement logement = logementMetier.getLogementsByReference(updatedRendezVous.getLogement().getReference());
                if (logement != null) {

                    listeRendezVous.set(i, updatedRendezVous);
                    return true;
                }
            }
        }
        return false;
    }

}
