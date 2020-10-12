package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Telephone;
import ru.itis.witchCrutch.repositories.TelephoneRepository;

public class TelephoneServiceImpl implements TelephoneService{

    private final TelephoneRepository telephoneRepository;

    public TelephoneServiceImpl(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    @Override
    public void saveTelephone(Telephone telephone) {
        telephoneRepository.save(telephone);
    }
}
