package com.financetrack.development.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financetrack.development.Repository.BillRepository;
import com.financetrack.development.Repository.NotificationRepository;
import com.financetrack.development.Repository.UserRepository;
import com.financetrack.development.model.Bill;
import com.financetrack.development.model.Notification;
import com.financetrack.development.model.User;

import jakarta.transaction.Transactional;

@Service
public class NotificationService {


    @Autowired
    private BillRepository billRepository;

    @Autowired
    private NotificationRepository notificationRepository;
 
    @Autowired
    private UserRepository userRepository;


    public int dueDays(Date dueDate) {
    LocalDate today = LocalDate.now();
    System.out.println("today: " + today);

    LocalDate due;
    if (dueDate instanceof java.sql.Date) {
        due = ((java.sql.Date) dueDate).toLocalDate();
    } else {
        due = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    System.out.println("due: " + due);
    return (int) ChronoUnit.DAYS.between(today, due);
}

    @Transactional
    public void updateNotificationsFromBills(UUID userId) {
        // Your implementation here

        
        List<Bill> bills = billRepository.findBillsDueInNext30Days(userId);

        System.out.println("bills getting: " + bills);

        User user = userRepository.getUserById(userId);

        notificationRepository.deleteAllByUser_Id(userId);

        List<Bill> afterBills = billRepository.findBillsDueInNext30Days(userId);


        System.out.println("notifications deleting: " + afterBills);

        for (Bill bill : bills) {
            System.out.println("bill first one: " + bill);
            Notification notification = new Notification();
            notification.setName(bill.getBillName());
            System.out.println("notification name: " + notification.getName());
            notification.setAmount(bill.getAmount());
            System.out.println("notification amount: " + notification.getAmount());
            System.out.println("notification dueDate: " + bill.getDueDate());
            System.out.println("notification dueDays: " + dueDays(bill.getDueDate()));
            notification.setDueDays(dueDays(bill.getDueDate()));
            System.out.println("notification dueDays: " + notification.getDueDays());
            notification.setStatus("pending");
            System.out.println("notification status: " + notification.getStatus());
            notification.setTotalLimit(bill.getAmount());
            System.out.println("notification totalLimit: " + notification.getTotalLimit());
            notification.setType("reminder");
            notification.setUser(user);
            System.out.println("notification after all details: " + notification);
            notificationRepository.save(notification);
        }
    }
}
