package calytrix.item.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceType {
    
    ADAMANTINE("adamantine"),
    ORICHALCUM("orichalum"),
    MITHRIL("mithril");
    
    private final String resourceName;
}
