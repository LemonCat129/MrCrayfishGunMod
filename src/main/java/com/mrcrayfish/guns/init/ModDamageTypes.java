package com.mrcrayfish.guns.init;

import com.mrcrayfish.guns.Reference;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Author: MrCrayfish
 */
public class ModDamageTypes
{
    public static final ResourceKey<DamageType> BULLET = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Reference.MOD_ID, "bullet"));

    /**
     * Based on code in Botania by Vazkii
     * <a href="https://github.com/VazkiiMods/Botania/blob/1.19.x/Xplat/src/main/java/vazkii/botania/common/BotaniaDamageTypes.java">Link</a>
     */
    public static class Sources
    {
        private static final String[] msgSuffix = {
                "death.attack.cgm.bullet.killed",
                "death.attack.cgm.bullet.eliminated",
                "death.attack.cgm.bullet.executed",
                "death.attack.cgm.bullet.annihilated",
                "death.attack.cgm.bullet.decimated"
        };
        private static Holder.Reference<DamageType> getHolder(RegistryAccess access, ResourceKey<DamageType> damageTypeKey)
        {
            return access.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damageTypeKey);
        }

        private static DamageSource source(RegistryAccess access, ResourceKey<DamageType> damageTypeKey, @Nullable Entity directEntity, @Nullable Entity causingEntity)
        {
            return new DamageSource(getHolder(access, damageTypeKey), directEntity, causingEntity) {
                @Override
                public String getMsgId() {
                    return msgSuffix[ThreadLocalRandom.current().nextInt(5)];
                }
            };
        }

        public static DamageSource projectile(RegistryAccess access, ProjectileEntity projectile, LivingEntity entity)
        {
            return source(access, BULLET, projectile, entity);
        }
    }
}
