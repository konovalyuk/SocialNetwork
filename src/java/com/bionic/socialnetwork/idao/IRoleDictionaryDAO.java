package com.bionic.socialnetwork.idao;

import com.bionic.socailnetwork.entity.RoleDictionary;
import java.util.List;

/**
 *
 * @author Катерина
 */
public interface IRoleDictionaryDAO {
    List<RoleDictionary> findAll();
    RoleDictionary findRoleById(Integer id);
    void addRole(RoleDictionary role);
    void deleteRole(RoleDictionary role);
    void deleteRole(Integer idRequest);
}
