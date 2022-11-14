package calytrix.item.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Resource {
    
    ADAMANTINE("adamantine"),
    ORICHALCUM("orichalum"),
    MITHRIL("mithril");
    
    private final String resourceName;
}
