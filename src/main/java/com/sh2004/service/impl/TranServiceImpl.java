package com.sh2004.service.impl;

import com.sh2004.base.mapper.ClueActivityRelationMapper;
import com.sh2004.base.util.DateTimeUtil;
import com.sh2004.base.util.UUIDUtil;
import com.sh2004.bean.*;
import com.sh2004.mapper.*;
import com.sh2004.service.ClueService;
import com.sh2004.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/23 18:32
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("tranService")
public class TranServiceImpl implements TranService {
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private ClueService clueService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;
    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    private TranHistoryMapper tranHistoryMapper;
    @Autowired
    private TranRemarkMapper tranRemarkMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    private ClueMapper clueMapper;

    @Override
    public void addTran(Tran tran, User user) {
        Example example = new Example(Customer.class);
        example.createCriteria().andEqualTo("name", tran.getName());
        int i = tranMapper.selectCountByExample(example);
        Clue clue = clueService.clueRemark(tran.getId());
        Activity activity = activityMapper.selectByPrimaryKey(tran.getActivityId());
        Contacts contacts = new Contacts();
        Customer customer = new Customer();
        ContactsRemark contactsRemark = new ContactsRemark();
        CustomerRemark customerRemark = new CustomerRemark();
        ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
        customer.setId(UUIDUtil.getUUID());
        customer.setOwner(clue.getOwner());
        customer.setAddress(clue.getAddress());
        customer.setContactSummary(clue.getContactSummary());
        customer.setCreateBy(user.getName());
        customer.setCreateTime(DateTimeUtil.getSysTime());
        customer.setName(clue.getCompany());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
        int insert = customerMapper.insert(customer);
        contacts.setId(UUIDUtil.getUUID());
        contacts.setOwner(activity.getOwner());
        contacts.setSource(clue.getSource());
        contacts.setCustomerId(customer.getId());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setCreateBy(user.getName());
        contacts.setCreateTime(DateTimeUtil.getSysTime());
        contacts.setDescription(clue.getDescription());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setAddress(clue.getAddress());
        int insert1 = contactsMapper.insert(contacts);
        List<ClueRemark> clueRemarks = clue.getClueRemarks();

        for (ClueRemark clueRemark : clueRemarks) {
            customerRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setId(UUIDUtil.getUUID());
            customerRemark.setNoteContent(clueRemark.getNoteContent());
            contactsRemark.setNoteContent(clueRemark.getNoteContent());
            customerRemark.setCreateBy(user.getName());
            contactsRemark.setCreateBy(user.getName());
            customerRemark.setCreateTime(DateTimeUtil.getSysTime());
            contactsRemark.setCreateTime(DateTimeUtil.getSysTime());
            customerRemark.setEditFlag("0");
            contactsRemark.setEditFlag("0");
            customerRemark.setCustomerId(customer.getId());
            contactsRemark.setContactsId(contacts.getId());
            contactsRemarkMapper.insert(contactsRemark);
            customerRemarkMapper.insert(customerRemark);
        }

        tran.setId(UUIDUtil.getUUID());
        tranMapper.insert(tran);
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setCreateBy(user.getName());
        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistoryMapper.insert(tranHistory);

        TranRemark tranRemark = new TranRemark();
        tranRemark.setId(UUIDUtil.getUUID());
        tranRemark.setCreateTime(DateTimeUtil.getSysTime());
        tranRemark.setCreateBy(user.getName());
        tranRemark.setTranId(tran.getId());
        tranRemarkMapper.insert(tranRemark);


        List<ClueRemark> clueRemarks2 = clue.getClueRemarks();
        for (ClueRemark clueRemark : clueRemarks2) {
            clueRemarkMapper.delete(clueRemark);
        }
        Example example1 = new Example(ClueActivityRelation.class);
        example1.createCriteria().andEqualTo("clueId", clue.getId());
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationMapper.selectByExample(example1);
        for (ClueActivityRelation clueActivityRelation : clueActivityRelations) {
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
            contactsActivityRelation.setContactsId(contacts.getId());
            contactsActivityRelationMapper.insert(contactsActivityRelation);
            clueActivityRelationMapper.delete(clueActivityRelation);
        }

        clueMapper.deleteByPrimaryKey(clue.getId());
    }
}
