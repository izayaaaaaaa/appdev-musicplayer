package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PersistSongs {
    public PersistSongs(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer"); //Create emf for customer trasactions table
		EntityManager em = emf.createEntityManager();   //Create em for emf
		
		em.getTransaction().begin();    //begin communication with sql table

        Song s1 = new Song();
        Song s2 = new Song();
        Song s3 = new Song();

        s1.setId(0);
        s2.setId(1);
        s3.setId(2);

        s1.setSongTitle("Anti-Hero");
        s2.setSongTitle("Curious");
        s3.setSongTitle("Sex (with my ex)");

        s1.setSongPath("MusicPlayer/src/main/java/com/songs/Anti-Hero - Taylor Swift.wav");
        s2.setSongPath("MusicPlayer/src/main/java/com/songs/Curious - Hayley Kiyoko.wav");
        s3.setSongPath("MusicPlayer/src/main/java/com/songs/Sex (with my ex) - Fletcher.wav");

        Query query = em.createQuery("SELECT COUNT(s) FROM Song s");
        Long recordCount = (Long) query.getSingleResult();

        if (recordCount > 0) {
            System.out.println("The table has contents.");
        } else {
            System.out.println("The table is empty.");
            em.persist(s1);
            em.persist(s2);
            em.persist(s3);

            em.getTransaction().commit();   //commit changes to the table
        }     
        //close emf and em
		em.close();
		emf.close();
    }     
}
