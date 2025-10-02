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

    public static final SoundEvent SOUND_MOLE_AMBIENT = registerSoundEvent("sound_mole_ambient");
    public static final SoundEvent SOUND_MOLE_HURT = registerSoundEvent("sound_mole_hurt");
    public static final SoundEvent SOUND_MOLE_DEATH = registerSoundEvent("sound_mole_death");

    public static final SoundEvent SOUND_BISON_AMBIENT = registerSoundEvent("sound_bison_ambient");
    public static final SoundEvent SOUND_BISON_HURT = registerSoundEvent("sound_bison_hurt");
    public static final SoundEvent SOUND_BISON_DEATH = registerSoundEvent("sound_bison_death");

    public static final SoundEvent SOUND_COYOTE_AMBIENT = registerSoundEvent("sound_coyote_ambient");
    public static final SoundEvent SOUND_COYOTE_HURT = registerSoundEvent("sound_coyote_hurt");
    public static final SoundEvent SOUND_COYOTE_DEATH = registerSoundEvent("sound_coyote_death");
    public static final SoundEvent SOUND_COYOTE_WHINE = registerSoundEvent("sound_coyote_whine");
    public static final SoundEvent SOUND_COYOTE_GROWL = registerSoundEvent("sound_coyote_growl");

    public static final SoundEvent SOUND_FERRUGINOUS_HAWK_AMBIENT = registerSoundEvent("sound_ferruginous_hawk_ambient");
    public static final SoundEvent SOUND_FERRUGINOUS_HAWK_HURT = registerSoundEvent("sound_ferruginous_hawk_hurt");
    public static final SoundEvent SOUND_FERRUGINOUS_HAWK_DEATH = registerSoundEvent("sound_ferruginous_hawk_death");

    public static final SoundEvent SOUND_PALLID_WINGED_GRASSHOPPER_AMBIENT = registerSoundEvent("sound_pallid_winged_grasshopper_ambient");
    public static final SoundEvent SOUND_PALLID_WINGED_GRASSHOPPER_HURT = registerSoundEvent("sound_pallid_winged_grasshopper_hurt");
    public static final SoundEvent SOUND_PALLID_WINGED_GRASSHOPPER_DEATH = registerSoundEvent("sound_pallid_winged_grasshopper_death");

    public static final SoundEvent SOUND_WESTERN_MEADOWLARK_AMBIENT = registerSoundEvent("sound_western_meadowlark_ambient");
    public static final SoundEvent SOUND_WESTERN_MEADOWLARK_HURT = registerSoundEvent("sound_western_meadowlark_hurt");
    public static final SoundEvent SOUND_WESTERN_MEADOWLARK_DEATH = registerSoundEvent("sound_western_meadowlark_death");

    public static final SoundEvent SOUND_BLACK_BILLED_MAGPIE_AMBIENT = registerSoundEvent("sound_black_billed_magpie_ambient");
    public static final SoundEvent SOUND_BLACK_BILLED_MAGPIE_HURT = registerSoundEvent("sound_black_billed_magpie_hurt");
    public static final SoundEvent SOUND_BLACK_BILLED_MAGPIE_DEATH = registerSoundEvent("sound_black_billed_magpie_death");

    public static final SoundEvent SOUND_WHITE_TAILED_JACKRABBIT_AMBIENT = registerSoundEvent("sound_white_tailed_jackrabbit_ambient");
    public static final SoundEvent SOUND_WHITE_TAILED_JACKRABBIT_HURT = registerSoundEvent("sound_white_tailed_jackrabbit_hurt");
    public static final SoundEvent SOUND_WHITE_TAILED_JACKRABBIT_DEATH = registerSoundEvent("sound_white_tailed_jackrabbit_death");

    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier id = Identifier.of(WildFields.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds()
    {

    }
}