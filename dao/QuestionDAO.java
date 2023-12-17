package com.example.web.dao;

import com.example.web.fordb.OrganizationRepo;
import com.example.web.models.Branch;
import com.example.web.models.Contact;
import com.example.web.models.Human;
import com.example.web.models.InputFields;
import com.example.web.models.JobTitle;
import com.example.web.models.Office;
import com.example.web.models.Organization;
import com.example.web.models.OutputFields;
import com.example.web.models.Rate;
import com.example.web.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class QuestionDAO {

    @Autowired
    private OrganizationRepo organizationRepo;


    @Transactional(readOnly = true)
    public List<OutputFields> query(InputFields input)
    {
        List<OutputFields> output = new ArrayList<>();

        List<Organization> organizations = (List<Organization>) organizationRepo.findAll();

        for (Organization org : organizations)
        {
            List<Subdivision> subdivisions = org.getSubdivisions();

            String nameOrg = org.getNameOrganization();
            String addressOrg = org.getAddress();

            for (Subdivision sub : subdivisions)
            {
                String nameSub = sub.getNameSubdivision();
                String email = sub.getEmail();

                List<Office> offices = sub.getOffices();

                for (Office off: offices)
                {
                    int numOff = off.getNumberOffice();

                    Human human = off.getHumanOffice();

                    String firstName = human.getNameHuman();
                    String surname = human.getSurname();
                    String patronymic = human.getPatronymic();

                    List<Rate> rates = human.getRates();

                    OutputFields onePart = new OutputFields();

                    onePart.setNameOrganization(nameOrg);
                    onePart.setAddressOrganization(addressOrg);
                    onePart.setNameSubdivision(nameSub);
                    onePart.setEmailSubdivision(email);
                    onePart.setNumberOffice(numOff);
                    onePart.setFirstName(firstName);
                    onePart.setSurname(surname);
                    onePart.setPatronymic(patronymic);
                    onePart.setSumRateHuman(organizationRepo.countRate(human.getPK_Human()));

                    for (Rate rate : rates)
                    {
                        JobTitle jobTitle = rate.getJobTitleRate();

                        String nameJobTitle = jobTitle.getNameOfJobTitle();

                        onePart.addJobTitle(nameJobTitle);
                    }

                    List<Contact> contacts = human.getContacts();

                    for (Contact cont : contacts)
                    {
                        String valueContact = cont.getValueContact();

                        onePart.addContact(valueContact);
                    }

                    output.add(onePart);
                }
            }
        }

        System.out.println(input);

        if (input != null) {
            Iterator<OutputFields> fieldsIterator = output.iterator();//создаем итератор
            while (fieldsIterator.hasNext()) {//до тех пор, пока в списке есть элементы

                OutputFields nextField = fieldsIterator.next();//получаем следующий элемент

                boolean flagDelete = false;

                if (input.getNameOrganization() != null && input.getNameOrganization().length() > 0 && flagDelete == false)
                    if (!Objects.equals(input.getNameOrganization(), nextField.getNameOrganization())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено1!");
                    }

                if (input.getNameSubdivision() != null && input.getNameSubdivision().length() > 0 && flagDelete == false)
                    if (!Objects.equals(input.getNameSubdivision(), nextField.getNameSubdivision())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено2!");
                    }

                if (input.getAddressOrganization() != null && input.getAddressOrganization().length() > 0 && flagDelete == false)
                    if (!Objects.equals(input.getAddressOrganization(), nextField.getAddressOrganization())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено3!");
                    }

                if (input.getEmailSubdivision() != null && input.getEmailSubdivision().length() > 0 && flagDelete == false)
                    if (!Objects.equals(input.getEmailSubdivision(), nextField.getEmailSubdivision())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено4!");
                    }

                if (input.getNumberOffice() > 0 && flagDelete == false)
                    if (input.getNumberOffice() != nextField.getNumberOffice()) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено5!");
                    }

                if (input.getFirstName() != null && input.getFirstName().length() > 0 && flagDelete == false)
                    if (!Objects.equals(input.getFirstName(), nextField.getFirstName())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено6!");
                    }

                if (input.getSurname() != null && input.getSurname().length() > 0 && flagDelete == false)
                    if (!Objects.equals(input.getSurname(), nextField.getSurname())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено7!");
                    }

                if (input.getPatronymic() != null && input.getPatronymic().length() > 0 && flagDelete == false)
                    if (!Objects.equals(input.getPatronymic(), nextField.getPatronymic())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено8!");
                    }



                if (input.getContactNumber() != null && input.getContactNumber().length() > 0 && flagDelete == false)
                    if (!nextField.getContactNumber().contains(input.getContactNumber())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено9!");
                    }


                if (input.getNameJobTitle() != null && input.getNameJobTitle().length() > 0 && flagDelete == false)
                    if (!nextField.getNameJobTitle().contains(input.getNameJobTitle())) {
                        fieldsIterator.remove();//удаляем
                        flagDelete = true;
                        System.out.println("Удалено10!");
                    }
            }
        }

        return output;
    }

}
