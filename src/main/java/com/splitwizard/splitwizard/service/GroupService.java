package com.splitwizard.splitwizard.service;

import com.splitwizard.splitwizard.Util.Result;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    Result getById(Integer id);

}
