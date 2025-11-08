package com.lodestar.aileron;

import me.fzzyhmstrs.fzzy_config.annotations.NonSync;
import me.fzzyhmstrs.fzzy_config.annotations.Version;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedDouble;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;

@Version(version = 1)
public class AileronConfig extends Config {
    public AileronConfig() {
        super(Aileron.CONFIG_RL);
    }

    public GeneralChanges generalChanges = new GeneralChanges();
    @NonSync public CameraSettings cameraSettings = new CameraSettings();
    public Enchantments enchantments = new Enchantments();
    public Campfires campfires = new Campfires();

    public static class GeneralChanges extends ConfigSection {
        public ValidatedEnum<FireworkUseBehavior> fireworkUseBehavior = new ValidatedEnum<>(FireworkUseBehavior.COSMETIC);
    }

    public static class CameraSettings extends ConfigSection {
        @NonSync public ValidatedBoolean doCameraRoll = new ValidatedBoolean(true);
        @NonSync public ValidatedDouble cameraRollScale = new ValidatedDouble(1.0, 2.0, 0.0);
        @NonSync public ValidatedDouble cameraRollSpeed = new ValidatedDouble(0.1, 1.0, 0.05);
    }

    public static class Enchantments extends ConfigSection {
        public ValidatedDouble cloudskipperSpeedMultiplier = new ValidatedDouble(1.0, 2.0, 0.1);
        public ValidatedInt cloudskipperCloudLevel = new ValidatedInt(192, 4064, -2032);
        public ValidatedBoolean smokestackAirRecharge = new ValidatedBoolean(true);
        public ValidatedInt smokestackChargeTicks = new ValidatedInt(20, Integer.MAX_VALUE, 2);
    }

    public static class Campfires extends ConfigSection {
        public ValidatedBoolean campfiresPushPlayers = new ValidatedBoolean(true);
        public ValidatedFloat campfirePushMaxStrength = new ValidatedFloat(24F);
        public ValidatedFloat campfirePushBaseStrength = new ValidatedFloat(10F);
    }
}
