package com.bionic.socialnetwork.idao;

import com.bionic.socailnetwork.entity.RelationsDictionary;
import java.util.List;

/**
 *
 * @author Катерина
 */
public interface IRelationsDictionaryDAO {
    List<RelationsDictionary> findAll();
    RelationsDictionary findRelationById(Integer id);
    void addRelation(RelationsDictionary relation);
    void deleteRelation(RelationsDictionary relation);
}
