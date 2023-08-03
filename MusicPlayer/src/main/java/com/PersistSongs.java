
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
        Song s4 = new Song();
        Song s5 = new Song();


        s1.setId(0);
        s2.setId(1);
        s3.setId(2);
        s4.setId(3);
        s5.setId(4);


        s1.setSongTitle("Ain't it Fun");
        s2.setSongTitle("Anti-Hero");
        s3.setSongTitle("Blank Space");
        s4.setSongTitle("Curious");
        s5.setSongTitle("Decode");


        s1.setSongPath("MusicPlayer/src/main/resources/songs/Ain't it Fun - Paramore.wav");
        s2.setSongPath("MusicPlayer/src/main/resources/songs/Anti-Hero - Taylor Swift.wav");
        s3.setSongPath("MusicPlayer/src/main/resources/songs/Blank Space - Taylor Swift.wav");
        s4.setSongPath("MusicPlayer/src/main/resources/songs/Curious - Hayley Kiyoko.wav");
        s5.setSongPath("MusicPlayer/src/main/resources/songs/Decode - Paramore.wav");


        s1.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Ain't it Fun - Paramore.txt");
        s2.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Anti-Hero - Taylor Swift.txt");
        s3.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Blank Space - Taylor Swift.txt");
        s4.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Curious - Hayley Kiyoko.txt");
        s5.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Decode - Paramore.txt");


        s1.setArtistName("Paramore");
        s2.setArtistName("Taylor Swift");
        s3.setArtistName("Taylor Swift");
        s4.setArtistName("Hayley Kiyoko");
        s5.setArtistName("Paramore");

    
        s1.setAlbumCover("MusicPlayer/src/main/resources/cover/Ain't It Fun - Paramore.png");
        s2.setAlbumCover("MusicPlayer/src/main/resources/cover/Anti-Hero - Taylor Swift.jpg");
        s3.setAlbumCover("MusicPlayer/src/main/resources/cover/Blank Space - Taylor Swift.png");
        s4.setAlbumCover("MusicPlayer/src/main/resources/cover/Curious - Hayley Kiyoko.jpg");
        s5.setAlbumCover("MusicPlayer/src/main/resources/cover/Decode - Paramore.png");


        Query query = em.createQuery("SELECT COUNT(s) FROM Song s");
        Long recordCount = (Long) query.getSingleResult();

        if (recordCount > 0) {
            System.out.println("The table has contents.");
        } else {
            System.out.println("The table is empty.");
            em.persist(s1);
            em.persist(s2);
            em.persist(s3);
            em.persist(s4);
            em.persist(s5);


            em.getTransaction().commit();   //commit changes to the table
        }     
        //close emf and em
		em.close();
		emf.close();
    }     
}
