package calytrix.item.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

import calytrix.Calytrix;
import calytrix.item.tools.ToolTierType;
import calytrix.util.ModTags;
import calytrix.util.ResourceLocationUtil;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ResourceType {
    
    ADAMANTINE("adamantine", List.of(Tiers.NETHERITE), List.of()),
    ORICHALCUM("orichalum", List.of(Tiers.NETHERITE), List.of(ToolTierType.ADAMANTINE.getTier())),
    MITHRIL("mithril", List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
    
    private final String resourceName;
    private final List<Object> after;
    private final List<Object> before;
    
    public TagKey<Item> getIngotTag() {
        return ModTags.Items.resourceIngotsTagsByType().get(this);
    }
    
    public ResourceLocation getResourceLocation() {
        return Calytrix.resourceLocation(resourceName);
    }
}
