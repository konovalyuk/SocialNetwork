package com.bionic.socialnetwork.idao;

import com.bionic.socailnetwork.entity.RequestStatusDictionary;
import java.util.List;

/**
 *
 * @author Катерина
 */
public interface IRequestStatusDictionaryDAO {
    List<RequestStatusDictionary> findAll();
    RequestStatusDictionary findRequestStatusById(Integer id);
    RequestStatusDictionary findRequestStatusByDescription(String value);
    void addRequestStatus(RequestStatusDictionary status);
    void deleteRequestStatus(RequestStatusDictionary status);
    void deleteRequestStatus(Integer idRequest);
}
