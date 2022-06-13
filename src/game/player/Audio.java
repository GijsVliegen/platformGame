package game.player;

import resource.AudioResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Audio {
    public static List<AudioResource> Sounds = new ArrayList<>();

    public static int[] hallo_sounds = {0, 6};
    public static int SOUND_GIGGITY = 0;
    public static int SOUND_HEY_DAAR = 1;
    public static int SOUND_HEY = 2;
    public static int SOUND_HALL0 = 3;
    public static int SOUND_HALL0_2 = 4;
    public static int SOUND_CHECK_MIJ = 5;

    public static int[] loop_sounds = {6, 8};
    public static int SOUND_WOEHOEW = 6;
    public static int SOUND_WOOEH = 7;

    public static int[] pijn_sounds = {8, 14};
    public static int SOUND_AUWCH = 8;
    public static int SOUND_AAA = 9;
    public static int SOUND_OOO = 10;
    public static int SOUND_AAAA = 11;
    public static int SOUND_AAA_JONG = 12;
    public static int SOUND_AU = 13;

    public static int[] extra_sounds = {14, 15};
    public static int SOUND_EARRAPE = 14;

    public static void audioInit(){
        AudioResource giggity = new AudioResource("res/Giggity.wav");
        AudioResource AAA = new AudioResource("res/AAA.wav");
        AudioResource aaaa = new AudioResource("res/aaaa.wav");
        AudioResource aaa_jong = new AudioResource("res/aaa jong.wav");
        AudioResource au = new AudioResource("res/au.wav");
        AudioResource auwch = new AudioResource("res/auwch.wav");
        AudioResource chech_mij = new AudioResource("res/check mij.wav");
        AudioResource hallo = new AudioResource("res/Hallo.wav");
        AudioResource hallo_2 = new AudioResource("res/Hallo2.wav");
        AudioResource hey = new AudioResource("res/Hey.wav");
        AudioResource hey_daar = new AudioResource("res/Hey Daar.wav");
        AudioResource OOO = new AudioResource("res/OOO.wav");
        AudioResource woehoew = new AudioResource("res/woehoew.wav");
        AudioResource wooeh = new AudioResource("res/wooeh.wav");
        AudioResource earrape = new AudioResource("res/earrape.wav");
        //volgorde is belangrijk!
        Sounds.add(giggity);
        Sounds.add(hey_daar);
        Sounds.add(hey);
        Sounds.add(hallo);
        Sounds.add(hallo_2);
        Sounds.add(chech_mij);
        Sounds.add(woehoew);
        Sounds.add(wooeh);
        Sounds.add(auwch);
        Sounds.add(AAA);
        Sounds.add(OOO);
        Sounds.add(aaaa);
        Sounds.add(aaa_jong);
        Sounds.add(au);
        Sounds.add(earrape);
    }

    //het is op het 1ste zicht niet mogelijk variabelen op te roepen
    // door een string te passen van de naam van de var
    //(retrieve field value via reflection) omdat deze classe abstract is
    public static void soundsInRange(int i, int j){
        Random random = new Random();
        int k = random.nextInt(j-i);
        //System.out.println("played sound " + (i+k));
        Sounds.get(i + k).playMusic();
    }
    public static void bunk(){
        soundsInRange(pijn_sounds[0], pijn_sounds[1]);
    }
    public static void hallo(){
        soundsInRange(hallo_sounds[0], hallo_sounds[1]);
    }
}
