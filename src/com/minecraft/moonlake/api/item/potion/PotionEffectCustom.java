package com.minecraft.moonlake.api.item.potion;

import com.minecraft.moonlake.property.*;
import com.minecraft.moonlake.validate.Validate;

/**
 * Created by MoonLake on 2016/9/15.
 */
public class PotionEffectCustom {

    private ReadOnlyObjectProperty<Byte> id;
    private ObjectProperty<Byte> amplifier;
    private IntegerProperty duration;
    private BooleanProperty ambient;
    private BooleanProperty showParticles;

    @Deprecated
    public PotionEffectCustom(int id, int amplifier, int duration) {

        this(id, amplifier, duration, false);
    }

    @Deprecated
    public PotionEffectCustom(int id, int amplifier, int duration, boolean ambient) {

        this(id, amplifier, duration, ambient, false);
    }

    @Deprecated
    public PotionEffectCustom(int id, int amplifier, int duration, boolean ambient, boolean showParticles) {

        this(PotionEffectType.fromId(id), amplifier, duration, ambient, showParticles);
    }

    public PotionEffectCustom(PotionEffectType type, int amplifier, int duration) {

        this(type, amplifier, duration, false);
    }

    public PotionEffectCustom(PotionEffectType type, int amplifier, int duration, boolean ambient) {

        this(type, amplifier, duration, ambient, false);
    }

    public PotionEffectCustom(PotionEffectType type, int amplifier, int duration, boolean ambient, boolean showParticles) {

        Validate.notNull(type, "The potion effect type object is null.");

        this.id = new SimpleObjectProperty<>((byte) type.getId());
        this.amplifier = new SimpleObjectProperty<>((byte) amplifier);
        this.duration = new SimpleIntegerProperty(duration);
        this.ambient = new SimpleBooleanProperty(ambient);
        this.showParticles = new SimpleBooleanProperty(showParticles);
    }

    public ReadOnlyObjectProperty<Byte> getId() {

        return id;
    }

    public ObjectProperty<Byte> getAmplifier() {

        return amplifier;
    }

    public IntegerProperty getDuration() {

        return duration;
    }

    public BooleanProperty getAmbient() {

        return ambient;
    }

    public BooleanProperty getShowParticles() {

        return showParticles;
    }
}

