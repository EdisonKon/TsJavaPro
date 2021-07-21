package TestBuildNodeTree4Joymo;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString
public class MainBusinessNode {

    private boolean hashAuth;
    private Long id;
    private String code;
    private String name;
    private Integer type;
    private String nodePrincipal;
    private Integer nodeCityCode;
    private String nodeAddress;
    private Long parentId;
    private Long subjectId;
    private String remark;
    private Integer status;
    private List<MainBusinessNode> children;
    private Set childIds = new HashSet();

}
