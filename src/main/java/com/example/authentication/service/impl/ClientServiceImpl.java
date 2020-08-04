package com.example.authentication.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.authentication.dao.ClientMapper;
import com.example.authentication.model.Client;
import com.example.authentication.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

}
