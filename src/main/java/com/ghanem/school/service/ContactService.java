package com.ghanem.school.service;

import com.ghanem.school.constants.GhanemSchoolConstants;
import com.ghanem.school.model.Contact;
import com.ghanem.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {
@Autowired
private ContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact){
       contact.setStatus(GhanemSchoolConstants.OPEN);
       Contact savedContact = contactRepository.save(contact);
        if(savedContact != null && savedContact.getContactId() >0){
            return true;
        }
        return false;

    }

    public List<Contact> findMsgWithOpenStatus() {
        return contactRepository.findByStatus(GhanemSchoolConstants.OPEN);
    }

    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(GhanemSchoolConstants.CLOSE);
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
