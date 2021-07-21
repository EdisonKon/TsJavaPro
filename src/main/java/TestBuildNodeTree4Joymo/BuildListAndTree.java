package TestBuildNodeTree4Joymo;


import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2021-06-10 09:19
 * @from
 **/
public class BuildListAndTree {

    public static void main(String[] args) {
        List<MainBusinessNode> mainBusinessNodes = bizNodsByUserId(100L);
        System.out.println(JSON.toJSONString(mainBusinessNodes));
        buildTree(mainBusinessNodes);
    }

    /**
     * 根据list 构建tree
     * @param mainBusinessNodes
     * @return
     */
    public static Map buildTree(List<MainBusinessNode> mainBusinessNodes){
        Map<Integer,Map> mapBiz = new HashMap<>();
        //O(log(N))
        mainBusinessNodes.sort((x, y) -> Double.compare(x.getType(), y.getType()));
        //O(N)
        for (MainBusinessNode node:mainBusinessNodes){
            if(node.getType() != 5){
                Map mapType = mapBiz.get(node.getType()) == null?new HashMap():mapBiz.get(node.getType());
                mapType.put(node.getId(),node);
                mapBiz.put(node.getType(), mapType);
            }else{
                help(mapBiz,node,5);
            }
        }
        System.out.println(JSON.toJSON(mapBiz.get(0)));
        return mapBiz.get(0);
    }

    public static void help(Map<Integer,Map> mapBiz, MainBusinessNode node,int type){
        int level = type-1;
        Map<Long,MainBusinessNode> mapUpper = mapBiz.get(level);
        if (mapUpper!=null && mapUpper.containsKey(node.getParentId())) {
            MainBusinessNode upNode = mapUpper.get(node.getParentId());
            if(level == 3){
                node.setParentId(upNode.getParentId());
                upNode = node;
            }else{
                List<MainBusinessNode> list = upNode.getChildren() == null? new ArrayList<>():upNode.getChildren();
                if(!upNode.getChildIds().contains(node.getId())){
                    upNode.getChildIds().add(node.getId());
                    list.add(node);
                    upNode.setChildren(list);
                }
            }
            help(mapBiz,upNode,level);
        }
    }



    /**
     * 根据用户ID，查询用户门店业务树节点列表
     * 0-根节点；1-大区；2-分公司；3-部门；4-运营区域；5-门店
     *
     * @param userId 用户ID
     * @return 业务树节点list
     */
    public static List<MainBusinessNode> bizNodsByUserId(Long userId) {
        List<MainBusinessNode> bizNodes = new ArrayList<>();
        MainBusinessNode rootNode = buildBizNode(1000000, "业务树", 0, null, null, Boolean.TRUE);
        bizNodes.add(rootNode);

        MainBusinessNode regionNode1 = buildBizNode(1200000, "商业化体系2区", 1, null, rootNode, Boolean.TRUE);
        bizNodes.add(regionNode1);

        MainBusinessNode regionNode2 = buildBizNode(1100000, "商业化体系1区", 1, null, rootNode, Boolean.TRUE);
        bizNodes.add(regionNode2);

        MainBusinessNode cityBeijing = buildBizNode(1210000, "北京分公司", 2, null, regionNode1, Boolean.TRUE);
        bizNodes.add(cityBeijing);

        MainBusinessNode cityBeijingDept = buildBizNode(1211000, "门店管理部", 3, null, cityBeijing, Boolean.TRUE);
        bizNodes.add(cityBeijingDept);

        MainBusinessNode cityBeijingDeptAreaChaoYang = buildBizNode(1211100, "朝阳区", 4, null, cityBeijingDept, Boolean.TRUE);
        bizNodes.add(cityBeijingDeptAreaChaoYang);

        for (int i = 9; i < 11; i++) {
            MainBusinessNode cityBeijingDeptAreaChaoYangShop = buildBizNode(1211100 + i, "朝阳门店"+i, 5, 110105, cityBeijingDeptAreaChaoYang, Boolean.TRUE);
            bizNodes.add(cityBeijingDeptAreaChaoYangShop);
        }

        MainBusinessNode cityBeijingDeptAreaTD = buildBizNode(1211200, "朝阳本部门区", 4, null, cityBeijingDept, Boolean.FALSE);
//        bizNodes.add(cityBeijingDeptAreaTD);

        MainBusinessNode cityBeijingDeptAreaDongCheng = buildBizNode(1211300, "东城区", 4, null, cityBeijingDept, Boolean.FALSE);
//        bizNodes.add(cityBeijingDeptAreaTD);

        MainBusinessNode cityBeijingDeptShopTD = buildBizNode(1211299, "东城门店", 5, null, cityBeijingDeptAreaDongCheng, Boolean.FALSE);
//        bizNodes.add(cityBeijingDeptShopTD);


        return bizNodes;
    }

    public static MainBusinessNode buildBizNode(Integer id, String name, Integer type, Integer cityCode, MainBusinessNode pNode, boolean hasAuth) {
        MainBusinessNode node = new MainBusinessNode();
        node.setId(Long.valueOf(id));
        node.setCode(id + "");
        node.setName(name);
        node.setType(type);
        node.setHashAuth(hasAuth);
        node.setNodeCityCode(cityCode);
        if (Objects.nonNull(pNode)) {
            node.setParentId(pNode.getId());
        }
        return node;
    }
}
