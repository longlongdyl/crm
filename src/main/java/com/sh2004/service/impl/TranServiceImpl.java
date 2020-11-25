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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private UserMapper userMapper;
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
        System.out.println("增加" + customer);

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
        System.out.println("增加" + contacts);
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
            System.out.println("增加" + contactsRemark);
            customerRemarkMapper.insert(customerRemark);
            System.out.println("增加" + customerRemark);
        }

        tran.setId(UUIDUtil.getUUID());
        tranMapper.insert(tran);
        System.out.println("增加" + tran);
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setCreateBy(user.getName());
        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistoryMapper.insert(tranHistory);
        System.out.println("增加" + tranHistory);
        TranRemark tranRemark = new TranRemark();
        tranRemark.setId(UUIDUtil.getUUID());
        tranRemark.setCreateTime(DateTimeUtil.getSysTime());
        tranRemark.setCreateBy(user.getName());
        tranRemark.setTranId(tran.getId());
        tranRemarkMapper.insert(tranRemark);
        System.out.println("增加" + tranRemark);


        List<ClueRemark> clueRemarks2 = clue.getClueRemarks();
        for (ClueRemark clueRemark : clueRemarks2) {
            clueRemarkMapper.delete(clueRemark);
            System.out.println("删除" + clueRemark);
        }
        Example example1 = new Example(ClueActivityRelation.class);
        example1.createCriteria().andEqualTo("clueId", clue.getId());
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationMapper.selectByExample(example1);
        for (ClueActivityRelation clueActivityRelation : clueActivityRelations) {
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
            contactsActivityRelation.setContactsId(contacts.getId());
            contactsActivityRelationMapper.insert(contactsActivityRelation);
            System.out.println("增加" + contactsActivityRelation);
            clueActivityRelationMapper.delete(clueActivityRelation);
            System.out.println("删除" + clueActivityRelation);
        }

        clueMapper.deleteByPrimaryKey(clue.getId());
        System.out.println("删除" + clue);
    }

    @Override
    public List<String> queryCustomerName(String customerName) {
        Example example = new Example(Customer.class);
        example.createCriteria().andLike("name",customerName +"%");
        List<String> list = new ArrayList<>();
        List<Customer> customers = customerMapper.selectByExample(example);
        for (Customer customer : customers) {
            String name = customer.getName();
            list.add(name);
        }
        return list;
    }

    @Override
    public void insertTran(Tran tran) {
        tran.setId(UUIDUtil.getUUID());
        Example example = new Example(Customer.class);
        example.createCriteria().andEqualTo("name",tran.getCustomerId());
        List<Customer> customers = customerMapper.selectByExample(example);
        if (customers.size()>0){
            for (Customer customer : customers) {
                tran.setCustomerId(customer.getId());
            }
        }else {
            Customer customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setOwner(tran.getOwner());
            customer.setName(tran.getCustomerId());
            tran.setCustomerId(customer.getId());
            customerMapper.insert(customer);
        }

        tranMapper.insertSelective(tran);
    }

    @Override
    public List<Map<String, String>> queryTran(TranQueryVo tranQueryVo) {
        List<Map<String, String>> list = tranMapper.queryTran(tranQueryVo);
        for (Map<String, String> tran : list) {
            if (null != tran.get("owner")){
                String owner = tran.get("owner");
                User user = new User();
                user.setId(owner);
                tran.put("owner",userMapper.selectOne(user).getName());
            }
            if (null != tran.get("customerId")){
                String customerId = tran.get("customerId");
                Customer customer = new Customer();
                customer.setId(customerId);
                tran.put("customerId",customerMapper.selectOne(customer).getName());
            }
            if (null != tran.get("contactsId")){
                String contactsId = tran.get("contactsId");
                Contacts contacts = new Contacts();
                contacts.setId(contactsId);
                tran.put("contactsId",contactsMapper.selectOne(contacts).getFullname());
            }

        }
        return list;
    }

    @Override
    public Tran queryTranById(String id) {
        Tran tran = tranMapper.selectByPrimaryKey(id);
        Example example = new Example(TranHistory.class);
        example.createCriteria().andEqualTo("tranId",id);
        List<TranHistory> tranHistories = tranHistoryMapper.selectByExample(example);
        tran.setHistoryList(tranHistories);
        example = new Example(TranRemark.class);
        example.createCriteria().andEqualTo("tranId",id);
        List<TranRemark> tranRemarks = tranRemarkMapper.selectByExample(example);
        tran.setTranRemarkList(tranRemarks);
        tran.setActivityId(activityMapper.selectByPrimaryKey(tran.getActivityId()).getName());
        tran.setCustomerId(customerMapper.selectByPrimaryKey(tran.getCustomerId()).getName());
        tran.setContactsId(contactsMapper.selectByPrimaryKey(tran.getContactsId()).getFullname());
        tran.setOwner(userMapper.selectByPrimaryKey(tran.getOwner()).getName());
        return tran;
    }

    @Override
    public void updateTran(Tran tran) {
        Tran oldTran = tranMapper.selectByPrimaryKey(tran.getId());
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setStage(oldTran.getStage());
        tranHistory.setMoney(oldTran.getMoney());
        tranHistory.setTranId(oldTran.getId());
        tranHistory.setCreateBy(tran.getEditBy());
        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistoryMapper.insertSelective(tranHistory);

        Example example = new Example(Customer.class);
        example.createCriteria().andEqualTo("name",tran.getCustomerId());
        List<Customer> customers = customerMapper.selectByExample(example);
        if (customers.size() == 1){
            for (Customer customer : customers) {
                tran.setCustomerId(customer.getId());
            }
        }


        example = new Example(Contacts.class);
        example.createCriteria().andEqualTo("fullname",tran.getContactsId());
        List<Contacts> contacts = contactsMapper.selectByExample(example);
        if (contacts.size() == 1){
            for (Contacts contact : contacts) {
                tran.setContactsId(contact.getId());
            }
        }
        example = new Example(Activity.class);
        example.createCriteria().andEqualTo("name",tran.getActivityId());
        List<Activity> activities = activityMapper.selectByExample(example);
        if (activities.size() == 1){
            for (Activity activitie : activities) {
                tran.setActivityId(activitie.getId());
            }
        }
        tran.setEditTime(DateTimeUtil.getSysTime());
        tranMapper.updateByPrimaryKeySelective(tran);
    }
}
