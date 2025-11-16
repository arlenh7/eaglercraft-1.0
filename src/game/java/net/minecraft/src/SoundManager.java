// Merged SoundManager preserving Eaglercraft audio system and keeping legacy voids
// NOTE: All actual audio functionality uses the Eaglercraft system.
// Legacy methods are preserved as no-op or wrappers.

package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.EaglerInputStream;
import net.lax1dude.eaglercraft.Random;
import net.lax1dude.eaglercraft.internal.EnumPlatformType;
import net.lax1dude.eaglercraft.internal.IAudioCacheLoader;
import net.lax1dude.eaglercraft.internal.IAudioHandle;
import net.lax1dude.eaglercraft.internal.IAudioResource;
import net.lax1dude.eaglercraft.internal.PlatformAudio;
import net.peyton.eagler.minecraft.AudioUtils;

public class SoundManager {

    private GameSettings options;
    private Random rand = new Random();
    private int field_583_i = this.rand.nextInt(12000);

    private Map<String, IAudioResource> sounds = new HashMap<>();
    private Map<String, IAudioResource> music = new HashMap<>();

    private IAudioHandle musicHandle;

    private String[] newMusic = new String[] {
            "hal1.ogg", "hal2.ogg", "hal3.ogg", "hal4.ogg",
            "nuance1.ogg", "nuance2.ogg",
            "piano1.ogg", "piano2.ogg", "piano3.ogg"
    };

    private boolean loaded = true; 

    public void init(GameSettings var1) {
        this.options = var1;
    }

    public void loadSoundSettings(GameSettings g) {
        this.options = g;
    }

    public void onSoundOptionsChanged() {
        if (this.options.musicVolume == 0.0F) {
            if (this.musicHandle != null && !this.musicHandle.shouldFree()) {
                musicHandle.end();
            }
        } else {
            if (this.musicHandle != null && !this.musicHandle.shouldFree()) {
                musicHandle.gain(this.options.musicVolume);
            }
        }
    }

    public void func_4033_c() {
        if (this.options.musicVolume != 0.0F) {
            if (this.musicHandle == null || this.musicHandle.shouldFree()) {
                if (this.field_583_i > 0) {
                    --this.field_583_i;
                    return;
                }

                int idx = rand.nextInt(newMusic.length);
                this.field_583_i = this.rand.nextInt(12000) + 12000;
                String name = "/newmusic/" + newMusic[idx];

                IAudioResource trk = this.music.get(name);
                if (trk == null) {
                    if (EagRuntime.getPlatformType() != EnumPlatformType.DESKTOP) {
                        trk = PlatformAudio.loadAudioDataNew(name, false, browserResourceLoader);
                    } else {
                        trk = PlatformAudio.loadAudioData(name, false);
                    }
                    if (trk != null) music.put(name, trk);
                }

                if (trk != null) {
                    musicHandle = PlatformAudio.beginPlaybackStatic(trk, this.options.musicVolume, 1.0f, false);
                }
            }
        }
    }

    public void func_338_a(EntityLiving var1, float var2) {
        if (var1 != null && this.options.soundVolume != 0.0F) {
            try {
                float pitch = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var2;
                float yaw = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2;
                double x = var1.prevPosX + (var1.posX - var1.prevPosX) * var2;
                double y = var1.prevPosY + (var1.posY - var1.prevPosY) * var2;
                double z = var1.prevPosZ + (var1.posZ - var1.prevPosZ) * var2;

                PlatformAudio.setListener((float)x, (float)y, (float)z, pitch, yaw);
            } catch (Throwable ignored) {
            }
        }
    }

    public void func_331_a(String var1, float var2, float var3, float var4, float var5, float var6) {
       
    }

    public void playSound(String var1, float var2, float var3, float var4, float var5, float var6) {
        if (this.options.soundVolume != 0.0F) {
            if (var5 > 0.0F) {
                if (var1 == null) return;

                String sound = AudioUtils.getSound(var1);
                if (sound == null) return;

                IAudioResource trk = this.sounds.get(sound);
                if (trk == null) {
                    if (EagRuntime.getPlatformType() != EnumPlatformType.DESKTOP) {
                        trk = PlatformAudio.loadAudioDataNew(sound, true, browserResourceLoader);
                    } else {
                        trk = PlatformAudio.loadAudioData(sound, true);
                    }
                    if (trk != null) sounds.put(sound, trk);
                }

                if (trk != null) {
                    PlatformAudio.beginPlayback(trk, var2, var3, var4, var5 * this.options.soundVolume, var6, false);
                }
            }
        }
    }


    public void func_337_a(String var1, float var2, float var3) {
        if (this.options.soundVolume != 0.0F) {
            if (var2 > 1.0F) var2 = 1.0F;
            var2 *= 0.25F;

            if (var1 == null) return;
            String sound = AudioUtils.getSound(var1);
            if (sound == null) return;

            IAudioResource trk = this.sounds.get(sound);
            if (trk == null) {
                if (EagRuntime.getPlatformType() != EnumPlatformType.DESKTOP) {
                    trk = PlatformAudio.loadAudioDataNew(sound, true, browserResourceLoader);
                } else {
                    trk = PlatformAudio.loadAudioData(sound, true);
                }
                if (trk != null) sounds.put(sound, trk);
            }

            if (trk != null) {
                PlatformAudio.beginPlaybackStatic(trk, var2 * this.options.soundVolume, var3, false);
            }
        }
    }

    public void addSound(String s, java.io.File f) {

    }
    public void addStreaming(String s, java.io.File f) {

    }
    public void addMusic(String s, java.io.File f) {

    }
    public void playRandomMusicIfReady() { 
        func_4033_c(); 
    }
    public void playStreaming(String s, float a,float b,float c,float d,float e) { 
        func_331_a(s,a,b,c,d,e); 
    }
    public void playSoundFX(String s, float v, float p) {
        func_337_a(s, v, p); 
    }
    public void closeMinecraft() {}

    private final IAudioCacheLoader browserResourceLoader = filename -> {
        try {
            return EaglerInputStream.inputStreamToBytesQuiet(EagRuntime.getRequiredResourceStream(filename));
        } catch (Throwable t) {
            return null;
        }
    };
}