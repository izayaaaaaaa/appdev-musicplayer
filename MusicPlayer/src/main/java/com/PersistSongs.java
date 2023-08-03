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
      

        s1.setId(0);
        s2.setId(1);
      
        s1.setSongTitle("Anti-Hero");
        s2.setSongTitle("Curious");
     

        s1.setSongPath("MusicPlayer/src/main/java/com/songs/Anti-Hero - Taylor Swift.wav");
        s2.setSongPath("MusicPlayer/src/main/java/com/songs/Curious - Hayley Kiyoko.wav");
       
        
        s1.setLyricsPath("MusicPlayer/src/main/java/com/lyrics/Taylor Swift - Anti-Hero.txt");
        s2.setLyricsPath("MusicPlayer/src/main/java/com/lyrics/Hayley Kiyoko - Curious.txt");
     

        s1.setArtistName("Taylor Swift");
        s2.setArtistName("Hayley Kiyoko");
    

        s1.setAlbumCover("MusicPlayer/src/main/java/com/cover/Taylor Swift - Anti-Hero.jpg");
        s2.setAlbumCover("MusicPlayer/src/main/java/com/cover/Hayley Kiyoko - Curious.png");
       

        Query query = em.createQuery("SELECT COUNT(s) FROM Song s");
        Long recordCount = (Long) query.getSingleResult();

        if (recordCount > 0) {
            System.out.println("The table has contents.");
        } else {
            System.out.println("The table is empty.");
            em.persist(s1);
            em.persist(s2);

            em.getTransaction().commit();   //commit changes to the table
        }     
        //close emf and em
		em.close();
		emf.close();
    }     
}
