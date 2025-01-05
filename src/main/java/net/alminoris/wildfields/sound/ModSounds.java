package net.alminoris.wildfields.sound;

import net.alminoris.wildfields.WildFields;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds
{
    public static final SoundEvent SOUND_MARMOT_AMBIENT = registerSoundEvent("sound_marmot_ambient");
    public static final SoundEvent SOUND_MARMOT_HURT = registerSoundEvent("sound_marmot_hurt");
    public static final SoundEvent SOUND_MARMOT_DEATH = registerSoundEvent("sound_marmot_death");

    public static final SoundEvent SOUND_STEPPE_EAGLE_AMBIENT = registerSoundEvent("sound_steppe_eagle_ambient");
    public static final SoundEvent SOUND_STEPPE_EAGLE_HURT = registerSoundEvent("sound_steppe_eagle_hurt");
    public static final SoundEvent SOUND_STEPPE_EAGLE_DEATH = registerSoundEvent("sound_steppe_eagle_death");

    public static final SoundEvent SOUND_DARKLING_BEETLE_AMBIENT = registerSoundEvent("sound_darkling_beetle_ambient");
    public static final SoundEvent SOUND_DARKLING_BEETLE_HURT = registerSoundEvent("sound_darkling_beetle_hurt");
    public static final SoundEvent SOUND_DARKLING_BEETLE_DEATH = registerSoundEvent("sound_darkling_beetle_death");

    public static final SoundEvent SOUND_STEPPE_VIPER_AMBIENT = registerSoundEvent("sound_steppe_viper_ambient");
    public static final SoundEvent SOUND_STEPPE_VIPER_HURT = registerSoundEvent("sound_steppe_viper_hurt");
    public static final SoundEvent SOUND_STEPPE_VIPER_DEATH = registerSoundEvent("sound_steppe_viper_death");

    public static final SoundEvent SOUND_SAIGA_AMBIENT = registerSoundEvent("sound_saiga_ambient");
    public static final SoundEvent SOUND_SAIGA_HURT = registerSoundEvent("sound_saiga_hurt");
    public static final SoundEvent SOUND_SAIGA_DEATH = registerSoundEvent("sound_saiga_death");

    public static final SoundEvent SOUND_SERVAL_AMBIENT = registerSoundEvent("sound_serval_ambient");
    public static final SoundEvent SOUND_SERVAL_GROWL = registerSoundEvent("sound_serval_growl");
    public static final SoundEvent SOUND_SERVAL_WHINE = registerSoundEvent("sound_serval_whine");
    public static final SoundEvent SOUND_SERVAL_HURT = registerSoundEvent("sound_serval_hurt");
    public static final SoundEvent SOUND_SERVAL_DEATH = registerSoundEvent("sound_serval_death");

    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier id = Identifier.of(WildFields.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds()
    {

    }
}
