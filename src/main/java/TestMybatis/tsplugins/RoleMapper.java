package TestMybatis.tsplugins;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-08 22:11
 * @from
 **/
public interface RoleMapper {
   Role getRole(Long id);
   Role getRole2(Long id);
   Role findRole(String roleName);
   int deleteRole(Long id);
   int insertRole(Role role);
}
