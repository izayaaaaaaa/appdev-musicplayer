// bug: double check that the gui updates upon song change (cover, lyrics changes etc)
// bug: memory limited heap eme
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
        // Song s6 = new Song();
        // Song s7 = new Song();
        // Song s8 = new Song();
        // Song s9 = new Song();
        // Song s10 = new Song();


        s1.setId(0);
        s2.setId(1);
        s3.setId(2);
        s4.setId(3);
        s5.setId(4);
        // s6.setId(5);
        // s7.setId(6);
        // s8.setId(7);
        // s9.setId(8);
        // s10.setId(9);

        s1.setSongTitle("Ain't it Fun");
        s2.setSongTitle("Anti-Hero");
        s3.setSongTitle("Blank Space");
        s4.setSongTitle("Curious");
        s5.setSongTitle("Decode");
        // s6.setSongTitle("Helena");
        // s7.setSongTitle("My Heart");
        // s8.setSongTitle("Out of Time");
        // s9.setSongTitle("Senorita");
        // s10.setSongTitle("You and I");

        s1.setSongPath("MusicPlayer/src/main/resources/songs/Ain't it Fun - Paramore.wav");
        s2.setSongPath("MusicPlayer/src/main/resources/songs/Anti-Hero - Taylor Swift.wav");
        s3.setSongPath("MusicPlayer/src/main/resources/songs/Blank Space - Taylor Swift.wav");
        s4.setSongPath("MusicPlayer/src/main/resources/songs/Curious - Hayley Kiyoko.wav");
        s5.setSongPath("MusicPlayer/src/main/resources/songs/Decode - Paramore.wav");
        // s6.setSongPath("MusicPlayer/src/main/resources/songs/Helena - My Chemical Romance.wav");
        // s7.setSongPath("MusicPlayer/src/main/resources/songs/My Heart - Paramore.wav");
        // s8.setSongPath("MusicPlayer/src/main/resources/songs/Out of Time - Taylor Swift.wav");
        // s9.setSongPath("MusicPlayer/src/main/resources/songs/Senorita - Shawn Mendes.wav");
        // s10.setSongPath("MusicPlayer/src/main/resources/songs/You and I - One Direction.wav");

        s1.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Ain't it Fun - Paramore.txt");
        s2.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Anti-Hero - Taylor Swift.txt");
        s3.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Blank Space - Taylor Swift.txt");
        s4.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Curious - Hayley Kiyoko.txt");
        s5.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Decode - Paramore.txt");
        // s6.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Helena - My Chemical Romance.txt");
        // s7.setLyricsPath("MusicPlayer/src/main/resources/lyrics/My Heart - Paramore.txt");
        // s8.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Out of Time - Taylor Swift.txt");
        // s9.setLyricsPath("MusicPlayer/src/main/resources/lyrics/Senorita - Shawn Mendes.txt");
        // s10.setLyricsPath("MusicPlayer/src/main/resources/lyrics/You and I - One Direction.txt");

        s1.setArtistName("Paramore");
        s2.setArtistName("Taylor Swift");
        s3.setArtistName("Taylor Swift");
        s4.setArtistName("Hayley Kiyoko");
        s5.setArtistName("Paramore");
        // s6.setArtistName("My Chemical Romance");
        // s7.setArtistName("Paramore");
        // s8.setArtistName("Taylor Swift");
        // s9.setArtistName("Shawn Mendes");
        // s10.setArtistName("One Direction");
    
        s1.setAlbumCover("MusicPlayer/src/main/resources/cover/Ain't It Fun - Paramore.png");
        s2.setAlbumCover("MusicPlayer/src/main/resources/cover/Anti-Hero - Taylor Swift.jpg");
        s3.setAlbumCover("MusicPlayer/src/main/resources/cover/Blank Space - Taylor Swift.jpg");
        s4.setAlbumCover("MusicPlayer/src/main/resources/cover/Curious - Hayley Kiyoko.jpg");
        s5.setAlbumCover("MusicPlayer/src/main/resources/cover/Decode - Paramore.png");
        // s6.setAlbumCover("MusicPlayer/src/main/resources/cover/Helena - My Chemical Romance.png");
        // s7.setAlbumCover("MusicPlayer/src/main/resources/cover/My Heart - Paramore.jpg");
        // s8.setAlbumCover("MusicPlayer/src/main/resources/cover/Out of Time - The Weeknd.jpg");
        // s9.setAlbumCover("MusicPlayer/src/main/resources/cover/Senorita - Shawn Mendes.png");
        // s10.setAlbumCover("MusicPlayer/src/main/resources/cover/You and I - One Direction.jpg");

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
            // em.persist(s6);
            // em.persist(s7);
            // em.persist(s8);
            // em.persist(s9);
            // em.persist(s10);

            em.getTransaction().commit();   //commit changes to the table
        }     
        //close emf and em
		em.close();
		emf.close();
    }     
}
