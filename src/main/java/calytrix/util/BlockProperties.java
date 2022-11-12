package calytrix.util;

import lombok.Getter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;

@Getter
public class BlockProperties {
    private final Properties properties;
    
    private BlockProperties(Properties properties) {
        this.properties = properties;
    }
    
    public static Builder builder(Material material) {
        return new Builder(material);
    }
    
    public static final class Builder {
        private float strength;
        private float resistance;
        private Material material;
        private boolean requiresCorrectToolForDrops;
        private int lightLevel;
        
        public Builder(Material material) {
            this.material = material;
        }
        
        public BlockProperties build() {
            Properties props = Properties.of(material)
                                         .strength(strength, resistance)
                                         .lightLevel(state -> switch (lightLevel) {
                                             default -> 0;
                                             case 1 -> 12;
                                             case 2 -> 15;
                                         });
            
            if(requiresCorrectToolForDrops) {
                props = props.requiresCorrectToolForDrops();
            }
            
            return new BlockProperties(props);
        }
        
        public Builder strength(float strength) {
            this.strength = strength;
            return this;
        }
        
        public Builder strength(float strength, float resistance) {
            this.strength = strength;
            this.resistance = resistance;
            return this;
        }
        
        public Builder resistance(float resistance) {
            this.resistance = resistance;
            return this;
        }
        
        public Builder material(Material material) {
            this.material = material;
            return this;
        }
        
        public Builder requiresCorrectToolForDrops(boolean requiresCorrectToolForDrops) {
            this.requiresCorrectToolForDrops = requiresCorrectToolForDrops;
            return this;
        }
        
        public Builder lightLevel(int lightLevel) {
            this.lightLevel = lightLevel;
            return this;
        }
    }
}
